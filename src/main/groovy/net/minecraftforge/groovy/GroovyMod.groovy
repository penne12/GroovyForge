package net.minecraftforge.groovy

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.common.registry.LanguageRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item

class GroovyMod {
    Map<String, Block> blocks = [:]
    Map<String, Item> items = [:]

    @Mod.EventHandler
    void initialize(FMLInitializationEvent event) {
        blocks.each {
            GameRegistry.registerBlock(it.value, "groovy.${it.key}")
            LanguageRegistry.addName(it.value, it.key)
        }

        items.each {
            GameRegistry.registerItem(it.value, "groovy.${it.key}")
            LanguageRegistry.addName(it.value, it.key)
        }
    }
}
