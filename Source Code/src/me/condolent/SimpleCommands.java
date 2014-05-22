package me.condolent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SimpleCommands implements CommandExecutor {
	
	McRPG plugin;
	
	public SimpleCommands(McRPG instance) {
		plugin = instance;
	}
	
	public FileConfiguration getConfig() {
		return plugin.getConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("heal")) {
			if(p.hasPermission("mcrpg.admin")) {
				if(args.length < 1) {
					p.sendMessage(ChatColor.YELLOW + "You were healed to 100%!");
					p.setHealth(20.0);
					p.setFoodLevel(20);
				} else if(args.length == 1) {
					@SuppressWarnings("deprecation")
					Player target = Bukkit.getServer().getPlayerExact(args[0]);
					if(target == null) {
						p.sendMessage(ChatColor.YELLOW + "Player " + args[0] + " not found.");
						return true;
					}
					p.sendMessage(ChatColor.YELLOW + "You healed player " + target.getName());
					target.setHealth(20.0);
					target.setFoodLevel(20);
					target.sendMessage(ChatColor.YELLOW + "You have been healed by " + p.getName());
				} else if(args.length > 1) {
					p.sendMessage(ChatColor.RED + "Invalid arguments. Usage: /heal <player>");
				}
			} else {
				p.sendMessage(ChatColor.RED + "That command is only availible to admins.");
			}
			
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("ci")) {
			if(p.hasPermission("mcrpg.admin")) {
				if(args.length < 1) {
						p.getInventory().clear();
						p.sendMessage(ChatColor.YELLOW + "Inventory cleared!");
						p.playSound(p.getLocation(), Sound.PORTAL_TRIGGER, 1, 2);
				} else if(args.length == 1) {
					@SuppressWarnings("deprecation")
					Player target = Bukkit.getServer().getPlayerExact(args[0]);
					if(target == null) {
						p.sendMessage(ChatColor.YELLOW + "Player " + args[0] + " not found.");
						return true;
					}
					target.getInventory().clear();
					p.sendMessage(ChatColor.YELLOW + "You cleared " + target.getName() + "'s inventory.");
					target.sendMessage(ChatColor.DARK_AQUA + "[GM] " + ChatColor.YELLOW + p.getName() + " cleared your inventory");
					target.playSound(p.getLocation(), Sound.PORTAL_TRIGGER, 1, 2);
				}
			} else {
				p.sendMessage(ChatColor.YELLOW + "You do not have permission to this command.");
			}
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("setspawn")) {
			if(p.hasPermission("mcrpg.admin")) {
				p.getWorld().setSpawnLocation(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
				p.sendMessage(ChatColor.YELLOW + "Spawn set.");
			} else {
				p.sendMessage(ChatColor.YELLOW + "You do not have access to that command.");
			}
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("spawn")) {
			p.sendMessage(ChatColor.YELLOW + "Teleporting to spawn..");
			p.teleport(p.getWorld().getSpawnLocation());
			p.playSound(p.getLocation(), Sound.PORTAL_TRIGGER, 1, 1);
			
			return true;
		}
		
		return false;
	}

}
