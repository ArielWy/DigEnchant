package me._olios.digenchant.digEnchant

import me._olios.digenchant.DigEnchant
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


class DigAction(private val plugin: DigEnchant, private val player: Player) {


    fun whenMined(centralBlock: Block, item: ItemStack?, blockFace: BlockFace?, size: Int) {

        player.sendMessage("whenMined")

        val range = (size - 1) / 2

        when (blockFace) {
            BlockFace.UP, BlockFace.DOWN -> breakUpDown(centralBlock, range)
            BlockFace.NORTH, BlockFace.SOUTH -> breakNorthSouth(centralBlock, range)
            BlockFace.EAST, BlockFace.WEST -> breakEastWest(centralBlock, range)
            else -> {}
        }
    }

    private fun breakUpDown(centralBlock: Block, range: Int) {

        player.sendMessage("breakUpDown")

        // gets the x,y,z coordination
        val centerX = centralBlock.x
        val centerY = centralBlock.y
        val centerZ = centralBlock.z

        // create a loop that break all the blocks
        for (dx in -range..range) {
            for (dz in -range..range) {
                val blockToBreak = centralBlock.world.getBlockAt(centerX + dx, centerY, centerZ + dz)
                blockToBreak.breakNaturally()
            }
        }
    }

    private fun breakNorthSouth(centralBlock: Block, range: Int) {

        player.sendMessage("breakNorthSouth")

        // gets the x,y,z coordination
        val centerX = centralBlock.x
        val centerY = centralBlock.y
        val centerZ = centralBlock.z

        // create a loop that break all the blocks
        for (dx in -range..range) {
            for (dy in -range..range) {
                val blockToBreak = centralBlock.world.getBlockAt(centerX + dx, centerY + dy, centerZ)
                blockToBreak.breakNaturally()
            }
        }
    }

    private fun breakEastWest(centralBlock: Block, range: Int) {

        player.sendMessage("breakEastWest")

        // gets the x,y,z coordination
        val centerX = centralBlock.x
        val centerY = centralBlock.y
        val centerZ = centralBlock.z

        // create a loop that break all the blocks
        for (dy in -range..range) {
            for (dz in -range..range) {
                val blockToBreak = centralBlock.world.getBlockAt(centerX, centerY + dy, centerZ + dz)
                blockToBreak.breakNaturally()
            }
        }
    }
}