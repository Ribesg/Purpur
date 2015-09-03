package fr.ribesg.minecraft.purpur.api.plugin

import fr.ribesg.minecraft.purpur.event.EventManager
import org.jetbrains.annotations.NotNull

/**
 * Purpur plugins should extend this class.
 *
 * @author Ribesg
 */
public abstract class Plugin(

    /**
     * Name of the plugin
     */
    public val name: String,

    /**
     * Version of the plugin
     */
    public val version: String

) {

    init {
        EventManager.registerHandlers(this, true)
    }

    /**
     * Registers all handlers held by the provided object.
     */
    public fun registerHandlers(@NotNull handlersHolder: Any) {
        EventManager.registerHandlers(handlersHolder)
    }

    /**
     * Unregisters all handlers held by the provided object.
     */
    public fun unRegisterHandlers(@NotNull handlersHolder: Any) {
        EventManager.unRegisterHandlers(handlersHolder)
    }
}
