package fr.ribesg.minecraft.purpur.api.plugin

import fr.ribesg.minecraft.purpur.event.EventManager
import org.jetbrains.annotations.NotNull

/**
 * Purpur plugins should extend this class.
 *
 * @author Ribesg
 */
abstract class Plugin {

    /**
     * Name of the plugin
     */
    abstract val name: String

    /**
     * Version of the plugin
     */
    abstract val version: String

    /**
     * Constructor
     */
    init {
        EventManager.registerHandlers(this, true)
    }

    /**
     * Registers all handlers held by the provided object.
     */
    fun registerHandlers(@NotNull handlersHolder: Any) {
        EventManager.registerHandlers(handlersHolder)
    }

    /**
     * Unregisters all handlers held by the provided object.
     */
    fun unRegisterHandlers(@NotNull handlersHolder: Any) {
        EventManager.unRegisterHandlers(handlersHolder)
    }
}
