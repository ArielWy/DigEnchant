package me._olios.digenchant

import me._olios.digenchant.listeners.BlockBreakListener
import me._olios.digenchant.listeners.BlockDamageListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class DigEnchant : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        registerCommands()
        registerListeners()
    }

    private fun registerCommands() {
        getCommand("dig")?.setExecutor(AddEnchantCommand(this))
    }

    private fun registerListeners() {
        Bukkit.getServer().pluginManager.registerEvents(BlockBreakListener(this), this)
        Bukkit.getServer().pluginManager.registerEvents(BlockDamageListener(this), this)
    }
        override fun onDisable() {
        // Plugin shutdown logic
    }
}