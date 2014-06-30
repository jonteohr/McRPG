package me.condolent;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Currency implements CommandExecutor {
	
	McRPG plugin;
	
	public Currency(McRPG instance) {
		plugin = instance;
	}
	
	public FileConfiguration getPlayerCurrency() {
		return plugin.getPlayerCurrency();
	}
	
	public FileConfiguration getConfig() {
		return plugin.getConfig();
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		String UUID = p.getUniqueId().toString();
		
		if(cmd.getName().equalsIgnoreCase("balance")) {
			
			if(getPlayerCurrency().contains(UUID)) {
				p.sendMessage(ChatColor.GOLD + "You have: §l" + getPlayerCurrency().getString(UUID) + " gold.");
			}
			
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("sell")) {
			
			if(args.length < 1) {
				p.sendMessage(ChatColor.RED + "Invalid arguments!");
			}
			
			if(args.length == 1) {
				p.sendMessage(ChatColor.RED + "§oComing soon...");
			}
			
		}
		
		
		
		return false;
	}

}
