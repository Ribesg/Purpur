package fr.ribesg.minecraft.purpur.api.event

import java.lang.reflect.Method

/**
 * @author Ribesg
 */
public class InvalidEventHandlerException(
    method: Method, reason: String
) : Exception(
    "Method " + method.getDeclaringClass().getName() + '.' +
    method.getName() + "(...) isn't a valid EventHandler: $reason"
)
