package fr.ribesg.minecraft.purpur.api.event

/**
 * @author Ribesg
 */

data class RawLogLineEvent(val line: String) : Event()

class CreateServerPropertiesEvent : Event()

class EulaRequiresAgreementEvent : Event()

class ServerReadyEvent : Event()

class ServerStoppedEvent : Event()
