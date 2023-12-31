package me._olios.digenchant.digEnchant

import me._olios.digenchant.DigEnchant
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.ExperienceOrb
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import kotlin.random.Random


class DigAction(private val plugin: DigEnchant, private val player: Player) {

    val blockToXpMap = mapOf(
        "coal_ore" to Pair(0, 2),
        "nether_gold_ore" to Pair(0, 1),
        "diamond_ore" to Pair(3, 7),
        "emerald_ore" to Pair(3, 7),
        "lapis_ore" to Pair(2, 5),
        "nether_quartz_ore" to Pair(2, 5),
        "redstone_ore" to Pair(1, 5),
        "spawner" to Pair(15, 43),
        "sculk" to Pair(1, 1),
        "sculk_catalyst" to Pair(5, 5),
        "sculk_shrieker" to Pair(5, 5),
        "sculk_sensor" to Pair(5, 5)
    )

    fun whenMined(centralBlock: Block, item: ItemStack?, xpVal: Int, blockFace: BlockFace?, size: Int) {

        player.sendMessage("whenMined")

        val range = (size - 1) / 2

        when (blockFace) {
            BlockFace.UP, BlockFace.DOWN -> breakUpDown(centralBlock, range, item, xpVal)
            BlockFace.NORTH, BlockFace.SOUTH -> breakNorthSouth(centralBlock, range, item, xpVal)
            BlockFace.EAST, BlockFace.WEST -> breakEastWest(centralBlock, range, item, xpVal)
            else -> {}
        }
    }

    private fun breakUpDown(centralBlock: Block, range: Int, item: ItemStack?, xpVal: Int) {

        player.sendMessage("breakUpDown")
        player.sendMessage(xpVal.toString())

        // gets the x,y,z coordination
        val centerX = centralBlock.x
        val centerY = centralBlock.y
        val centerZ = centralBlock.z

        // create a loop that break all the blocks
        for (dx in -range..range) {
            for (dz in -range..range) {
                val blockToBreak = centralBlock.world.getBlockAt(centerX + dx, centerY, centerZ + dz)
                if (blockToBreak == centralBlock) continue // doesn't break the center block
                blockToBreak.breakNaturally(item)
                val xpOrb = blockToBreak.world.spawn(blockToBreak.location, ExperienceOrb::class.java)
                val blockType = blockToBreak.type.toString() // Get the block type as a string
                val xpRange = blockToXpMap[blockType] // Get the XP range for this block type
                val xpVal = if (xpRange != null) Random.nextInt(xpRange.first, xpRange.second + 1) else 0
                xpVal.also { xpOrb.experience = it }
            }
        }
    }

    private fun breakNorthSouth(centralBlock: Block, range: Int, item: ItemStack?, xpVal: Int) {

        player.sendMessage("breakNorthSouth")
        player.sendMessage(xpVal.toString())

        // gets the x,y,z coordination
        val centerX = centralBlock.x
        val centerY = centralBlock.y
        val centerZ = centralBlock.z

        // create a loop that break all the blocks
        for (dx in -range..range) {
            for (dy in -range..range) {
                val blockToBreak = centralBlock.world.getBlockAt(centerX + dx, centerY + dy, centerZ)
                if (blockToBreak == centralBlock) continue // doesn't break the center block
                blockToBreak.breakNaturally(item)
                val xpOrb = blockToBreak.world.spawn(blockToBreak.location, ExperienceOrb::class.java)
                val blockType = blockToBreak.type.toString() // Get the block type as a string
                val xpRange = blockToXpMap[blockType] // Get the XP range for this block type
                val xpVal = if (xpRange != null) Random.nextInt(xpRange.first, xpRange.second + 1) else 0
                xpVal.also { xpOrb.experience = it }
            }
        }
    }

    private fun breakEastWest(centralBlock: Block, range: Int, item: ItemStack?, xpVal: Int) {

        player.sendMessage("breakEastWest")
        player.sendMessage(xpVal.toString())

        // gets the x,y,z coordination
        val centerX = centralBlock.x
        val centerY = centralBlock.y
        val centerZ = centralBlock.z

        // create a loop that break all the blocks
        for (dy in -range..range) {
            for (dz in -range..range) {
                val blockToBreak = centralBlock.world.getBlockAt(centerX, centerY + dy, centerZ + dz)
                if (blockToBreak == centralBlock) continue // doesn't break the center block
                blockToBreak.breakNaturally(item)
                val xpOrb = blockToBreak.world.spawn(blockToBreak.location, ExperienceOrb::class.java)
                val blockType = blockToBreak.type.toString() // Get the block type as a string
                val xpRange = blockToXpMap[blockType] // Get the XP range for this block type
                val xpVal = if (xpRange != null) Random.nextInt(xpRange.first, xpRange.second + 1) else 0
                xpVal.also { xpOrb.experience = it }
            }
        }
    }
}