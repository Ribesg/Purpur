package fr.ribesg.minecraft.purpur.event

import fr.ribesg.minecraft.purpur.Props
import fr.ribesg.minecraft.purpur.parse
import java.util.Calendar

/**
 * @author Ribesg
 */
public data class PlayerCommandEvent(public val time: Calendar, content: String) : Event() {

    public val playerName: String
    public val command: String
    public val args: Array<String>

    init {
        val (p, t, a) = Props.regexPlayerCommand.parse(content)
        playerName = p
        command = t
        args = a.split(' ').toTypedArray()
    }

}
