package fr.ribesg.minecraft.purpur

import fr.ribesg.minecraft.purpur.api.event.RawLogLineEvent
import fr.ribesg.minecraft.purpur.api.event.ServerStoppedEvent
import fr.ribesg.minecraft.purpur.event.EventManager
import fr.ribesg.minecraft.purpur.event.InternalHandlers
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.file.Path
import java.util.Queue
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.properties.Delegates

/**
 * @author Ribesg
 */

public class ServerWrapper(serverJarPath: Path, serverFolderPath: Path) : Thread() {

    private val builder: ProcessBuilder

    private var reader: BufferedReader by Delegates.notNull()
    private var writer: BufferedWriter by Delegates.notNull()

    private val queue: Queue<String>

    private volatile var loop: Boolean

    init {
        Thread.setDefaultUncaughtExceptionHandler { thread, t -> Log.error(t) }

        EventManager.registerHandlers(InternalHandlers)

        builder = ProcessBuilder(
            "java",
            "-jar",
            "\"" + serverJarPath.toAbsolutePath() + '"',
            "nogui"
        ).directory(serverFolderPath.toAbsolutePath().toFile())

        queue = ConcurrentLinkedQueue()

        loop = true
    }

    override fun run() {
        Log.info("Starting Minecraft Server...")

        val p = builder.start()
        reader = BufferedReader(InputStreamReader(p.getInputStream()))
        writer = BufferedWriter(OutputStreamWriter(p.getOutputStream()))

        var line: String?
        var time: Long
        while (loop) {
            time = System.currentTimeMillis()

            do {
                line = reader.readLine()
                if (line != null) {
                    Log.server(line)
                    EventManager.call(RawLogLineEvent(line))
                }
            } while (line != null)

            while (queue.isNotEmpty()) {
                writer.write(queue.remove())
            }

            time = System.currentTimeMillis() - time
            if (time > 0) {
                Thread.sleep(50 - Math.min(time, 50))
            } else if (time < 0) {
                Log.debug("Loop lasted ${time}ms")
            }

            if (!p.isAlive()) {
                Log.info("Minecraft server stopped!")
                loop = false
            }
        }
        if (p.isAlive()) {
            Log.info("Stopping Minecraft Server...")
            writer.write("stop\n")
        }
        p.waitFor()
        EventManager.call(ServerStoppedEvent())
    }

    public fun print(line: String) {
        queue.add(line)
    }

    public fun stopServer() {
        loop = false
    }
}
