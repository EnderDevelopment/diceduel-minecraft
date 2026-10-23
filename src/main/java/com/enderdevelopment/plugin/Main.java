package com.enderdevelopment.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("DiceDuel plugin has been enabled!");
        getLogger().info("DiceDuel plugin enabled!");
        getServer().getPluginManager().registerEvents(new DiceRollListener(), this);
        this.getCommand("diceroll").setExecutor(new DiceRollCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
        getLogger().info("DiceDuel plugin disabled!");
    }
}