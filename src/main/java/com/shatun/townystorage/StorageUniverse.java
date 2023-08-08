package com.shatun.townystorage;

import com.shatun.townystorage.objects.TownStorage;
import com.shatun.townystorage.utils.Translation;

import javax.xml.crypto.Data;
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
        TownyCommandHook.registerTownyCommands();
    }
    public void saveData(){
        // saving data
    }
    public StorageSettings getSettings() { return settings; }
    public HashMap<String, TownStorage> getStorages() {
        return storages;
    }
}
