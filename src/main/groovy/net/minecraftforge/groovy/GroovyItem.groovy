package net.minecraftforge.groovy

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraftforge.groovy.event.RightClickEvent

class GroovyItem extends Item {

    private List<Closure<Boolean>> rightClickHandlers = []

    GroovyItem() {
        super(ModUtils.assignItemID())
    }

    GroovyItem(int itemID) {
        super(itemID)
    }

    @Override
    boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int a, float fj, float fa, float fm) {
        if (!world.isRemote) {
            rightClickHandlers*.call(new RightClickEvent(player, stack, world, x, y, z)).contains(true)
        } else {
            false
        }
    }

    def onRightClick(Closure<Boolean> handler) {
        rightClickHandlers += handler
    }
}
