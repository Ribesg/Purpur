package fr.ribesg.minecraft.purpur.event

import fr.ribesg.minecraft.purpur.Log
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.Queue
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Manages Events. Yeah.
 * @author Ribesg
 */
public object EventManager {

    /**
     * This little private Class is used to be able to store Object instances
     * and associated EventHandler methods in pairs.
     */
    private class ObjectMethod(

        /**
         * Object instance
         */
        public val instance: Any,

        /**
         * EventHandler method
         */
        public val method: Method,

        /**
         * If this method should be called for a consumed Event
         */
        public val ignoreConsumed: Boolean
    )

    /**
     * A Map to store all the EventHandlers
     */
    private val handlers: MutableMap<Class<out Event>, MutableMap<EventHandlerPriority, Queue<ObjectMethod>>>

    /**
     * Builds the EventManager instance
     */
    init {
        this.handlers = ConcurrentHashMap()
    }

    /**
     * Registers all handlers of the provided object.
     * <p>
     * Valid EventHandlers are public method with the
     * [EventHandler] annotation.
     *
     * @param handlersHolder an object holding one or multiple EventHandlers
     */
    suppress("UNCHECKED_CAST")
    public fun registerHandlers(handlersHolder: Any, ignoreErrors: Boolean = false) {
        var eh: EventHandler?
        var parameterType: Class<*>
        var handlerRegistered = false
        for (m: Method in handlersHolder.javaClass.getMethods()) {
            eh = m.getAnnotation(javaClass<EventHandler>())
            if (eh != null) {
                parameterType = m.getParameterTypes()[0]
                if (m.getParameterCount() != 1 || !javaClass<Event>().isAssignableFrom(parameterType)) {
                    throw InvalidEventHandlerException(m, "Invalid parameter count or type")
                } else if (!Modifier.isPublic(m.getModifiers())) {
                    throw InvalidEventHandlerException(m, "Not public")
                } else {
                    var eventHandlers = this.handlers.getOrPut(
                        parameterType as Class<out Event>,
                        { ConcurrentHashMap() }
                    )
                    var priorityHandlers = eventHandlers.getOrPut(
                        eh.priority,
                        { ConcurrentLinkedQueue() }
                    )
                    priorityHandlers.add(ObjectMethod(handlersHolder, m, eh.ignoreConsumed))
                    handlerRegistered = true
                }
            }
        }
        if (!ignoreErrors && !handlerRegistered) {
            throw IllegalArgumentException("Provided object class '" + handlersHolder.javaClass.getName() + "' has no valid EventHandler")
        }
    }

    /**
     * Unregisters all handlers of the provided object.
     *
     * @param handlersHolder     an object holding one or multiple registered
     *                           EventHandlers
     * @param ignoreUnregistered if this call should ignore errors that may
     *                           occur if the provided instance isn't
     *                           registered
     */
    public fun unRegisterHandlers(handlersHolder: Any, ignoreErrors: Boolean = false) {
        var eh: EventHandler?
        var parameterType: Class<*>
        var registeredHandlerFound = false
        for (m: Method in handlersHolder.javaClass.getMethods()) {
            eh = m.getAnnotation(javaClass<EventHandler>())
            if (m.isAccessible() && eh != null) {
                parameterType = m.getParameterTypes()[0]
                if (m.getParameterCount() != 1 || !javaClass<Event>().isAssignableFrom(parameterType)) {
                    throw InvalidEventHandlerException(m, "Invalid parameter count or type")
                } else {
                    var eventHandlers = this.handlers.get(parameterType)
                    if (eventHandlers != null) {
                        var priorityHandlers = eventHandlers.get(eh.priority)
                        if (priorityHandlers != null) {
                            val it = priorityHandlers.iterator()
                            while (it.hasNext()) {
                                if (it.next().instance == handlersHolder) {
                                    it.remove()
                                    registeredHandlerFound = true
                                    break
                                }
                            }
                            if (priorityHandlers.isEmpty()) {
                                eventHandlers.remove(eh.priority)
                            }
                        }
                        if (eventHandlers.isEmpty()) {
                            this.handlers.remove(parameterType)
                        }
                    }
                }
            }
        }
        if (!ignoreErrors && !registeredHandlerFound) {
            throw IllegalArgumentException("Provided instance of '" + handlersHolder.javaClass.getName() + "' has no registered EventHandler")
        }
    }

    /**
     * Calls an Event.
     *
     * @param event an Event
     */
    public fun call(event: Event) {
        val clazz = event.javaClass
        val eventHandlers = this.handlers.get(clazz) ?: return
        for (priority in EventHandlerPriority.values ()) {
            val priorityHandlers = eventHandlers.get(priority) ?: continue
            priorityHandlers.forEach { om ->
                try {
                    if (!event.isConsumed() || !om.ignoreConsumed) {
                        om.method.invoke(om.instance, event)
                    }
                } catch (t: Throwable) {
                    Log.error(
                        "EventHandler '" + om.method.getDeclaringClass().getName() + '.' + om.method.getName() +
                        "(...)' invokation failed", t
                    )
                }
            }
        }
    }
}
