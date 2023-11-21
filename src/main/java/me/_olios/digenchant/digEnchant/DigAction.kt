package me._olios.digenchant.digEnchant

import me._olios.digenchant.DigEnchant
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


class DigAction(private val plugin: DigEnchant, private val player: Player) {
    fun whenMined(centralBlock: Block, item: ItemStack?, blockFace: BlockFace, size: Int) {
        val range = (size - 1) / 2

        for (dx in -range..range) {
            for (dy in -range..range) {
                for (dz in -range..range) {
                    val blockToDestroy = when (blockFace) {
                        BlockFace.UP, BlockFace.DOWN -> centralBlock.getRelative(dx, dy, 0)
                        BlockFace.NORTH, BlockFace.SOUTH -> centralBlock.getRelative(dx, 0, dz)
                        BlockFace.EAST, BlockFace.WEST -> centralBlock.getRelative(0, dy, dz)
                        else -> centralBlock
                    }
                    blockToDestroy.breakNaturally(item)
                }
            }
        }
    }



    fun getBlocks(block: Block): List<Block> {
        val faces = listOf(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.SELF)
        return faces.flatMap { face ->
            faces.map { block.getRelative(face).getRelative(it) }
        }
    }

    private fun blockFace(): BlockFace {
        // Get the player's direction
        return when (((player.location.yaw - 90) % 360).toDouble().let { if (it < 0) it + 360 else it }) {
            in 0.0..45.0, in 315.0..360.0 -> BlockFace.WEST
            in 45.0..135.0 -> BlockFace.NORTH
            in 135.0..225.0 -> BlockFace.EAST
            in 225.0..315.0 -> BlockFace.SOUTH
            else -> BlockFace.SOUTH
        }
    }
}