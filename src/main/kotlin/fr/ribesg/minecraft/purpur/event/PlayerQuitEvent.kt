package fr.ribesg.minecraft.purpur.event

import fr.ribesg.minecraft.purpur.Props
import fr.ribesg.minecraft.purpur.parse
import java.util.Calendar

/**
 * @author Ribesg
 */
public data class PlayerQuitEvent(public val time: Calendar, content: String) : Event() {

    public val playerName: String
    public val reason: String // TODO Or not?

    init {
        val (p, r) = Props.regexPlayerQuit.parse(content)
        playerName = p
        reason = r
    }
}
