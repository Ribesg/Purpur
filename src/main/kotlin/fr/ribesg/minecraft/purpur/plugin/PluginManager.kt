package fr.ribesg.minecraft.purpur.plugin

import fr.ribesg.minecraft.purpur.api.plugin.Plugin
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * @author Ribesg
 */
public object PluginManager {

    private val plugins = linkedListOf<Plugin>()

    init {
        val root = Paths.get("plugins")
        Files.newDirectoryStream(root).use { dir ->
            dir.forEach { file ->
                if (file.getFileName().toString().endsWith(".jar")) {
                    loadPlugin(file)
                }
            }
        }
    }

    private fun loadPlugin(jarPath: Path) {
        // TODO
    }
}
