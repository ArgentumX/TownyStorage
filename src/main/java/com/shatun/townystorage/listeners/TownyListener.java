package com.shatun.townystorage.listeners;


import com.palmergames.bukkit.towny.event.DeleteTownEvent;
import com.palmergames.bukkit.towny.event.PreNewTownEvent;
import com.palmergames.bukkit.towny.event.RenameTownEvent;
import com.shatun.townystorage.StorageUniverse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TownyListener implements Listener {

    @EventHandler
    public void OnTownCreate(PreNewTownEvent e){
        StorageUniverse.getInstance().onTownCreate(e.getTownName());
    }
    @EventHandler
    public void OnRenameTown(RenameTownEvent e){
        StorageUniverse.getInstance().onTownRename(e.getOldName(), e.getTown().getName());
    }
    @EventHandler
    public void OnDeleteTown(DeleteTownEvent e){
        StorageUniverse.getInstance().onTownDelete(e.getTownName());
    }
}
