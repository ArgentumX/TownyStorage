package com.shatun.townystorage.commands;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Town;
import com.shatun.townystorage.StorageUniverse;
import com.shatun.townystorage.TownyStorage;
import com.shatun.townystorage.enums.AccessMode;
import com.shatun.townystorage.objects.Storage;
import com.shatun.townystorage.utils.Perms;
import com.shatun.townystorage.utils.Translation;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandAdminStorage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(Perms.ADMIN) && !sender.isOp()) {
            sender.sendMessage(Translation.of("NO_PERMISSIONS"));
            return true;
        }
        if (args.length == 1){
            if(args[0].equals("reload")){
                TownyStorage.plugin.getLogger().info("Plugin reloaded");
                StorageUniverse.getInstance().restartPlugin();
                return true;
            } else if (args[0].equals("about")) {
                if (sender instanceof Player){
                    Player p = (Player) sender;
                    TextComponent message = new TextComponent("§7(§ehttps://github.com/Comissar1917/TownyStorage/tree/master§7)");
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/Comissar1917/TownyStorage/tree/master"));
                    message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("source code").create()));
                    p.sendMessage("\n§lAuthor: §3Shatun\n§f§lversion: §e" + TownyStorage.plugin.getDescription().getVersion());
                    p.sendMessage(message);
                    p.sendMessage("");
                }
                return true;
            }
        }
        else if(args.length == 2){
            if(args[0].equals("open")){
                if (!(sender instanceof Player))
                    return false;
                Player p = (Player) sender;
                Storage storage = StorageUniverse.getInstance().getStorage(args[1]);
                if (storage == null){
                    p.sendMessage(Translation.of("NO_TOWN"));
                    return true;
                }
                storage.updateStorageSavingState(true);
                p.openInventory(storage.getInventory());
                return true;
            }
        }
        return false;
    }
}
