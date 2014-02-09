package net.minecraftforge.groovy

import cpw.mods.fml.common.registry.LanguageRegistry

abstract class Translator extends Script {

    @Override
    Object invokeMethod(String name, Object args) {
        def translations = ((args as Object[])[0] as Map<String, String>)
        translations.each { entry ->
            LanguageRegistry.instance().addStringLocalization(name, entry.key, entry.value)
        }
    }
}
