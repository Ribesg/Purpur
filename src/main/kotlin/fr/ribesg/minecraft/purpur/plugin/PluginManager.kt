package fr.ribesg.minecraft.purpur.plugin

import fr.ribesg.minecraft.purpur.Log
import fr.ribesg.minecraft.purpur.api.plugin.Plugin
import java.net.URL
import java.net.URLClassLoader
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.NoSuchElementException
import java.util.Properties
import java.util.jar.JarFile

/**
 * @author Ribesg
 */
object PluginManager {

    private val plugins = linkedListOf<Plugin>()

    init {
        val root = Paths.get("plugins")
        if (Files.isDirectory(root)) {
            Files.newDirectoryStream(root).use { dir ->
                dir.forEach { file ->
                    if (file.getFileName().toString().endsWith(".jar")) {
                        loadPlugin(file)
                    }
                }
            }
        }
    }

    private fun loadPlugin(jarPath: Path) {
        val jar = JarFile(jarPath.toFile())
        val propsEntry = try {
            jar.entries().asSequence().first { "plugin.properties".equals(it.getName()) }
        } catch (e: NoSuchElementException) {
            Log.error("Missing plugin.properties in ${jarPath}")
            return
        }
        val props = Properties()
        props.load(jar.getInputStream(propsEntry))

        val name = props.getProperty("name")
        val version = props.getProperty("version")
        val main = props.getProperty("main")

        if (name == null) {
            Log.error("Can't load plugin in ${jarPath}: Missing 'name' property in plugin.properties")
            return
        } else if (version == null) {
            Log.error("Can't load plugin in ${jarPath}: Missing 'version' property in plugin.properties")
            return
        } else if (main == null) {
            Log.error("Can't load plugin in ${jarPath}: Missing 'main' property in plugin.properties")
            return
        }

        this.loadJar(name, version, jarPath)

        val plugin = try {
            ClassLoader.getSystemClassLoader().loadClass(main).newInstance() as Plugin
        } catch (e: Throwable) {
            Log.error("Failed to load $name", e)
            return
        }

        this.plugins.add(plugin)
    }

    private fun loadJar(name: String, version: String, jarPath: Path) {
        try {
            val addURL = javaClass<URLClassLoader>().getDeclaredMethod("addURL", javaClass<URL>())
            addURL.setAccessible(true)
            addURL.invoke(ClassLoader.getSystemClassLoader(), jarPath.toFile().toURI().toURL())
            Log.info("$name version $version loaded")
        } catch (e: ReflectiveOperationException) {
            Log.error("Failed to load classes of $jarPath", e)
        }
    }
}
