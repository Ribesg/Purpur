package fr.ribesg.minecraft.purpur.api.event

import fr.ribesg.minecraft.purpur.Props
import fr.ribesg.minecraft.purpur.parse
import java.util.Calendar

/**
 * @author Ribesg
 */
data class PlayerJoinEvent(val time: Calendar, content: String) : Event() {

    val playerName: String

    init {
        playerName = Props.regexPlayerJoin.parse(content)[0]
    }
}
