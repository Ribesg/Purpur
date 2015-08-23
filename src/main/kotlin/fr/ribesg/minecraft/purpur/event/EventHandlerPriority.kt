package fr.ribesg.minecraft.purpur.event

/**
 * Priority of an [EventHandler].
 *
 * @author Ribesg
 */
public enum class EventHandlerPriority {

    /**
     * Use this if you want your [EventHandler] to be executed before any
     * internal EventHandler.
     */
    PRE_INTERNAL,

    /**
     * This is the internal EventHandlerPriority. It should only be used by
     * Purpur.
     */
    @deprecated("Should only be used by Purpur")
    INTERNAL,

    /**
     * Use this if you want your [EventHandler] to be executed before any
     * default EventHandler.
     */
    FIRST,

    /**
     * This is the default EventHandlerPriority. If you don't know what to use, use this.
     */
    DEFAULT,

    /**
     * Use this if you want your [EventHandler] to be executed after any
     * default EventHandler.
     */
    LAST,
}
