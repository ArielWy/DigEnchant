package me._olios.digenchant.digEnchant

import me._olios.digenchant.DigEnchant
import org.bukkit.NamespacedKey
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class DigEnchantData(private val plugin: DigEnchant, private val player: Player) {
    fun heldItem(item: ItemStack, enchantLevel: Int) {
        val itemMeta = item.itemMeta
        val key = NamespacedKey(plugin, "enchantment")
        itemMeta.persistentDataContainer.set(key, PersistentDataType.INTEGER, enchantLevel)
        item.itemMeta = itemMeta
    }

    object SharedData {
        val lastBlockFace = HashMap<Player, BlockFace>()
    }

}