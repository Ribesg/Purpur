package fr.ribesg.minecraft.purpur.api.event

/**
 * @author Ribesg
 */

public data class RawLogLineEvent(val line: String) : Event()

public class CreateServerPropertiesEvent : Event()

public class EulaRequiresAgreementEvent : Event()

public class ServerReadyEvent : Event()

public class ServerStoppedEvent : Event()
