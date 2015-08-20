package fr.ribesg.minecraft.purpur.event

/**
 * @author Ribesg
 */
public open class Event {

    private var consumed = false

    public fun isConsumed(): Boolean = this.consumed

    public fun consume() {
        this.consumed = true
    }
}
