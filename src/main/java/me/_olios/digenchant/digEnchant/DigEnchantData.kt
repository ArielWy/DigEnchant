package me._olios.digenchant.digEnchant

import me._olios.digenchant.DigEnchant
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class DigEnchantData(private val plugin: DigEnchant, private val player: Player) {
    fun heldItem(item: ItemStack) {
        val itemMeta = item.itemMeta
    }
}