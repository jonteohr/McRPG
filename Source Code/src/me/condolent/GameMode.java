package me.condolent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameMode implements CommandExecutor {
	
	McRPG plugin;
	
	public GameMode(McRPG instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("gm")) {
			if(p.hasPermission("mcrpg.admin")) {
			if(args.length < 1) {
				
				p.sendMessage(ChatColor.YELLOW + "/gm <0/1/creative/survival>");
				
			} else if(args.length == 1) {
				
				if(args[0].equalsIgnoreCase("1")) {
					p.sendMessage(ChatColor.YELLOW + "Gamemode set to creative-mode");
					p.setGameMode(org.bukkit.GameMode.CREATIVE);
					p.playSound(p.getLocation(), Sound.PORTAL_TRAVEL, 1, 1);
				}
				
				if(args[0].equalsIgnoreCase("0")) {
					p.sendMessage(ChatColor.YELLOW + "Gamemode set to survival-mode");
					p.setGameMode(org.bukkit.GameMode.SURVIVAL);
					p.playSound(p.getLocation(), Sound.PORTAL_TRAVEL, 1, 1);
				}
				
				if(args[0].equalsIgnoreCase("creative")) {
					p.sendMessage(ChatColor.YELLOW + "Gamemode set to creative-mode");
					p.setGameMode(org.bukkit.GameMode.CREATIVE);
					p.playSound(p.getLocation(), Sound.PORTAL_TRAVEL, 1, 1);
				}
				
				if(args[0].equalsIgnoreCase("survival")) {
					p.sendMessage(ChatColor.YELLOW + "Gamemode set to survival-mode");
					p.setGameMode(org.bukkit.GameMode.SURVIVAL);
					p.playSound(p.getLocation(), Sound.PORTAL_TRAVEL, 1, 1);
				}
				
			}
			
			if(args.length == 2) {
				@SuppressWarnings("deprecation")
				Player target = Bukkit.getServer().getPlayerExact(args[1]);
				if(target == null) {
					p.sendMessage(ChatColor.YELLOW + "Player " + args[1] + " not found.");
					return true;
				}
				
				if(args[0].equalsIgnoreCase("1")) {
					target.setGameMode(org.bukkit.GameMode.CREATIVE);
					p.sendMessage(ChatColor.YELLOW + "Changed " + target.getName() + "'s gamemode to Creative");
					target.sendMessage(ChatColor.YELLOW + "Your gamemode was changed to Creative by " + ChatColor.DARK_AQUA + "[GM] " + ChatColor.YELLOW + p.getName());
					target.playSound(p.getLocation(), Sound.PORTAL_TRAVEL, 1, 1);
				}
				if(args[0].equalsIgnoreCase("0")) {
					target.setGameMode(org.bukkit.GameMode.SURVIVAL);
					p.sendMessage(ChatColor.YELLOW + "Changed " + target.getName() + "'s gamemode to Survival");
					target.sendMessage(ChatColor.YELLOW + "Your gamemode was changed to Survival by " + ChatColor.DARK_AQUA + "[GM] " + ChatColor.YELLOW + p.getName());
					target.playSound(p.getLocation(), Sound.PORTAL_TRAVEL, 1, 1);
				}
				if(args[0].equalsIgnoreCase("creative")) {
					target.setGameMode(org.bukkit.GameMode.CREATIVE);
					p.sendMessage(ChatColor.YELLOW + "Changed " + target.getName() + "'s gamemode to Creative");
					target.sendMessage(ChatColor.YELLOW + "Your gamemode was changed to Creative by " + ChatColor.DARK_AQUA + "[GM] " + ChatColor.YELLOW + p.getName());
					target.playSound(p.getLocation(), Sound.PORTAL_TRAVEL, 1, 1);
				}
				if(args[0].equalsIgnoreCase("survival")) {
					target.setGameMode(org.bukkit.GameMode.SURVIVAL);
					p.sendMessage(ChatColor.YELLOW + "Changed " + target.getName() + "'s gamemode to Survival");
					target.sendMessage(ChatColor.YELLOW + "Your gamemode was changed to Survival by " + ChatColor.DARK_AQUA + "[GM] " + ChatColor.YELLOW + p.getName());
					target.playSound(p.getLocation(), Sound.PORTAL_TRAVEL, 1, 1);
				}
			}
			
			return true;
			} else {
				p.sendMessage(ChatColor.YELLOW + "You do not have access to this command!");
				p.playSound(p.getLocation(), Sound.GHAST_SCREAM, 1, 1);
			}
		}
		
		
		return false;
	}

}
