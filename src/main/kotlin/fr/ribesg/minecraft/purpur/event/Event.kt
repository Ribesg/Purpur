package fr.ribesg.minecraft.purpur.event

/**
 * An Event.
 *
 * @author Ribesg
 */
public abstract class Event {

    /**
     * If this event has been consumed by a handler
     */
    private var consumed = false

    /**
     * @return true if this event has been consumed by a handler
     */
    public fun isConsumed(): Boolean = this.consumed

    /**
     * Consumes this event.
     */
    public fun consume() {
        this.consumed = true
    }
}
