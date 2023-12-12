package me._olios.digenchant.listeners

import me._olios.digenchant.DigEnchant
import me._olios.digenchant.digEnchant.DigEnchantData
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDamageEvent

class BlockDamageListener(private val plugin: DigEnchant): Listener {

    @EventHandler
    fun onDamageBlock(event: BlockDamageEvent) {
        val player = event.player
        val itemInHand = player.inventory.itemInMainHand.type

        if (itemInHand != Material.NETHERITE_PICKAXE) return
        val blockFace = event.blockFace
        DigEnchantData.SharedData.lastBlockFace[player] = blockFace
        player.sendMessage("blockFace = " + DigEnchantData.SharedData.lastBlockFace[player])
    }
}