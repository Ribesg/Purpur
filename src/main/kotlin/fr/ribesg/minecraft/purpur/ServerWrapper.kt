package fr.ribesg.minecraft.purpur

import fr.ribesg.minecraft.purpur.event.EventManager
import fr.ribesg.minecraft.purpur.event.RawLineEvent
import java.io.*
import java.util.Queue
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * @author Ribesg
 */

public class ServerWrapper(input: InputStream, output: OutputStream) : Thread() {

    private val reader: BufferedReader
    private val writer: BufferedWriter

    private val queue: Queue<String>

    private volatile var loop: Boolean

    init {
        reader = BufferedReader(InputStreamReader(input))
        writer = BufferedWriter(OutputStreamWriter(output))

        queue = ConcurrentLinkedQueue()

        loop = true
    }

    override fun run() {
        var line: String?
        var time: Long
        while (loop) {
            time = System.currentTimeMillis()

            do {
                line = reader.readLine()
                if (line != null) {
                    Log.server(line)
                    EventManager.call(RawLineEvent(line))
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
        }
    }

    public fun print(line: String) {
        queue.add(line)
    }

    public fun stopLoop() {
        loop = false
    }
}
