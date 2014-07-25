package me.condolent;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
	
	public FileConfiguration getPlayerCurrency() {
		return plugin.getPlayerCurrency();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String UUID = p.getUniqueId().toString();
		
		e.setJoinMessage(ChatColor.YELLOW + p.getName() + " joined the game");
		
		if(getConfig().getBoolean("welcome_msg")) {
			p.sendMessage(getConfig().getString("MOTD").replaceAll("&", "§").replaceAll("%player%", p.getName()));
		} else if(!getConfig().getBoolean("welcome_msg")) {
			
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
		
		if(!getPlayerCurrency().contains(UUID)) {
			getPlayerCurrency().set(UUID, 0);
			plugin.savePlayerCurrency();
		} else {
			
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
	

}
