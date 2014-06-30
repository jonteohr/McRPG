package me.condolent;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {
	
	McRPG plugin;
	
	public Chat(McRPG instance) {
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
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		
		if(getConfig().getString("chat_colors", "enable").equalsIgnoreCase("enable")) {
			String message = e.getMessage().replaceAll("&", "§");
			e.setMessage(message);
		}
		if(getConfig().getString("chat_colors", "disable").equalsIgnoreCase("disable")) {

		}
		
		if(!p.isOp()) {
			if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
				e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", "").replaceAll("%class%", "").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
			} else {
				e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", ChatColor.GRAY + p.getName() + "§r").replaceAll("%message%", e.getMessage()).replaceAll("%faction%", "").replaceAll("%class%", "").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
			}
			// IF HORDE
			if(getPlayerFactions().getStringList("Horde").contains(p.getUniqueId().toString())) {
				if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
					e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
				} else {
					e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
				}
				if(getPlayerClass().getStringList("Warriors").contains(p.getUniqueId().toString())) {
					if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
						e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "(Warrior)").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
					} else {
					e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "(Warrior)").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
					}
				} else if(getPlayerClass().getStringList("Paladins").contains(p.getUniqueId().toString())) {
					if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
						e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "(Paladin)").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
					} else {
						e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "(Paladin)").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
					}
				} else if(getPlayerClass().getStringList("Mages").contains(p.getUniqueId().toString())) {
					if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
						e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "(Mage)").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
					} else {
						e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "(Mage)").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
					}
				}
			}
			// IF ALLIANCE
			if(getPlayerFactions().getStringList("Alliance").contains(p.getUniqueId().toString())) {
				if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
					e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
				} else {
					e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
				}
				
				if(getPlayerClass().getStringList("Warriors").contains(p.getUniqueId().toString())) {
					if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
						e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "(Warrior)").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
					} else {
						e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "(Warrior)").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
					}
				} else if(getPlayerClass().getStringList("Paladins").contains(p.getUniqueId().toString())) {
					if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
						e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "(Paladin)").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
					} else {
						e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "(Paladin)").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
					}
				} else if(getPlayerClass().getStringList("Mages").contains(p.getUniqueId().toString())) {
					if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
						e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "(Mage)").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
					} else {
						e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "(Mage)").replaceAll("%op%", ChatColor.DARK_AQUA + "").replaceAll("&", "§"));
					}
				}
			}
		}
		
		if(p.isOp()) {
			if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
				e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", "").replaceAll("%class%", "").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
			} else {
				e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", "").replaceAll("%class%", "").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]" + ChatColor.RESET).replaceAll("&", "§"));
			}
			// IF HORDE
			if(getPlayerFactions().getStringList("Horde").contains(p.getUniqueId().toString())) {
				if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
					e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
				} else {
					e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
				}
				if(getPlayerClass().getStringList("Warriors").contains(p.getUniqueId().toString())) {
					if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3a")) {
						e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "(Warrior)").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
					} else {
						e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "(Warrior)").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
					}
				} else if(getPlayerClass().getStringList("Paladins").contains(p.getUniqueId().toString())) {
					if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
						e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "(Paladin)").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
					} else {
						e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "(Paladin)").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
					}
				} else if(getPlayerClass().getStringList("Mages").contains(p.getUniqueId().toString())) {
					if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
						e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "(Mage)").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
					} else {
						e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.RED + "§l[H]§r").replaceAll("%class%", "(Mage)").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
					}
				}
			}
			// IF ALLIANCE
			if(getPlayerFactions().getStringList("Alliance").contains(p.getUniqueId().toString())) {
				if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
					e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
				} else {
					e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
				}
				
				if(getPlayerClass().getStringList("Warriors").contains(p.getUniqueId().toString())) {
					if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
						e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "(Warrior)").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
					} else {
						e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "(Warrior)").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
					}
				} else if(getPlayerClass().getStringList("Paladins").contains(p.getUniqueId().toString())) {
					if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
						e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "(Paladin)").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
					} else {
						e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "(Paladin)").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
					}
				} else if(getPlayerClass().getStringList("Mages").contains(p.getUniqueId().toString())) {
					if(p.getUniqueId().toString().equals("1808b926-27e6-4da3-a61d-c5b834d4b4a3")) {
						e.setFormat(ChatColor.GOLD + "§o[Dev] " + getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "(Mage)").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
					} else {
						e.setFormat(getConfig().getString("chat_formatting").replaceAll("%player%", p.getName()).replaceAll("%message%", e.getMessage()).replaceAll("%faction%", ChatColor.AQUA + "§l[A]§r").replaceAll("%class%", "(Mage)").replaceAll("%op%", ChatColor.DARK_AQUA + "[GM]§r").replaceAll("&", "§"));
					}
				}
			}
		}
	}

}
