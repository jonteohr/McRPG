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
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if(p.hasPlayedBefore() == false) {
			p.sendMessage(ChatColor.YELLOW + "Welcome to the server, " + p.getName() + "! Choose your class by typing /class <mage/paladin/warrior>");
		}
		
		e.setJoinMessage(ChatColor.YELLOW + p.getName() + " joined the game");
		
		if(getConfig().getString("welcome_msg", "enable").equalsIgnoreCase("enable")) {
			p.sendMessage(getConfig().getString("MOTD").replaceAll("&", "§").replaceAll("%player%", p.getName()));
		} else if(getConfig().getString("welcome_msg", "disable").equalsIgnoreCase("disable")) {
			
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
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		if(!p.isOp()) {
			e.setFormat(ChatColor.WHITE + "[%s] says: %s");
		}
		
		if(p.isOp()) {
			e.setFormat(ChatColor.DARK_AQUA + "[GM] " + ChatColor.WHITE + "[%s] says: %s");
		}
	}

}
