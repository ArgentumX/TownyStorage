package com.shatun.townystorage;

import com.shatun.townystorage.commands.CommandTownyStorage;
import com.shatun.townystorage.enums.AccessMode;
import com.shatun.townystorage.listeners.TownyListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class TownyStorage extends JavaPlugin {

    public static TownyStorage plugin;
    @Override
    public void onEnable() {
        plugin = this;
        StorageUniverse.getInstance().startPlugin();
        StorageUniverse.getInstance().startTimers();
        Bukkit.getPluginManager().registerEvents(new TownyListener(), this);
        plugin.getCommand("townystorage").setExecutor(new CommandTownyStorage());
        TownyHook.createStoragesForOldTowns();
        TownyHook.registerTownyCommands();
    }

    @Override
    public void onDisable() {
        StorageUniverse.getInstance().savePluginData();
    }
}
