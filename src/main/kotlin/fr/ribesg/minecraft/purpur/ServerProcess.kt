package fr.ribesg.minecraft.purpur

import java.nio.file.Path

/**
 * @author Ribesg
 */
public class ServerProcess(serverJarPath: Path, serverFolderPath: Path) {

    init {
        val pb = ProcessBuilder(
            "java",
            "-jar",
            "\"" + serverJarPath.toAbsolutePath() + '"',
            "nogui"
        ).directory(serverFolderPath.toAbsolutePath().toFile())

        val p = pb.start()

        val input = p.getInputStream()
        val output = p.getOutputStream()

        val handlerThread = ServerWrapper(input, output)
        handlerThread.start()

        p.waitFor()
        handlerThread.stopLoop()
        handlerThread.join()
    }
}
