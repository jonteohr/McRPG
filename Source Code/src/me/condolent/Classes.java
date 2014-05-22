package me.condolent;

import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Classes implements CommandExecutor {
	
	McRPG plugin;
	
	public Classes(McRPG instance) {
		plugin = instance;
	}
	
	public FileConfiguration getCustomConfig() {
		return plugin.getPlayerLogging();
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		/* ITEMS */
		
		ItemStack warriorweapon = new ItemStack(Material.STONE_SWORD, 1);
		ItemMeta mwarriorweapon = warriorweapon.getItemMeta();
		mwarriorweapon.setDisplayName(ChatColor.GRAY + "Homemade Sword");
		mwarriorweapon.setLore(Arrays.asList("Made of the stone outside your house"));
		warriorweapon.setItemMeta(mwarriorweapon);
		
		ItemStack warriorchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		ItemMeta mwarriorchest = warriorchest.getItemMeta();
		mwarriorchest.setDisplayName(ChatColor.GRAY + "Thin jacket");
		warriorchest.setItemMeta(mwarriorchest);
		
		ItemStack mageweapon = new ItemStack(Material.WOOD_HOE, 1);
		ItemMeta mmageweapon = mageweapon.getItemMeta();
		mmageweapon.setDisplayName(ChatColor.GRAY + "Old Staff");
		mmageweapon.setLore(Arrays.asList("You get a splinter just by", "touching it..."));
		mageweapon.setItemMeta(mmageweapon);
		
		ItemStack magehat = new ItemStack(Material.LEATHER_HELMET, 1);
		ItemMeta mmagehat = magehat.getItemMeta();
		mmagehat.setDisplayName(ChatColor.GRAY + "Wizard's Hat");
		magehat.setItemMeta(mmagehat);
		
		ItemStack paladinweapon = new ItemStack(Material.STONE_AXE, 1);
		ItemMeta mpaladinweapon = paladinweapon.getItemMeta();
		mpaladinweapon.setDisplayName(ChatColor.GRAY + "Stone Hammer");
		mpaladinweapon.setLore(Arrays.asList("Homemade"));
		paladinweapon.setItemMeta(mpaladinweapon);
		
		ItemStack paladinlegs = new ItemStack(Material.IRON_LEGGINGS, 1);
		ItemMeta mpaladinlegs = paladinlegs.getItemMeta();
		mpaladinlegs.setDisplayName(ChatColor.GRAY + "Father's Old Legguards");
		paladinlegs.setItemMeta(mpaladinlegs);
		
		
		// LOG THE PLAYER FOR CHOOSING CLASS
		List<String> classed_players = getCustomConfig().getStringList("classed_players");
		List<String> warriors = getCustomConfig().getStringList("warriors");
		List<String> mages = getCustomConfig().getStringList("mages");
		List<String> paladins = getCustomConfig().getStringList("paladins");
		getCustomConfig().set("mages", mages);
		getCustomConfig().set("warriors", warriors);
		getCustomConfig().set("paladins", paladins);
		getCustomConfig().set("classed_players", classed_players);
		plugin.savePlayerLogging();
		
		if(cmd.getName().equalsIgnoreCase("class")) {
			
			if(args.length < 1) {
				
				p.sendMessage(ChatColor.YELLOW + "Invalid arguments! /class <class>");
				p.sendMessage(ChatColor.YELLOW + "Avalible classes: Warrior, Mage & Paladin");
				
			} else if(args.length == 1) {
				if(getCustomConfig().getStringList("classed_players").contains(p.getName())) {
					
					p.sendMessage(ChatColor.YELLOW + "You've already chosen a class!");
					
				}
				if(!getCustomConfig().getStringList("classed_players").contains(p.getName())) {
					
					if(args[0].equalsIgnoreCase("warrior")) {
							p.sendMessage(ChatColor.YELLOW + "You chose Warrior as your class and were given starter-gear for Warriors.");
							p.getInventory().addItem(new ItemStack(warriorweapon));
							p.getInventory().setChestplate(warriorchest);
							p.playSound(p.getLocation(), Sound.ANVIL_USE, 1, 1);
							classed_players.add(p.getName());
							warriors.add(p.getName());
					}
				
					if(args[0].equalsIgnoreCase("mage")) {
						p.sendMessage(ChatColor.YELLOW + "You chose Mage as your class and were given starter-gear for Mages.");
						p.getInventory().addItem(new ItemStack(mageweapon));
						p.getInventory().setHelmet(new ItemStack(magehat));
						p.playSound(p.getLocation(), Sound.FIRE, 1, 1);
						classed_players.add(p.getName());
						mages.add(p.getName());
					}
					
					if(args[0].equalsIgnoreCase("paladin")) {
						p.sendMessage(ChatColor.YELLOW + "You chose Paladin as your class and were given starter-gear for Paladins.");
						p.getInventory().addItem(new ItemStack(paladinweapon));
						p.getInventory().setLeggings(new ItemStack(paladinlegs));
						p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1, 1);
						classed_players.add(p.getName());
						paladins.add(p.getName());
					}
					
				}
				
			} else if(args.length > 1) {
				p.sendMessage(ChatColor.YELLOW + "Too many arguments! /class <class>");
				p.sendMessage(ChatColor.YELLOW + "Avalible classes: Warrior, Mage & Paladin");
			}
			return true;
		}
		
		return false;
	}

}
