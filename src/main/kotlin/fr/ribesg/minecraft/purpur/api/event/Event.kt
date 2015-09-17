package fr.ribesg.minecraft.purpur.api.event

/**
 * An Event.
 *
 * @author Ribesg
 */
abstract class Event {

    /**
     * If this event has been consumed by a handler
     */
    private var consumed = false

    /**
     * @return true if this event has been consumed by a handler
     */
    fun isConsumed(): Boolean = this.consumed

    /**
     * Consumes this event.
     */
    fun consume() {
        this.consumed = true
    }
}
