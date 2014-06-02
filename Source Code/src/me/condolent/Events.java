package me.condolent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import net.gravitydevelopment.updater.Updater;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {
	
	McRPG plugin;
	
	Updater updaterclass;
	
	public Events(McRPG instance) {
		plugin = instance;
	}
	
	public FileConfiguration getConfig() {
		return plugin.getConfig();
	}
	
	public FileConfiguration getPlayerClass() {
		return plugin.getPlayerLogging();
	}
	
	public FileConfiguration getPlayerFactions() {
		return plugin.getPlayerFactions();
	}
	
	public FileConfiguration getPlayerCurrency() {
		return plugin.getPlayerCurrency();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		e.setJoinMessage(ChatColor.YELLOW + p.getName() + " joined the game");
		
		if(getConfig().getString("welcome_msg", "enable").equalsIgnoreCase("enable")) {
			p.sendMessage(getConfig().getString("MOTD").replaceAll("&", "§").replaceAll("%player%", p.getName()));
		} else if(getConfig().getString("welcome_msg", "disable").equalsIgnoreCase("disable")) {
			
		}
		if(!getPlayerCurrency().contains(p.getUniqueId().toString())) {
			getPlayerCurrency().set(p.getUniqueId().toString(), 0);
		}
		if(p.hasPlayedBefore() == false) {
			p.sendMessage(ChatColor.GREEN + "Choose your class by typing");
			p.sendMessage(ChatColor.AQUA + "§l/class <Mage/Paladin/Warrior>");
			p.sendMessage(" ");
			p.sendMessage(ChatColor.GREEN + "Choose your faction by typing");
			p.sendMessage(ChatColor.AQUA + "§l/faction <Alliance/Horde>");
		}
		
		if(p.isOp()) {
			    try {
			        URL verfile = new URL("https://raw.githubusercontent.com/condolent/McRPG/master/Source%20Code/version.txt");
			        BufferedReader in = new BufferedReader(
			        new InputStreamReader(verfile.openStream()));
			        String newVersion = in.readLine();
			        in.close();
			        String oldVersion = plugin.pdf.getVersion();
			                 
			        if (newVersion != null && ! newVersion.equalsIgnoreCase(oldVersion)) {
			            p.sendMessage("§l[" + ChatColor.RED + "§lWARNING!" + "§r§l]§r " + ChatColor.LIGHT_PURPLE + "§oYou're running an outdated version of McRPG. Version " + newVersion.toString() + ChatColor.LIGHT_PURPLE +  " §ois available at: " + ChatColor.AQUA + "§ohttp://bit.ly/1gR7UqV");
			            plugin.getLogger().info("McRPG version " + newVersion.toString() + " is available for download! Go to http://bit.ly/1gR7UqV to download!");
			        	}
			        }
			    catch (IOException ex) {
			        // Ignore any problems that may happen
			    }
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		e.setQuitMessage(ChatColor.YELLOW + p.getName() + " left the game");
	}
	
	@EventHandler
	public void onDing(PlayerLevelChangeEvent e) {
		Player p = e.getPlayer();
		
		p.sendMessage(ChatColor.YELLOW + "Congratulations! You just leveled up to level " + ChatColor.GOLD + "§l[" + ChatColor.GOLD + "§l" + e.getNewLevel() + "]");
	}
	
	
	// CHAT-FORMATTING
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		if(getConfig().getString("chat_colors", "enable").equalsIgnoreCase("enable")) {
			String message = e.getMessage().replaceAll("&", "§");
			e.setMessage(message);
		}
		if(getConfig().getString("chat_colors", "disable").equalsIgnoreCase("disable")) {

		}
		
		if(!p.isOp()) {
			e.setFormat(ChatColor.GRAY + "[%s] says: %s");
			
			// IF HORDE
			if(getPlayerFactions().getStringList("Horde").contains(p.getUniqueId().toString())) {
				e.setFormat(ChatColor.RED + "§l[H] " + ChatColor.WHITE + "§r[%s] says: %s");
				
				if(getPlayerClass().getStringList("Warriors").contains(p.getUniqueId().toString())) {
					e.setFormat(ChatColor.RED + "§l[H] " + ChatColor.GREEN + "(Warrior)" + ChatColor.WHITE + "[%s] says: %s");
				} else if(getPlayerClass().getStringList("Paladins").contains(p.getUniqueId().toString())) {
					e.setFormat(ChatColor.RED + "§l[H] " + ChatColor.GREEN + "(Paladin)" + ChatColor.WHITE + "[%s] says: %s");
				} else if(getPlayerClass().getStringList("Mages").contains(p.getUniqueId().toString())) {
					e.setFormat(ChatColor.RED + "§l[H] " + ChatColor.GREEN + "(Mage)" + ChatColor.WHITE + "[%s] says: %s");
				}
			}
			// IF ALLIANCE
			if(getPlayerFactions().getStringList("Alliance").contains(p.getUniqueId().toString())) {
				e.setFormat(ChatColor.AQUA + "§l[A] " + ChatColor.WHITE + "§r[%s] says: %s");
				
				if(getPlayerClass().getStringList("Warriors").contains(p.getUniqueId().toString())) {
					e.setFormat(ChatColor.AQUA + "§l[A] " + ChatColor.GREEN + "(Warrior)" + ChatColor.WHITE + "[%s] says: %s");
				} else if(getPlayerClass().getStringList("Paladins").contains(p.getUniqueId().toString())) {
					e.setFormat(ChatColor.AQUA + "§l[A] " + ChatColor.GREEN + "(Paladin)" + ChatColor.WHITE + "[%s] says: %s");
				} else if(getPlayerClass().getStringList("Mages").contains(p.getUniqueId().toString())) {
					e.setFormat(ChatColor.AQUA + "§l[A] " + ChatColor.GREEN + "(Mage)" + ChatColor.WHITE + "[%s] says: %s");
				}
			}
		}
		
		if(p.isOp()) {
			e.setFormat(ChatColor.DARK_AQUA + "[GM] " + ChatColor.WHITE + "[%s] says: %s");
			
			// IF HORDE
			if(getPlayerFactions().getStringList("Horde").contains(p.getUniqueId().toString())) {
				e.setFormat(ChatColor.DARK_AQUA + "[GM] " + ChatColor.RED + "§l[H] " + ChatColor.WHITE + "§r[%s] says: %s");
				
				if(getPlayerClass().getStringList("Warriors").contains(p.getUniqueId().toString())) {
					e.setFormat(ChatColor.DARK_AQUA + "[GM] " + ChatColor.RED + "§l[H] " + ChatColor.GREEN + "(Warrior)" + ChatColor.WHITE + "[%s] says: %s");
				} else if(getPlayerClass().getStringList("Paladins").contains(p.getUniqueId().toString())) {
					e.setFormat(ChatColor.DARK_AQUA + "[GM] " + ChatColor.RED + "§l[H] " + ChatColor.GREEN + "(Paladin)" + ChatColor.WHITE + "[%s] says: %s");
				} else if(getPlayerClass().getStringList("Mages").contains(p.getUniqueId().toString())) {
					e.setFormat(ChatColor.DARK_AQUA + "[GM] " + ChatColor.RED + "§l[H] " + ChatColor.GREEN + "(Mage)" + ChatColor.WHITE + "[%s] says: %s");
				}
			}
			// IF ALLIANCE
			if(getPlayerFactions().getStringList("Alliance").contains(p.getUniqueId().toString())) {
				e.setFormat(ChatColor.DARK_AQUA + "[GM] " + ChatColor.AQUA + "§l[A] " + ChatColor.WHITE + "§r[%s] says: %s");
				
				if(getPlayerClass().getStringList("Warriors").contains(p.getUniqueId().toString())) {
					e.setFormat(ChatColor.DARK_AQUA + "[GM] " + ChatColor.AQUA + "§l[A] " + ChatColor.GREEN + "(Warrior)" + ChatColor.WHITE + "[%s] says: %s");
				} else if(getPlayerClass().getStringList("Paladins").contains(p.getUniqueId().toString())) {
					e.setFormat(ChatColor.DARK_AQUA + "[GM] " + ChatColor.AQUA + "§l[A] " + ChatColor.GREEN + "(Paladin)" + ChatColor.WHITE + "[%s] says: %s");
				} else if(getPlayerClass().getStringList("Mages").contains(p.getUniqueId().toString())) {
					e.setFormat(ChatColor.DARK_AQUA + "[GM] " + ChatColor.AQUA + "§l[A] " + ChatColor.GREEN + "(Mage)" + ChatColor.WHITE + "[%s] says: %s");
				}
			}
		}
	}

}
