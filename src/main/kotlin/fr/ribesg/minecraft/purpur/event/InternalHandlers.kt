package fr.ribesg.minecraft.purpur.event

import fr.ribesg.minecraft.purpur.Props
import fr.ribesg.minecraft.purpur.api.event.*

/**
 * @author Ribesg
 */
public object InternalHandlers {

    /**
     * Calls a [LogLineEvent] for each [RawLogLineEvent].
     */
    @EventHandler(EventHandlerPriority.INTERNAL)
    public fun onRawLogLine(e: RawLogLineEvent): Unit
        = EventManager.call(LogLineEvent(e.line.trim()))

    /**
     * Calls all Regex-triggered [Event]s.
     */
    // TODO Once most events are here, order them correctly (for example, chat first).
    @EventHandler(EventHandlerPriority.INTERNAL)
    public fun onLogLine(e: LogLineEvent): Unit = when {

        Props.regexCreateServerProperties.matches(e.content)
        -> EventManager.call(CreateServerPropertiesEvent())

        Props.regexEulaRequiresAgreement.matches(e.content)
        -> EventManager.call(EulaRequiresAgreementEvent())

        Props.regexServerReady.matches(e.content)
        -> EventManager.call(ServerReadyEvent())

        Props.regexPlayerJoin.matches(e.content)
        -> EventManager.call(PlayerJoinEvent(e.time, e.content))

        Props.regexPlayerQuit.matches(e.content)
        -> EventManager.call(PlayerQuitEvent(e.time, e.content))

        Props.regexPlayerChat.matches(e.content)
        -> EventManager.call(PlayerChatEvent(e.time, e.content))

        Props.regexPlayerCommand.matches(e.content)
        -> EventManager.call(PlayerCommandEvent(e.time, e.content))

    }
}
