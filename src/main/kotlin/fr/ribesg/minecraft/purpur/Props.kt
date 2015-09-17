package fr.ribesg.minecraft.purpur

import java.util.*
import kotlin.text.Regex

/**
 * Internal properties from purpur.properties, with values generated by Maven.
 *
 * @author Ribesg
 */
internal object Props {

    /**
     * Maven Project Name
     */
    val name: String

    /**
     * Maven Version
     */
    val version: String

    /**
     * Regex for raw server log line parsing
     */
    val regexLine: Regex

    /**
     * Regex triggering
     * [fr.ribesg.minecraft.purpur.event.CreateServerPropertiesEvent].
     */
    val regexCreateServerProperties: Regex

    /**
     * Regex triggering
     * [fr.ribesg.minecraft.purpur.event.EulaRequiresAgreementEvent].
     */
    val regexEulaRequiresAgreement: Regex

    /**
     * Regex triggering
     * [fr.ribesg.minecraft.purpur.event.ServerReadyEvent].
     */
    val regexServerReady: Regex

    /**
     * Regex triggering
     * [fr.ribesg.minecraft.purpur.event.PlayerJoinEvent].
     */
    val regexPlayerJoin: Regex

    /**
     * Regex triggering
     * [fr.ribesg.minecraft.purpur.event.PlayerQuitEvent].
     */
    val regexPlayerQuit: Regex

    /**
     * Regex triggering
     * [fr.ribesg.minecraft.purpur.event.PlayerChatEvent].
     */
    val regexPlayerChat: Regex

    /**
     * Regex triggering
     * [fr.ribesg.minecraft.purpur.event.PlayerCommandEvent].
     */
    val regexPlayerCommand: Regex

    /**
     * Properties
     */
    private val props: Properties

    /**
     * Load properties from resource file
     */
    init {
        props = Properties()
        props.load(ClassLoader.getSystemResourceAsStream("purpur.properties"))

        name = props["name"] as String
        version = props["version"] as String

        regexLine = (props["regex_Line"] as String).toRegex()
        regexCreateServerProperties = (props["regex_CreateServerProperties"] as String).toRegex()
        regexEulaRequiresAgreement = (props["regex_EulaRequiresAgreement"] as String).toRegex()
        regexServerReady = (props["regex_ServerReady"] as String).toRegex()
        regexPlayerJoin = (props["regex_PlayerJoin"] as String).toRegex()
        regexPlayerQuit = (props["regex_PlayerQuit"] as String).toRegex()
        regexPlayerChat = (props["regex_PlayerChat"] as String).toRegex()
        regexPlayerCommand = (props["regex_PlayerCommand"] as String).toRegex()
    }
}
