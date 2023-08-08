package com.shatun.townystorage;

import com.shatun.townystorage.objects.Storage;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class TownyStorageAPI {
    public static TownyStorageAPI instance;
    public static TownyStorageAPI getInstance(){
        if (instance == null)
            instance = new TownyStorageAPI();
        return instance;
    }
    public boolean containsItems(String townName, ItemStack[] items){
        return StorageUniverse.getInstance().getStorage(townName).containsItems(items);
    }
    public boolean tryToGetItems(String townName, ItemStack[] items){
        return StorageUniverse.getInstance().getStorage(townName).tryToGetItems(items);
    }
    public Inventory getStorageInventory(String townName){
        return StorageUniverse.getInstance().getStorage(townName).getInventory();
    }
    public Storage getStorage(String townName){
        return StorageUniverse.getInstance().getStorage(townName);
    }
    public HashMap<String, Storage> getStorages(){
        return StorageUniverse.getInstance().getStorages();
    }

}
