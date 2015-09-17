package fr.ribesg.minecraft.purpur.api.event

/**
 * Annotation used to mark Event handlers.
 *
 * This annotation should only be used on public methods having a single
 * parameter of type [Event] or a subclass of Event.
 *
 * @author Ribesg
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class EventHandler(
    val priority: EventHandlerPriority = EventHandlerPriority.DEFAULT,
    val ignoreConsumed: Boolean = true
)
