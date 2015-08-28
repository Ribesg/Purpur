package fr.ribesg.minecraft.purpur.event

import fr.ribesg.minecraft.purpur.Props
import fr.ribesg.minecraft.purpur.parse
import java.util.Calendar

/**
 * @author Ribesg
 */
public data class PlayerChatEvent(public val time: Calendar, content: String) : Event() {

    public val playerName: String
    public val text: String

    init {
        val (p, t) = Props.regexPlayerChat.parse(content)
        playerName = p
        text = t
    }
}
