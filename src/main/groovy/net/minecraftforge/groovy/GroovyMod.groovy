package net.minecraftforge.groovy

import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.common.registry.LanguageRegistry
import net.minecraft.block.Block
import net.minecraft.item.Item
import org.codehaus.groovy.control.CompilerConfiguration

abstract class GroovyMod {

    private GroovyShell groovyShell = {
        def compilerConfig = new CompilerConfiguration()
        compilerConfig.scriptBaseClass = Translator.class.name
        return new GroovyShell(compilerConfig)
    }()

    Map<String, Block> blocks = [:]
    Map<String, Item> items = [:]
    GEventBus eventBus = new GEventBus()

    void registration() {
        blocks.each { entry ->
            GameRegistry.registerBlock(entry.value, "${entry.key}")
            LanguageRegistry.addName(entry.value, entry.key)
        }

        items.each { entry ->
            GameRegistry.registerItem(entry.value, entry.key)
            LanguageRegistry.addName(entry.value, entry.key)
        }
    }

    void loadLanguageScript(String resourcePath) {
        def scriptURL = getClass().classLoader.getResource(resourcePath)

        if (scriptURL == null)
            throw new RuntimeException("Unable to find Language Script '${resourcePath}'")

        def scriptText = scriptURL.getText()

        try {
            groovyShell.parse(scriptText).run()
        } catch (e) {
            throw new RuntimeException("Language Script '${resourcePath}' failed to load", e)
        }
    }
}
