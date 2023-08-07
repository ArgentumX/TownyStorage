package com.shatun.townystorage;

import com.shatun.townystorage.objects.TownStorage;
import com.shatun.townystorage.utils.Translation;

import java.util.HashMap;

public class StorageUniverse {
    private static StorageUniverse instance;
    private HashMap<String, TownStorage> storages;
    public static StorageUniverse getInstance(){
        if (instance == null){
            instance = new StorageUniverse();
        }
        return instance;
    }
    public void startPlugin(){
        Translation.initialize();
        // setting loading
        // database loading
        TownyCommandHook.registerTownyCommands();
    }
    public void saveData(){
        // saving data
    }
    public HashMap<String, TownStorage> getStorages() {
        return storages;
    }
}
