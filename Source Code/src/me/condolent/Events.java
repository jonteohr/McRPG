package me.condolent;

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
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		e.setJoinMessage(ChatColor.YELLOW + p.getName() + " joined the game");
		
		if(getConfig().getString("welcome_msg", "enable").equalsIgnoreCase("enable")) {
			p.sendMessage(getConfig().getString("MOTD").replaceAll("&", "§").replaceAll("%player%", p.getName()));
		} else if(getConfig().getString("welcome_msg", "disable").equalsIgnoreCase("disable")) {
			
		}
		if(p.hasPlayedBefore() == false) {
			p.sendMessage(ChatColor.GREEN + "Choose your class by typing");
			p.sendMessage(ChatColor.AQUA + "§l/class <Mage/Paladin/Warrior>");
			p.sendMessage(" ");
			p.sendMessage(ChatColor.GREEN + "Choose your faction by typing");
			p.sendMessage(ChatColor.AQUA + "§l/faction <Alliance/Horde>");
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
