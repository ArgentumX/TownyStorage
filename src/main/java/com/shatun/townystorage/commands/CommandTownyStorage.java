package com.shatun.townystorage.commands;

import com.shatun.townystorage.TownyStorage;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandTownyStorage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
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
