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
		
		//List<String> balance = getPlayerCurrency().getStringList("player_balances");
		//getPlayerCurrency().set("player_balances", balance);
		//plugin.savePlayerCurrency();
		
		if(cmd.getName().equalsIgnoreCase("balance")) {

			if(!getPlayerCurrency().contains(p.getUniqueId().toString())) {
				p.sendMessage(ChatColor.RED + "You were not registered!");
				p.sendMessage(ChatColor.GREEN + "§oAdding your UUID to the log... Do /balance again to see your amount of gold.");
				
				getPlayerCurrency().set(p.getUniqueId().toString(), 0);
				plugin.savePlayerCurrency();
				
			}
			
			else if(getPlayerCurrency().contains(p.getUniqueId().toString())) {
				p.sendMessage(ChatColor.GOLD + "You have: §l" + getPlayerCurrency().getString(p.getUniqueId().toString()) + " gold.");
			}
			
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("sell")) {
			
			if(args.length < 1) {
				p.sendMessage(ChatColor.RED + "Invalid arguments!");
			}
			
			if(args.length == 1) {
				p.sendMessage("hi");
			}
			
		}
		
		
		
		return false;
	}

}
