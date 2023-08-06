package com.shatun.townystorage;

import org.bukkit.plugin.java.JavaPlugin;

public final class TownyStorage extends JavaPlugin {

    @Override
    public void onEnable() {
        StorageUniverse.getInstance().startPlugin();
    }

    @Override
    public void onDisable() {
        StorageUniverse.getInstance().saveData();
    }
}
