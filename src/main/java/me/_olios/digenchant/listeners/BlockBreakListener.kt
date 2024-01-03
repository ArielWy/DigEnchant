package me._olios.digenchant.listeners

import me._olios.digenchant.DigEnchant
import me._olios.digenchant.digEnchant.DigAction
import me._olios.digenchant.digEnchant.DigEnchantData
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BlockBreakListener(private val plugin: DigEnchant): Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        if (event.block.hasMetadata("brokenByDigEnchant")) {
            // This block was broken by the plugin
            event.block.removeMetadata("brokenByDigEnchant", plugin)
            return
        }

        val player = event.player
        val itemInHand = player.inventory.itemInMainHand.type

        if (itemInHand != Material.NETHERITE_PICKAXE) return
        val block = event.block
        val blockFace = DigEnchantData.SharedData.lastBlockFace[player]
        val item = player.itemInUse
        val xpVal = event.expToDrop
        DigAction(plugin, player).whenMined(block, item, blockFace, 3)
    }
}