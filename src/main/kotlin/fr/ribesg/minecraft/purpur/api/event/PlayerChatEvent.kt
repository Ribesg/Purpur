package fr.ribesg.minecraft.purpur.api.event

import fr.ribesg.minecraft.purpur.Props
import fr.ribesg.minecraft.purpur.parse
import java.util.*

/**
 * @author Ribesg
 */
data class PlayerChatEvent(val time: Calendar, content: String) : Event() {

    val playerName: String
    val text: String

    init {
        val (p, t) = Props.regexPlayerChat.parse(content)
        playerName = p
        text = t
    }
}
