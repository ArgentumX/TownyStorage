package com.shatun.townystorage;

import com.shatun.townystorage.enums.AccessMode;
import com.shatun.townystorage.objects.TownStorage;
import com.shatun.townystorage.utils.FileUtil;
import com.shatun.townystorage.utils.Paths;
import com.shatun.townystorage.utils.Translation;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Set;

public class DataManager {
    public static StorageSettings loadSettings(){
        StorageSettings settings = new StorageSettings();
        FileConfiguration fileConfiguration = FileUtil.getConfigFile(Paths.CONFIGS, "settings.yml");

        settings.accessMode = AccessMode.valueOf(fileConfiguration.getString(Paths.YML_SETTINGS_ACCESS_MODE));
        settings.defaultStorageSize = fileConfiguration.getInt(Paths.YML_SETTINGS_DEFAULT_SIZE);
        settings.storageAutosavePeriod = fileConfiguration.getInt(Paths.YML_SETTINGS_AUTOSAVE_PERIOD);

        return settings;
    }

    public static HashMap<String, TownStorage> loadStorages(){
        HashMap<String, TownStorage> storages = new HashMap<>();
        HashMap<String, YamlConfiguration> storagesFiles = FileUtil.getDataFilesConfigurations(Paths.DATA);
        for (String townName : storagesFiles.keySet()){
            YamlConfiguration fileConfig = storagesFiles.get(townName);
            // Loading items from town storage file.
            Inventory inventory = Bukkit.createInventory(null, fileConfig.getInt(Paths.YML_STORAGE_SIZE), Component.text(Translation.of(Paths.TRANSLATION_DEFAULT_STORAGE_NAME)));
            if(fileConfig.contains(Paths.YML_STORAGE_INVENTORY)) {
                Set<String> keys = fileConfig.getConfigurationSection(Paths.YML_STORAGE_INVENTORY).getKeys(false);
                for (String key : keys) {
                    inventory.setItem(Integer.valueOf(key), fileConfig.getItemStack(Paths.YML_STORAGE_INVENTORY + "." + key));
                }
            }
            TownStorage storage = new TownStorage(inventory);
            storages.put(townName, storage);
        }
        return storages;
    }

    public static void saveStorages(){
        HashMap<String, TownStorage> dataToSave = StorageUniverse.getInstance().getStorages();
        for (String townName : dataToSave.keySet()){
            TownStorage townStorage = dataToSave.get(townName);
            if (townStorage.needsBeSaved())
                saveStorage(townName, townStorage);
        }
    }
    public static void saveStorage(String townName, TownStorage townStorage){
        FileConfiguration fileConfig = new YamlConfiguration();
        fileConfig.set(Paths.YML_STORAGE_SIZE, townStorage.getInventory().getSize());
        // Saving items from town storage.
        ItemStack[] items = townStorage.getItems();
        for(int i = 0; i < items.length; i++){
            fileConfig.set(Paths.YML_STORAGE_INVENTORY + "." + i, items[i]);
        }
        FileUtil.saveDataFile(fileConfig, Paths.DATA, townName + ".yml");
    }
}
