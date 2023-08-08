package com.shatun.townystorage;

import com.shatun.townystorage.enums.AccessMode;
import com.shatun.townystorage.objects.TownStorage;
import com.shatun.townystorage.utils.FileUtil;
import com.shatun.townystorage.utils.Paths;
import com.shatun.townystorage.utils.Translation;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Set;

public class DataManager {
    public static StorageSettings loadSettings(){
        StorageSettings settings = new StorageSettings();
        FileConfiguration fileConfiguration = FileUtil.getCongigFile(Paths.CONFIGS, "settings.yml");

        settings.accessMode = AccessMode.valueOf(fileConfiguration.getString("access_mode"));
        settings.defaultStorageSize = fileConfiguration.getInt("default_storage_size");

        return settings;
    }

    public static HashMap<String, TownStorage> loadStorages(){
        HashMap<String, TownStorage> storages = new HashMap<>();
        HashMap<String, YamlConfiguration> storagesFiles = FileUtil.getDataFilesConfigurations(Paths.DATA);
        for (String townName : storagesFiles.keySet()){
            YamlConfiguration fileConfig = storagesFiles.get(townName);
            // Loading items from town storage file.
            Inventory inventory = Bukkit.createInventory(null, fileConfig.getInt("warehouse.size"), Component.text(Translation.of("DEFAULT_STORAGE_NAME")));
            if(fileConfig.contains("storage.inventory")) {
                Set<String> keys = fileConfig.getConfigurationSection("storage.inventory").getKeys(false);
                for (String key : keys) {
                    inventory.setItem(Integer.valueOf(key), fileConfig.getItemStack("warehouse.inventory." + key));
                }
            }
            TownStorage storage = new TownStorage(inventory);
            storages.put(townName, storage);
        }
        return storages;
    }
}
