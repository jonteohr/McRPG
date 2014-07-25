package me.condolent;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SimpleCommands implements CommandExecutor {
	
	McRPG plugin;
	
	public SimpleCommands(McRPG instance) {
		plugin = instance;
	}
	
	public FileConfiguration getConfig() {
		return plugin.getConfig();
	}
	
	public FileConfiguration getFactions() {
		return plugin.getPlayerFactions();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		String UUID = p.getUniqueId().toString();
		
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
						p.sendMessage(ChatColor.RED + "Player " + args[0] + " not found.");
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
				p.sendMessage(ChatColor.RED + "§lThat command is only availible to admins.");
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
						p.sendMessage(ChatColor.RED + "Player " + args[0] + " not found.");
						return true;
					}
					target.getInventory().clear();
					p.sendMessage(ChatColor.YELLOW + "You cleared " + target.getName() + "'s inventory.");
					target.sendMessage(ChatColor.DARK_AQUA + "[GM] " + ChatColor.YELLOW + p.getName() + " cleared your inventory");
					target.playSound(p.getLocation(), Sound.PORTAL_TRIGGER, 1, 2);
				}
			} else {
				p.sendMessage(ChatColor.RED + "§lYou do not have permission to this command.");
			}
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("setspawn")) {
			if(p.hasPermission("mcrpg.admin")) {
				
				if(args.length < 1) {
					p.getWorld().setSpawnLocation(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
					p.sendMessage(ChatColor.YELLOW + "Spawn set.");
				} else if(args.length == 1) {
					
					if(args[0].equalsIgnoreCase("horde")) {
						p.sendMessage(ChatColor.YELLOW + "Spawn for the " + ChatColor.RED + "§lHorde " + ChatColor.YELLOW + "has been set!");
						getFactions().set("xh", p.getLocation().getBlockX());
						getFactions().set("yh", p.getLocation().getBlockY());
						getFactions().set("zh", p.getLocation().getBlockZ());
						plugin.savePlayerFactions();
					}
					
					if(args[0].equalsIgnoreCase("alliance")) {
						p.sendMessage(ChatColor.YELLOW + "Spawn for the " + ChatColor.AQUA + "§lAlliance " + ChatColor.YELLOW + "has been set!");
						getFactions().set("xa", p.getLocation().getBlockX());
						getFactions().set("ya", p.getLocation().getBlockY());
						getFactions().set("za", p.getLocation().getBlockZ());
						plugin.savePlayerFactions();
					}
				}
				
			} else {
				p.sendMessage(ChatColor.RED + "§lYou do not have access to that command.");
			}
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("spawn")) {
			List<String> horde = getFactions().getStringList("Horde");
			List<String> alliance = getFactions().getStringList("Alliance");
			
			int xh = getFactions().getInt("xh");
			int yh = getFactions().getInt("yh");
			int zh = getFactions().getInt("zh");
			
			int xa = getFactions().getInt("xa");
			int ya = getFactions().getInt("ya");
			int za = getFactions().getInt("za");
			
			
			Location horde_spawn = new Location(p.getWorld(), xh, yh, zh);
			Location alliance_spawn = new Location(p.getWorld(), xa, ya, za);
			
			
			if(!getFactions().getStringList("Registered_Players").contains(UUID)) {
				p.sendMessage(ChatColor.YELLOW + "Teleporting to spawn..");
				p.teleport(p.getWorld().getSpawnLocation());
				p.playSound(p.getLocation(), Sound.PORTAL_TRIGGER, 1, 1);
			} else if(getFactions().getStringList("Registered_Players").contains(UUID)) {
				if(horde.contains(UUID)) {
					p.teleport(horde_spawn);
				}
				if(alliance.contains(UUID)) {
					p.teleport(alliance_spawn);
					p.setPlayerListName("hello");
				}
			}
			
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("vanish")) {
			if(p.hasPermission("mcrpg.admin")) {
				if(p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
					p.removePotionEffect(PotionEffectType.INVISIBILITY);
					p.sendMessage(ChatColor.GREEN + "§lYou are no longer invisible!");
				}
				else if(!p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60000, 120));
					p.sendMessage(ChatColor.AQUA + "§lYou are now invisible!");
					p.sendMessage(ChatColor.AQUA + "§lType §n/vanish§r" + ChatColor.AQUA + " §lagain to become visible.");
				}
			} else {
				p.sendMessage(ChatColor.RED + "§lYou're not allowed to do that.");
			}
		}
		
		return false;
	}

}
