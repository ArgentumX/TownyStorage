package com.shatun.townystorage;

import com.shatun.townystorage.objects.TownStorage;
import com.shatun.townystorage.utils.FileUtil;
import com.shatun.townystorage.utils.Paths;
import com.shatun.townystorage.utils.Translation;
import org.bukkit.Bukkit;

import javax.annotation.Nullable;
import java.util.HashMap;

public class StorageUniverse {
    private static StorageUniverse instance;
    private HashMap<String, TownStorage> storages;
    private StorageSettings settings;
    public static StorageUniverse getInstance(){
        if (instance == null){
            instance = new StorageUniverse();
        }
        return instance;
    }
    public void startPlugin(){
        Translation.initialize();
        settings = DataManager.loadSettings();
        storages = DataManager.loadStorages();
        TownyHook.registerTownyCommands();
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
    public StorageSettings getSettings() { return settings; }
    public HashMap<String, TownStorage> getStorages() {
        return storages;
    }
    @Nullable
    public TownStorage getStorage(String townName){
        return storages.getOrDefault(townName, null);
    }
    public void onTownRename(String oldName, String newName){
        TownStorage storage = storages.get(oldName);
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
        TownStorage storage = new TownStorage();
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
