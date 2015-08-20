package fr.ribesg.minecraft.purpur

import fr.ribesg.minecraft.purpur.event.EventManager
import fr.ribesg.minecraft.purpur.event.RawLineEvent
import java.nio.file.Files
import java.nio.file.Paths
import fr.ribesg.minecraft.purpur.event.EventHandler as eventHandler

/**
 * TODO This is just a prototyping thing, the main should be almost empty in the end
 * @author Ribesg
 */

public fun main(args: Array<String>) {
    Thread.setDefaultUncaughtExceptionHandler { thread, t -> Log.error(t) }
    Log.setDebugEnabled(true)
    Log.info("Starting ${Props.name} version ${Props.version}")

    var serverFolderName: String = "server"
    var serverJarName: String? = null
    try {
        for (i in args.indices) {
            when (args[i]) {
                "--directory",
                "-d" -> serverFolderName = args[i + 1]

                "--jar",
                "-j" -> serverJarName = args[i + 1]

                else -> exitError()
            }
        }
    } catch (e: IndexOutOfBoundsException) {
        exitError()
    }

    val serverFolderPath = Paths.get(serverFolderName)
    if (!Files.isDirectory(serverFolderPath)) {
        exitError("Please specify a server folder name")
    }

    if (serverJarName == null) {
        Files.newDirectoryStream(serverFolderPath) { it.toString().endsWith(".jar") }.use { dirStream ->
            val files = dirStream.toList()
            if (files.size() == 1) {
                serverJarName = files.first().getFileName().toString()
            } else {
                exitError("Please specify a server jar name")
            }
        }
    }

    val serverJarPath = serverFolderPath.resolve(serverJarName)

    EventManager.registerHandlers(RawLineEventHandler())

    ServerProcess(serverJarPath, serverFolderPath)

    Log.info("Server dead, stopping ${Props.name} version ${Props.version}")
}

private class RawLineEventHandler {
    public eventHandler fun onRawLineEvent(event: RawLineEvent) {
        if (event.line.endsWith("You need to agree to the EULA in order to run the server. Go to eula.txt for more info.")) {
            Log.info("EULA!")
        }
    }
}

private fun exitError(error: String? = null) {
    if (error != null) Log.error(error) else printUsage()
    System.exit(42)
}

private fun printUsage() {
    listOf(
        //                                                 column n°80 for reference -> |
        "USAGE",
        "    java -jar Purpur.jar [OPTIONS]",
        "",
        "OPTIONS",
        "    -d, --directory <path>",
        "        Selects the directory containing the Minecraft Server to run",
        "        DEFAULT: 'server'",
        "",
        "    -j, --jar <name>",
        "        Specifies the name of the Minecraft Server jar file to run",
        "        DEFAULT: will find the jar if there's only one, or fail",
        ""
    ).forEach { Log.info(it) }
}
