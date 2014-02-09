package net.minecraftforge.groovy.event

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class RightClickEvent {
    EntityPlayer player
    ItemStack stack
    World world
    int x
    int y
    int z

    RightClickEvent(EntityPlayer player, ItemStack stack, World world, int x, int y, int z) {
        this.player = player
        this.stack = stack
        this.world = world
        this.x = x
        this.y = y
        this.z = z
    }

    RightClickEvent(EntityPlayer player, World world, int x, int y, int z) {
        this.player = player
        this.world = world
        this.x = x
        this.y = y
        this.z = z
    }
}