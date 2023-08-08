package com.shatun.townystorage.commands;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Town;
import com.shatun.townystorage.StorageUniverse;
import com.shatun.townystorage.TownyStorage;
import com.shatun.townystorage.objects.TownStorage;
import com.shatun.townystorage.utils.Perms;
import com.shatun.townystorage.utils.Translation;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStorage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(Perms.COMMAND_STORAGE) && !sender.isOp()) {
            sender.sendMessage(Translation.of("NO_PERMISSIONS"));
            return true;
        }
        if (args.length == 0){
            Player p = (Player) sender;
            Town playerTown = TownyAPI.getInstance().getResident(p).getTownOrNull();
            if (playerTown == null) {
                p.sendMessage(Component.text(Translation.of("PLAYER_HAS_NOT_TOWN")));
                return true;
            }
            TownStorage storage = StorageUniverse.getInstance().getStorage(playerTown.getName());
            storage.updateStorageSavingState(true);
            p.openInventory(storage.getInventory());
            return true;
        }
        return false;
    }
}
