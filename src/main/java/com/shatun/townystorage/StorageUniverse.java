package com.shatun.townystorage;

import com.shatun.townystorage.objects.Storage;
import com.shatun.townystorage.utils.FileUtil;
import com.shatun.townystorage.utils.Paths;
import com.shatun.townystorage.utils.Translation;
import org.bukkit.Bukkit;

import javax.annotation.Nullable;
import java.util.HashMap;

public class StorageUniverse {
    private static StorageUniverse instance;
    private HashMap<String, Storage> storages;
    private StorageSettings settings;
    public static StorageUniverse getInstance(){
        if (instance == null)
            instance = new StorageUniverse();
        return instance;
    }
    public void startPlugin(){
        Translation.initialize();
        settings = DataManager.loadSettings();
        storages = DataManager.loadStorages();
    }

    public void startTimers(){
        // Storages save timer.
        Bukkit.getScheduler().scheduleSyncRepeatingTask(TownyStorage.plugin, new Runnable() { @Override
        public void run() {
            savePluginData();
        }
        }, settings.storageAutosavePeriod, settings.storageAutosavePeriod);
    }
    public void savePluginData(){
        DataManager.saveStorages();
    }
    public void restartPlugin(){
        savePluginData();
        startPlugin();
    }
    public StorageSettings getSettings() { return settings; }
    public HashMap<String, Storage> getStorages() {
        return storages;
    }
    @Nullable
    public Storage getStorage(String townName){
        return storages.getOrDefault(townName, null);
    }
    public void onTownRename(String oldName, String newName){
        Storage storage = storages.get(oldName);
        storages.remove(oldName);
        storages.put(newName, storage);
        FileUtil.renameFile(Paths.DATA, oldName + ".yml", newName + ".yml");
    }
    public void onTownCreate(String townName){
        addTownStorage(townName);
    }
    public void onTownDelete(String townName){
        removeTownStorage(townName);
    }
    public void addTownStorage(String townName){
        Storage storage = new Storage();
        storages.put(townName, storage);
        DataManager.saveStorage(townName, storage);
    }
    public void removeTownStorage(String townName){
        storages.remove(townName);
        FileUtil.removeFile(Paths.DATA, townName+".yml");
    }
    public boolean hasStorageData(String townName){
        return storages.containsKey(townName);
    }
}
