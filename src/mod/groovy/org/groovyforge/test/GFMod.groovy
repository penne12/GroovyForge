package org.groovyforge.test

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import net.minecraft.entity.player.EntityPlayer
import net.minecraftforge.groovy.GroovyMod
import net.minecraftforge.groovy.MCEventRegister

@Mod(modid = "GFMod", name = "GroovyForge Test Mod", version = "1.0")
class GFMod extends GroovyMod {

    static Set<String> playerList = []

    @Mod.EventHandler
    void init(FMLInitializationEvent initializationEvent) {
        // Enable our Event Bus to use Join and Quit Events
        MCEventRegister.joinQuitEvents(eventBus)

        // Append to Player Join List
        eventBus.on("player.join") { event ->
            playerList.add((event["player"] as EntityPlayer).displayName)
        }

        // Load Localizations
        loadLanguageScript("groovyforge/translations.groovy")

        // Testing Item
        items["Player List"] = new PlayerListItem()

        // Complete Registration
        registration()
    }
}
