package net.minecraftforge.groovy

import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.common.registry.LanguageRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item

class GroovyMod {

    Map<String, Block> blocks = [:]
    Map<String, Item> items = [:]
    GEventBus eventBus = new GEventBus()

    void registration() {
        blocks.each { entry ->
            GameRegistry.registerBlock(entry.value, "groovy.${entry.key}")
            LanguageRegistry.addName(entry.value, entry.key)
        }

        items.each { entry ->
            GameRegistry.registerItem(entry.value, "groovy.${entry.key}")
            LanguageRegistry.addName(entry.value, entry.key)
        }
    }
}
