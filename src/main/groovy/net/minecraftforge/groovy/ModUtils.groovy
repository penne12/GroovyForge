package net.minecraftforge.groovy

import net.minecraft.block.Block
import net.minecraft.item.Item

class ModUtils {
    static int assignItemID() {
        def minimum = 3840
        def slots = Item.itemsList.size() - 1
        for (slot in minimum..slots) {
            if (Item.itemsList[slot] == null) {
                return slot
            }
        }
        return minimum
    }

    static int assignBlockID() {
        def minimum = 512
        def slots = Block.blocksList.size() - 1
        for (slot in minimum..slots) {
            if (Block.blocksList[slot] == null) {
                return slot
            }
        }
        return minimum
    }
}
