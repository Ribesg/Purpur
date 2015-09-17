package fr.ribesg.minecraft.purpur.api.event

import fr.ribesg.minecraft.purpur.Props
import fr.ribesg.minecraft.purpur.parse
import java.util.Calendar

/**
 * @author Ribesg
 */
data class PlayerQuitEvent(val time: Calendar, content: String) : Event() {

    val playerName: String
    val reason: String // TODO Or not?

    init {
        val (p, r) = Props.regexPlayerQuit.parse(content)
        playerName = p
        reason = r
    }
}
