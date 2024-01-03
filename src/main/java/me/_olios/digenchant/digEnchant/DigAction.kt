package me._olios.digenchant.digEnchant

import me._olios.digenchant.DigEnchant
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.*
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue
import kotlin.random.Random


class DigAction(private val plugin: DigEnchant, private val player: Player) {

    private val blockToXpMap = mapOf(
        Material.COAL_ORE to Pair(0, 2),
        Material.NETHER_GOLD_ORE to Pair(0, 1),
        Material.DIAMOND_ORE to Pair(3, 7),
        Material.EMERALD_ORE to Pair(3, 7),
        Material.LAPIS_ORE to Pair(2, 5),
        Material.NETHER_QUARTZ_ORE to Pair(2, 5),
        Material.REDSTONE_ORE to Pair(1, 5),
        Material.SPAWNER to Pair(15, 43),
        Material.SCULK to Pair(1, 1),
        Material.SCULK_CATALYST to Pair(5, 5),
        Material.SCULK_SHRIEKER to Pair(5, 5),
        Material.SCULK_SENSOR to Pair(5, 5)
    )

    fun whenMined(centralBlock: Block, item: ItemStack?, blockFace: BlockFace?, size: Int) {

        player.sendMessage("whenMined")

        val range = (size - 1) / 2

        when (blockFace) {
            BlockFace.UP, BlockFace.DOWN -> breakUpDown(centralBlock, range, item)
            BlockFace.NORTH, BlockFace.SOUTH -> breakNorthSouth(centralBlock, range, item)
            BlockFace.EAST, BlockFace.WEST -> breakEastWest(centralBlock, range, item)
            else -> {}
        }
    }

    private fun breakUpDown(centralBlock: Block, range: Int, item: ItemStack?) {

        player.sendMessage("breakUpDown")

        // gets the x,y,z coordination
        val centerX = centralBlock.x
        val centerY = centralBlock.y
        val centerZ = centralBlock.z

        // create a loop that break all the blocks
        for (dx in -range..range) {
            for (dz in -range..range) {
                val blockToBreak = centralBlock.world.getBlockAt(centerX + dx, centerY, centerZ + dz)

                val blockType = blockToBreak.type // Get the block type
                val xpRange = blockToXpMap[blockType] // Get the XP range for this block type
                val experience = if (xpRange != null) Random.nextInt(xpRange.first, xpRange.second + 1) else 0

                // Check if the block has metadata
                if (blockToBreak.hasMetadata("brokenByDigEnchant")) return
                blockToBreak.setMetadata("brokenByDigEnchant", FixedMetadataValue(plugin, true)) // Add metadata to the block
                // Simulate a player breaking the block
                val blockBreakEvent = BlockBreakEvent(blockToBreak, player)
                Bukkit.getPluginManager().callEvent(blockBreakEvent)
                if (!blockBreakEvent.isCancelled) {
                    blockToBreak.breakNaturally(player.inventory.itemInMainHand) // Break the block with the player's main hand item
                }

                val world = blockToBreak.world
                val location = blockToBreak.location
                val orb = world.spawn(location, ExperienceOrb::class.java)
                orb.experience = experience

                player.sendMessage("Broke block: ${blockToBreak.type}, XP: $experience")
            }
        }
    }

    private fun breakNorthSouth(centralBlock: Block, range: Int, item: ItemStack?) {

        player.sendMessage("breakNorthSouth")

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
                val blockType = blockToBreak.type // Get the block type
                val xpRange = blockToXpMap[blockType] // Get the XP range for this block type
                val xpVal = if (xpRange != null) Random.nextInt(xpRange.first, xpRange.second + 1) else 0
                xpVal.also { xpOrb.experience = it }
            }
        }
    }

    private fun breakEastWest(centralBlock: Block, range: Int, item: ItemStack?) {

        player.sendMessage("breakEastWest")

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
                val blockType = blockToBreak.type // Get the block type
                val xpRange = blockToXpMap[blockType] // Get the XP range for this block type
                val xpVal = if (xpRange != null) Random.nextInt(xpRange.first, xpRange.second + 1) else 0
                xpVal.also { xpOrb.experience = it }
            }
        }
    }
}