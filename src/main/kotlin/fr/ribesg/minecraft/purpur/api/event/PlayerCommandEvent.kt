package fr.ribesg.minecraft.purpur.api.event

import fr.ribesg.minecraft.purpur.Props
import fr.ribesg.minecraft.purpur.parse
import java.util.Calendar

/**
 * @author Ribesg
 */
data class PlayerCommandEvent(val time: Calendar, content: String) : Event() {

    val playerName: String
    val command: String
    val args: Array<String>

    init {
        val (p, t, a) = Props.regexPlayerCommand.parse(content)
        playerName = p
        command = t
        args = a.split(' ').toTypedArray()
    }

}
