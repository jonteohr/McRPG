package me.condolent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Yell implements CommandExecutor {
	
	McRPG plugin;
	
	public Yell(McRPG instance) {
		plugin = instance;
	}
	
	public FileConfiguration getConfig() {
		return plugin.getConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("y")) {
			StringBuilder s = new StringBuilder();
			for(int i = 0; i < args.length; i++) {
				s.append(args[i]).append(" ");
			}
			String message = s.toString();
			
			if(!getConfig().getBoolean("admin_yell")) {
				if(args.length < 1) {
					p.sendMessage(ChatColor.YELLOW + "Usage: /y <message>");
				} else {
					Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " yells: " + message);
				}
			} else if(getConfig().getBoolean("admin_yell")) {
				if(p.hasPermission("mcrpg.admin")) {
					if(args.length < 1) {
						p.sendMessage(ChatColor.RED + "Usage: /y <message>");
					} else {
						Bukkit.broadcastMessage(ChatColor.RED + p.getName() + " yells: " + message);
					}
				} else {
					p.sendMessage(ChatColor.RED + "That command is set to only be availible to admins.");
				}
			}
		}
		
		return false;
	}

}
