package com.enderdevelopment.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("CustomChat26 has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("CustomChat26 has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("customchat")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Usage: /customchat <subcommand>");
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("customchat.admin")) {
                    reloadConfig();
                    sender.sendMessage(ChatColor.GREEN + "CustomChat26 configuration reloaded!");
                } else {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                }
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        // Format the message with color codes
        String formattedMessage = ChatColor.translateAlternateColorCodes('&', message);

        // Set the format for the chat message
        event.setFormat(ChatColor.GRAY + "[%s] " + ChatColor.RESET + "%s: %s",
            player.getWorld().getName(),
            player.getDisplayName(),
            formattedMessage);
    }
}