package com.shatun.townystorage;

import com.shatun.townystorage.objects.TownStorage;

import java.util.HashMap;

public class StorageUniverse {
    private static StorageUniverse instance;
    private static HashMap<String, TownStorage> storages;

    public static StorageUniverse getInstance(){
        if (instance == null){
            instance = new StorageUniverse();
        }
        return instance;
    }
    public void startPlugin(){
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
