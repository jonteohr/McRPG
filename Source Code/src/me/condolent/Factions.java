package me.condolent;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Factions implements CommandExecutor {
	
	McRPG plugin;
	
	public Factions(McRPG instance) {
		plugin = instance;
	}
	
	public FileConfiguration getFactionsLog() {
		return plugin.getPlayerFactions();
	}
	
	public FileConfiguration getPlayerCurrency() {
		return plugin.getPlayerCurrency();
	}
	
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		// LOGGING THE PLAYER FOR CHOOSING FACTION
		List<String> registered_players = getFactionsLog().getStringList("Registered_Players");
		List<String> alliance = getFactionsLog().getStringList("Alliance");
		List<String> horde = getFactionsLog().getStringList("Horde");
		getFactionsLog().set("Registered_Players", registered_players);
		getFactionsLog().set("Horde", horde);
		getFactionsLog().set("Alliance", alliance);
		plugin.savePlayerFactions();
		
		
		if(cmd.getName().equalsIgnoreCase("faction")) {
			
			if(args.length < 1) {
				p.sendMessage(ChatColor.RED + "Invalid arguments!");
				p.sendMessage(ChatColor.RED + "/faction <alliance/horde>");
			} else if(args.length == 1) {

				if(getFactionsLog().getStringList("Registered_Players").contains(p.getUniqueId().toString())) {
					
					p.sendMessage(ChatColor.RED + "You've already chosen your faction!");
					
				}
				
				if(!getFactionsLog().getStringList("Registered_Players").contains(p.getUniqueId().toString())) {
					if(args[0].equalsIgnoreCase("alliance")) {
						p.sendMessage(ChatColor.YELLOW + "You've chosen to join the " + ChatColor.AQUA + "§lAlliance" + ChatColor.YELLOW + ".");
						p.playSound(p.getLocation(), Sound.ENTITY_HORSE_BREATHE, 1, 1);
						alliance.add(p.getUniqueId().toString());
						registered_players.add(p.getUniqueId().toString());
						plugin.savePlayerFactions();
					}
				
					if(args[0].equalsIgnoreCase("horde")) {
					
						p.sendMessage(ChatColor.YELLOW + "You've chosen to join the " + ChatColor.RED + "§lHorde" + ChatColor.YELLOW + ".");
						p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1, 1);
						horde.add(p.getUniqueId().toString());
						registered_players.add(p.getUniqueId().toString());
						plugin.savePlayerFactions();
						
					}
				}
			} if(args.length == 2) {
				
				if(args[0].equalsIgnoreCase("deluser")) {
					if(p.hasPermission("mcrpg.admin")) {
						@SuppressWarnings("deprecation")
						Player target = Bukkit.getServer().getPlayerExact(args[1]);
						if(target == null) {
							p.sendMessage(ChatColor.RED + "Player " + args[1] + " not found.");
							return true;
						}
						if(registered_players.contains(target.getUniqueId().toString())) {
							
							horde.remove(target.getUniqueId().toString());
							registered_players.remove(target.getUniqueId().toString());
							alliance.remove(target.getUniqueId().toString());
							p.sendMessage(ChatColor.YELLOW + target.getName() + " has been removed from the faction.");
							target.sendMessage(ChatColor.YELLOW + "A admin has removed you from your faction. You may choose again by doing /faction <alliance/horde>");
							plugin.savePlayerFactions();
							
						} else if(!registered_players.contains(target.getUniqueId().toString()))  {
							p.sendMessage(ChatColor.RED + target.getName() + " has not selected a faction or has already been removed from the list.");
						}
					} else {
						p.sendMessage(ChatColor.RED + "§lYou do not have permission to do that.");
					}
				}
				
			}
			if(args.length > 2) {
				p.sendMessage(ChatColor.RED + "Too many arguments!");
				p.sendMessage(ChatColor.RED + "Usage: /faction <Alliance/Horde>");
			}
			
			return true;
		}
		
		
		return false;
	}

}
