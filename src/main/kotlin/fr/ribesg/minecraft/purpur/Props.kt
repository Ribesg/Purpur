package fr.ribesg.minecraft.purpur

import java.util.Properties

/**
 * @author Ribesg
 */

public object Props {

    public val name: String
    public val version: String

    private val props: Properties

    init {
        props = Properties()
        props.load(ClassLoader.getSystemResourceAsStream("purpur.properties"))

        name = props.getProperty("name")
        version = props.getProperty("version")
    }
}
