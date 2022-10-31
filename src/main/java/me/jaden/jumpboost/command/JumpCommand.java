package me.jaden.jumpboost.command;

import me.jaden.jumpboost.inventory.impl.JumpMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JumpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to execute this command!");
            return true;
        }

        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("menu")) {
                new JumpMenu((Player) sender).open();
                return true;
            }
        }
        return false;
    }
}
