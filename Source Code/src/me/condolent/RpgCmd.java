package me.condolent;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class RpgCmd implements CommandExecutor {
	
	McRPG plugin;
	
	public RpgCmd(McRPG instance) {
		plugin = instance;
	}
	
	public FileConfiguration getConfig() {
		return plugin.getConfig();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		PluginDescriptionFile pdf = plugin.getDescription();
		
		if(cmd.getName().equalsIgnoreCase("rpg")) {
			if(args.length < 1) {
				p.sendMessage(ChatColor.YELLOW + "Invalid arguments. Type /rpg help for a list of commands.");
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("day")) {
					if(p.hasPermission("mcrpg.admin")) {
						p.getWorld().setTime(0);
						p.sendMessage(ChatColor.YELLOW + "Time set to day.");
					} else {
						p.sendMessage(ChatColor.YELLOW + "You do not have permission to this command.");
					}
				}
				if(args[0].equalsIgnoreCase("night")) {
					if(p.hasPermission("mcrpg.admin")) {
						p.getWorld().setTime(18000);
						p.sendMessage(ChatColor.YELLOW + "Time set to night.");
					} else {
						p.sendMessage(ChatColor.YELLOW + "You do not have permission to this command.");
					}
				}
				if(args[0].equalsIgnoreCase("reload")) {
					if(p.hasPermission("mcrpg.admin")) {
						plugin.reloadConfig();
						p.sendMessage(ChatColor.YELLOW + "Configuration-file reloaded!");
					} else {
						p.sendMessage(ChatColor.YELLOW + "Not permitted to do this!");
					}
				}
				if(args[0].equalsIgnoreCase("version")) {
					p.sendMessage(ChatColor.YELLOW + "You're running McRPG version " + pdf.getVersion());
				}
				if(args[0].equalsIgnoreCase("update")) {
					p.sendMessage(ChatColor.YELLOW + "http://giantfall.net/mcrpg - McRPG official website.");
				}
				if(args[0].equalsIgnoreCase("help")) {
					if(p.hasPermission("mcrpg.admin")) {
						p.sendMessage(ChatColor.YELLOW + "/rpg reload §o- Reloads the configuration file.");
						p.sendMessage(ChatColor.YELLOW + "/supply <player> <item> §o- Supplies the specified player with a item from this plugin.");
						p.sendMessage(ChatColor.YELLOW + "/heal <player> §o- Fully heals the specified player. /heal just heals yourself.");
						p.sendMessage(ChatColor.YELLOW + "rpg <day/night> §o- Sets world-time to either night or day, depending on what you choose.");
						p.sendMessage(ChatColor.YELLOW + "/rpg update §o- Sends you a link to the official plugin-website.");
						p.sendMessage(ChatColor.YELLOW + "/gm <0/1/survival/creative> <player> §o- Sets the specified gamemode to the specified player.");
						p.sendMessage(ChatColor.YELLOW + "/ci <player> §o- Clears the inventory of the player. (Gear is not affected)");
					}
					p.sendMessage(ChatColor.YELLOW + "/rpg version §o - Shows which version of McRPG you're running.");
					p.sendMessage(ChatColor.YELLOW + "/w <player> <message> §o- Sends a whisper to specified online player.");
					p.sendMessage(ChatColor.YELLOW + "/y <message> §o- Sends out a server-wide yell.");
					p.sendMessage(ChatColor.YELLOW + "/class <class> §o- Choose which class you want to play as.");
				}
			}
		}
		
		return false;
	}
	
	
}
