package me.condolent;

import org.bukkit.Bukkit;
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
				p.sendMessage(ChatColor.RED + "Invalid arguments. Type /rpg help for a list of commands.");
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("day")) {
					if(p.hasPermission("mcrpg.admin")) {
						p.getWorld().setTime(0);
						p.sendMessage(ChatColor.YELLOW + "Time set to day.");
					} else {
						p.sendMessage(ChatColor.RED + "§lYou do not have permission to this command.");
					}
				}
				if(args[0].equalsIgnoreCase("night")) {
					if(p.hasPermission("mcrpg.admin")) {
						p.getWorld().setTime(18000);
						p.sendMessage(ChatColor.YELLOW + "Time set to night.");
					} else {
						p.sendMessage(ChatColor.RED + "§lYou do not have permission to this command.");
					}
				}
				if(args[0].equalsIgnoreCase("uuid")) {
					p.sendMessage(ChatColor.YELLOW + "Your UUID is: §n" + p.getUniqueId().toString());
				}
				if(args[0].equalsIgnoreCase("reload")) {
					if(p.hasPermission("mcrpg.admin")) {
						plugin.reloadConfig();
						plugin.reloadPlayerLogging();
						plugin.reloadPlayerFactions();
						plugin.reloadPlayerCurrency();
						plugin.reloadPlayerAchievements();
						p.sendMessage(ChatColor.YELLOW + "Configuration-files reloaded!");
					} else {
						p.sendMessage(ChatColor.RED + "§lNot permitted to do this!");
					}
				}
				if(args[0].equalsIgnoreCase("version")) {
					p.sendMessage(ChatColor.YELLOW + "You're running McRPG version Beta-" + pdf.getVersion());
				}
				if(args[0].equalsIgnoreCase("update")) {
					p.sendMessage(ChatColor.YELLOW + "http://dev.bukkit.org/bukkit-plugins/mc-rpg/ - McRPG official project-page.");
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
						p.sendMessage(ChatColor.YELLOW + "/faction deluser <user> §o- Removes a user from his/her faction, making it re-selectable.");
						p.sendMessage(ChatColor.YELLOW + "/class deluser <user> §o- Deletes a user from her/his class, making it re-selectable.");
						p.sendMessage(ChatColor.YELLOW + "/vanish §o- Toggles invisibility.");
						p.sendMessage(ChatColor.YELLOW + "/setspawn §o- Sets spawn for non-faction specified players.");
						p.sendMessage(ChatColor.YELLOW + "/setspawn alliance §o- Sets spawn for the Alliance.");
						p.sendMessage(ChatColor.YELLOW + "/setspawn horde §o- Sets spawn for the Horde.");
					}
					p.sendMessage(ChatColor.YELLOW + "/rpg version §o - Shows which version of McRPG you're running.");
					p.sendMessage(ChatColor.YELLOW + "/w <player> <message> §o- Sends a whisper to specified online player.");
					p.sendMessage(ChatColor.YELLOW + "/y <message> §o- Sends out a server-wide yell.");
					p.sendMessage(ChatColor.YELLOW + "/spawn §o- Teleports you to spawn.");
					p.sendMessage(ChatColor.YELLOW + "/class <class> §o- Choose which class you want to play as.");
					p.sendMessage(ChatColor.YELLOW + "/rpg uuid <player> §o- Gets the specified player's UUID (Unique User Identification).");
					p.sendMessage(ChatColor.YELLOW + "/achievement §o- Shows the achievement-menu.");
				}
			}
			if(args.length > 1) {
				if(args[0].equalsIgnoreCase("uuid")) {
					@SuppressWarnings("deprecation")
					Player target = Bukkit.getServer().getPlayerExact(args[1]);
					if(target == null) {
						p.sendMessage(ChatColor.RED + "Player " + args[1] + " not found.");
						return true;
					}
					p.sendMessage(ChatColor.YELLOW + target.getName() + "'s UUID is: §n" + target.getUniqueId().toString());
				}
			}
		}
		
		return false;
	}
	
	
}
