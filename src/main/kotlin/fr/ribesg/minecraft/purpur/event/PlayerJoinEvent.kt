package fr.ribesg.minecraft.purpur.event

import fr.ribesg.minecraft.purpur.Props
import fr.ribesg.minecraft.purpur.parse
import java.util.Calendar

/**
 * @author Ribesg
 */
public data class PlayerJoinEvent(public val time: Calendar, content: String) : Event() {

    public val playerName: String

    init {
        playerName = Props.regexPlayerJoin.parse(content)[0]
    }
}
