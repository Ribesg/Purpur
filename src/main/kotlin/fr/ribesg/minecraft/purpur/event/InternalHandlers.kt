package fr.ribesg.minecraft.purpur.event

import fr.ribesg.minecraft.purpur.Props
import fr.ribesg.minecraft.purpur.event.EventHandler as eventHandler
import fr.ribesg.minecraft.purpur.event.EventHandlerPriority as Priority

/**
 * @author Ribesg
 */
public object InternalHandlers {

    /**
     * Calls a [LogLineEvent] for each [RawLogLineEvent].
     */
    eventHandler(Priority.INTERNAL)
    public fun onRawLogLine(e: RawLogLineEvent): Unit
        = EventManager.call(LogLineEvent(e.line.trim()))

    /**
     * Calls all Regex-triggered [Event]s.
     */
    // TODO Once most events are here, order them correctly (for example, chat first).
    eventHandler(Priority.INTERNAL)
    public fun onLogLine(e: LogLineEvent): Unit = when {

        Props.regexCreateServerProperties.matches(e.content)
        -> EventManager.call(CreateServerPropertiesEvent())

        Props.regexEulaRequiresAgreement.matches(e.content)
        -> EventManager.call(EulaRequiresAgreementEvent())

        Props.regexServerReady.matches(e.content)
        -> EventManager.call(ServerReadyEvent())

    }
}
