package me._olios.digenchant

import me._olios.digenchant.digEnchant.DigEnchantData
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AddEnchantCommand(private val plugin: DigEnchant): CommandExecutor {
    override fun onCommand(sender: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if (sender !is Player) return false
        val itemInHand = sender.inventory.itemInMainHand.type
        if (!itemInHand.isItem) return false
        val itemStack = sender.inventory.itemInMainHand
        DigEnchantData(plugin, sender).heldItem(itemStack)
        return true
    }
}