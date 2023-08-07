package com.shatun.townystorage;

import org.bukkit.plugin.java.JavaPlugin;

public final class TownyStorage extends JavaPlugin {

    public static TownyStorage plugin;
    @Override
    public void onEnable() {
        plugin = this;
        StorageUniverse.getInstance().startPlugin();
    }

    @Override
    public void onDisable() {
        StorageUniverse.getInstance().saveData();
    }
}
