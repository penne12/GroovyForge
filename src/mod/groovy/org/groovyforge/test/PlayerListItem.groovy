package org.groovyforge.test

import net.minecraft.creativetab.CreativeTabs
import net.minecraftforge.groovy.GroovyItem
import net.minecraftforge.groovy.event.RightClickEvent

class PlayerListItem extends GroovyItem {
    PlayerListItem() {
        setCreativeTab(CreativeTabs.tabTransport)
        onRightClick { RightClickEvent event ->
            event.player.addChatMessage(GFMod.playerList.join(", "))
            return true
        }
    }
}
