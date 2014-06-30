package me.condolent;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Achievement implements CommandExecutor, Listener {
	
	McRPG plugin;
	
	public Achievement(McRPG instance) {
		plugin = instance;
	}
	
	public FileConfiguration getPlayerAchievements() {
		return plugin.getPlayerAchievements();
	}
	
	public FileConfiguration getCurrency() {
		return plugin.getPlayerCurrency();
	}
	
	public FileConfiguration getConfig() {
		return plugin.getConfig();
	}
	
	
	private void openGUI(Player player) {
		List<String> zombie_achiev = getPlayerAchievements().getStringList("zombie_killah");
		getPlayerAchievements().set("zombie_killah", zombie_achiev);
		List<String> kinda_serious = getPlayerAchievements().getStringList("kinda_serious");
		getPlayerAchievements().set("kinda_serious", kinda_serious);
		List<String> get_rekt = getPlayerAchievements().getStringList("get_rekt");
		getPlayerAchievements().set("get_rekt", get_rekt);
		List<String> achievement_novice = getPlayerAchievements().getStringList("achievement_novice");
		getPlayerAchievements().set("achievement_novice", achievement_novice);
		plugin.savePlayerAchievements();
		
		Inventory inv = Bukkit.createInventory(null, 18, ChatColor.DARK_GREEN + "§lAchievements");
		
		ItemStack ZombieKill = new ItemStack(Material.SKULL_ITEM);
		ItemMeta ZombieKillMeta = ZombieKill.getItemMeta();
		if(zombie_achiev.contains(player.getUniqueId().toString())) {
			ZombieKillMeta.setDisplayName(ChatColor.GOLD + "§mZombie Killah" + ChatColor.GOLD + " (Finished)");
		} else {
			ZombieKillMeta.setDisplayName(ChatColor.GOLD + "Zombie Killah");
		}
		ZombieKillMeta.setLore(Arrays.asList("Kill a zombie!"));
		ZombieKill.setItemMeta(ZombieKillMeta);
		
		ItemStack getSword = new ItemStack(Material.IRON_SWORD);
		ItemMeta getSwordMeta = getSword.getItemMeta();
		if(kinda_serious.contains(player.getUniqueId().toString())) {
			getSwordMeta.setDisplayName(ChatColor.GOLD + "§mIt's kinda serious.." + ChatColor.GOLD + " (Finished)");
		} else {
			getSwordMeta.setDisplayName(ChatColor.GOLD + "It's kinda serious..");
		}
		getSwordMeta.setLore(Arrays.asList("Obtain a dropped iron sword."));
		getSword.setItemMeta(getSwordMeta);

		ItemStack playerKill = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemMeta mplayerKill = playerKill.getItemMeta();
		if(get_rekt.contains(player.getUniqueId().toString())) {
			mplayerKill.setDisplayName(ChatColor.GOLD + "§mGet rekt." + ChatColor.GOLD + " (Finished)");
		} else {
			mplayerKill.setDisplayName(ChatColor.GOLD + "Get rekt.");
		}
		mplayerKill.setLore(Arrays.asList("Kill another player"));
		playerKill.setItemMeta(mplayerKill);
		
		ItemStack openAchievs = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta mopenAchievs = openAchievs.getItemMeta();
		if(!achievement_novice.contains(player.getUniqueId().toString())) {
			mopenAchievs.setDisplayName(ChatColor.GOLD + "Achievement novice");
		} else {
			mopenAchievs.setDisplayName(ChatColor.GOLD + "§mAchievement novice" + ChatColor.GOLD + " (Finished)");
		}
		mopenAchievs.setLore(Arrays.asList("Open the achievement-menu"));
		openAchievs.setItemMeta(mopenAchievs);
		
		ItemStack comingSoon = new ItemStack(Material.BAKED_POTATO);
		ItemMeta mcomingSoon = comingSoon.getItemMeta();
		mcomingSoon.setDisplayName(ChatColor.GREEN + "More coming soon..");
		mcomingSoon.setLore(Arrays.asList("We're working on adding more achievements", "in the next update!"));
		comingSoon.setItemMeta(mcomingSoon);
		
		inv.setItem(0, openAchievs);
		inv.setItem(1, ZombieKill);
		inv.setItem(2, getSword);
		inv.setItem(3, playerKill);
		inv.setItem(17, comingSoon);
		
		player.openInventory(inv);
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("achievement")) {
			p.sendMessage(ChatColor.YELLOW + "§oOpening up achievement-menu...");
			openGUI(p);
		}
		
		return false;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Achievements"))
			return;
		
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onZombieKill(EntityDeathEvent e) {
		Entity ent = e.getEntity();
		Entity killer = e.getEntity().getKiller();
		
		if(ent.isDead()) {
			if (killer == null || !(killer instanceof Player)) {
			    return;
			}
		}
		if(ent instanceof Player) {
			if(killer == null || !(killer instanceof Player)) {
				return;
			}
		}
		
		String KillerName = e.getEntity().getKiller().getUniqueId().toString();
		List<String> zombie_achiev = getPlayerAchievements().getStringList("zombie_killah");
		List<String> get_rekt = getPlayerAchievements().getStringList("get_rekt");
		getPlayerAchievements().set("get_rekt", get_rekt);
		getPlayerAchievements().set("zombie_killah", zombie_achiev);
		
		if(ent instanceof Zombie) {
			if(!zombie_achiev.contains(KillerName)) {
				zombie_achiev.add(KillerName);
				plugin.savePlayerAchievements();
				getCurrency().set(KillerName, getCurrency().getInt(KillerName) + 5);
				plugin.savePlayerCurrency();
				e.getEntity().getKiller().sendMessage(ChatColor.GREEN + "§lCongratulations!");
				e.getEntity().getKiller().sendMessage(ChatColor.GREEN + "You have just earned the achievement '§lZombie Killah" + ChatColor.GREEN + "'!");
				e.getEntity().getKiller().sendMessage(ChatColor.GREEN + "You have been rewarded 5 gold for your actions!");
				e.getEntity().getKiller().sendMessage(ChatColor.GREEN + "§oDo /achievement to view all achievements.");
			} else {
				
			}
		}
		
		if(ent instanceof Player) {
			if(!get_rekt.contains(KillerName)) {
				get_rekt.add(KillerName);
				getCurrency().set(KillerName, getCurrency().getInt(KillerName) + 10);
				plugin.savePlayerAchievements();
				plugin.savePlayerCurrency();
				e.getEntity().getKiller().sendMessage(ChatColor.GREEN + "§lCongratulations!");
				e.getEntity().getKiller().sendMessage(ChatColor.GREEN + "You have just earned the achievement '§lGet Rekt" + ChatColor.GREEN + "'!");
				e.getEntity().getKiller().sendMessage(ChatColor.GREEN + "You have been rewarded 10 gold for your actions!");
				e.getEntity().getKiller().sendMessage(ChatColor.GREEN + "§oDo /achievement to view all achievements.");
			} else {
				
			}
		}
	}
	
	@EventHandler
	public void onIronSword(PlayerPickupItemEvent e) {
		List<String> kinda_serious = getPlayerAchievements().getStringList("kinda_serious");
		getPlayerAchievements().set("kinda_serious", kinda_serious);
		Player p = e.getPlayer();
		String UUID = p.getUniqueId().toString();
		ItemStack sword = e.getItem().getItemStack();
		
		if(sword.getType()==Material.IRON_SWORD) {
			if(!kinda_serious.contains(UUID)) {
				p.sendMessage(ChatColor.GREEN + "§lCongratulations!");
				p.sendMessage(ChatColor.GREEN + "You have just earned the achievement '§lIt's kinda serious.." + ChatColor.GREEN + "'!");
				p.sendMessage(ChatColor.GREEN + "You have been rewarded 5 gold for your actions!");
				p.sendMessage(ChatColor.YELLOW + "§oDo /achievement to view all achievements.");
				kinda_serious.add(p.getUniqueId().toString());
				getCurrency().set(UUID, getCurrency().getInt(UUID) + 5);
				plugin.savePlayerAchievements();
				plugin.savePlayerCurrency();
			} else {
				
			}
		}
		
	}
	
	@EventHandler
	public void onAchievOpen(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String UUID = p.getUniqueId().toString();
		
		List<String> achievement_novice = getPlayerAchievements().getStringList("achievement_novice");
		getPlayerAchievements().set("achievement_novice", achievement_novice);
		
		if(e.getMessage().contains("achievement")) {
			if(!achievement_novice.contains(UUID)) {
				achievement_novice.add(UUID);
				getCurrency().set(UUID, getCurrency().getInt(UUID) + 2);
				plugin.saveDefaultPlayerAchievements();
				plugin.savePlayerCurrency();
				p.sendMessage(ChatColor.GREEN + "§lCongratulations!");
				p.sendMessage(ChatColor.GREEN + "You have just earned the achievement '§lAchievement novice" + ChatColor.GREEN + "'!");
				p.sendMessage(ChatColor.GREEN + "You have been rewarded 2 gold for your actions!");
				p.sendMessage(ChatColor.YELLOW + "§oDo /achievement to view all achievements.");
			} else {
				
			}
		}
	}

}
