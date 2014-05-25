package me.condolent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Whisper implements CommandExecutor {
	
	McRPG plugin;
	
	public Whisper(McRPG instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		
			if(cmd.getName().equalsIgnoreCase("w")) {
				if(args.length < 2) {
					p.sendMessage(ChatColor.RED + "Invalid arguments.");
					p.sendMessage(ChatColor.RED + "/w <player> <message>");
				} else {
					@SuppressWarnings("deprecation")
					Player target = Bukkit.getServer().getPlayerExact(args[0]);
					if(target == null) {
						p.sendMessage(ChatColor.RED + "Player " + args[0] + " not found.");
						return true;
					}
					StringBuilder s = new StringBuilder();
					for(int i = 1; i < args.length; i++) {
						s.append(args[i]).append(" ");
					}
					String message = s.toString();

					if(!p.isOp()) {
						target.sendMessage(ChatColor.LIGHT_PURPLE + "[" + p.getName() + "] whispers: " + message);
						if(!target.isOp()) {
							p.sendMessage(ChatColor.LIGHT_PURPLE + "To [" + target.getName() + "]: " + message);
						} else if(target.isOp()) {
							p.sendMessage(ChatColor.LIGHT_PURPLE + "To " + ChatColor.DARK_AQUA + "[GM] " + ChatColor.LIGHT_PURPLE + "[" + args[0] + "]: " + message);
						}
					} else if(p.isOp()) {
						target.sendMessage(ChatColor.DARK_AQUA + "[GM] " + ChatColor.LIGHT_PURPLE + "[" + p.getName() + "] whispers: " + message);
						if(!target.isOp()) {
							p.sendMessage(ChatColor.LIGHT_PURPLE + "To [" + target.getName() + "]: " + message);
						} else if(target.isOp()) {
							p.sendMessage(ChatColor.LIGHT_PURPLE + "To " + ChatColor.DARK_AQUA + "[GM] " + ChatColor.LIGHT_PURPLE + "[" + target.getName() + "]: " + message);
						}
					}
				}
			}
		
		return false;
	}

}
