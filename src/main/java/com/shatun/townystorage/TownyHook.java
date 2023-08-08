package com.shatun.townystorage;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyCommandAddonAPI;
import com.palmergames.bukkit.towny.object.AddonCommand;
import com.palmergames.bukkit.towny.object.Town;
import com.shatun.townystorage.commands.CommandStorage;

import java.util.List;

public class TownyHook {
    public static void registerTownyCommands(){
        // /t storage.
        AddonCommand warehouseCommand = new AddonCommand(TownyCommandAddonAPI.CommandType.TOWN, "storage", new CommandStorage());
        TownyCommandAddonAPI.addSubCommand(warehouseCommand);
    }
    public static void createStoragesForOldTowns(){
        List<Town> towns = TownyAPI.getInstance().getTowns();
        for (Town town : towns){
            if (!StorageUniverse.getInstance().hasStorageData(town.getName())){
                StorageUniverse.getInstance().addTownStorage(town.getName());
            }
        }
    }
}