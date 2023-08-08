package com.shatun.townystorage.objects;

import com.shatun.townystorage.StorageSettings;
import com.shatun.townystorage.StorageUniverse;
import com.shatun.townystorage.utils.Translation;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TownStorage {
    private Inventory inventory;
    private boolean needsBeSaved = false;

    public TownStorage(Inventory inventory) {
        this.inventory = inventory;
    }

    public TownStorage() {
        inventory = Bukkit.createInventory(null, StorageUniverse.getInstance().getSettings().defaultStorageSize, Component.text(Translation.of("DEFAULT_STORAGE_NAME", false)));
    }

    public boolean containsItems(ItemStack[] items) {
        for (ItemStack item : items) {
            if (!inventory.contains(item.getType(), item.getAmount())) {
                return false;
            }
        }
        return true;
    }

    public boolean tryToGetItems(ItemStack[] items) {
        if (!containsItems(items))
            return false;
        updateStorageSavingState(true);
        for (ItemStack item : items) {
            inventory.removeItem(item);
        }
        return true;
    }

    public ItemStack[] getItems() {
        return inventory.getContents();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void updateStorageSavingState(boolean newState) {
        needsBeSaved = newState;
    }

    public boolean needsBeSaved() {
        return needsBeSaved;
    }

}
