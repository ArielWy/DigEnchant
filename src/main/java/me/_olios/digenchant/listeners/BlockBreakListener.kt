package me._olios.digenchant.listeners

import me._olios.digenchant.DigEnchant
import me._olios.digenchant.digEnchant.DigAction
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BlockBreakListener(private val plugin: DigEnchant): Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val player = event.player
        val itemInHand = player.inventory.itemInMainHand.type

        if (itemInHand != Material.NETHERITE_PICKAXE) return
        val block = event.block
        val blockFace = player.facing
        val item = player.itemInUse
        DigAction(plugin, player).whenMined(block, item, blockFace, 3)
    }
}