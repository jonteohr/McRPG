package me.condolent;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
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
	
	public FileConfiguration getClassesLog() {
		return plugin.getPlayerLogging();
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		/* ITEMS */
		
		ItemStack warriorweapon = new ItemStack(Material.STONE_SWORD, 1);
		warriorweapon.setDurability((short) 64);
		ItemMeta mwarriorweapon = warriorweapon.getItemMeta();
		mwarriorweapon.setDisplayName(ChatColor.GRAY + "Homemade Sword");
		mwarriorweapon.setLore(Arrays.asList("Made of the stone outside your house"));
		warriorweapon.setItemMeta(mwarriorweapon);
		
		ItemStack warriorchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		warriorchest.setDurability((short) 25);
		ItemMeta mwarriorchest = warriorchest.getItemMeta();
		mwarriorchest.setDisplayName(ChatColor.GRAY + "Thin jacket");
		warriorchest.setItemMeta(mwarriorchest);
		
		ItemStack mageweapon = new ItemStack(Material.WOOD_HOE, 1);
		mageweapon.setDurability((short) 45);
		ItemMeta mmageweapon = mageweapon.getItemMeta();
		mmageweapon.setDisplayName(ChatColor.GRAY + "Old Staff");
		mmageweapon.setLore(Arrays.asList("You get a splinter just by", "touching it..."));
		mageweapon.setItemMeta(mmageweapon);
		
		ItemStack magehat = new ItemStack(Material.LEATHER_HELMET, 1);
		magehat.setDurability((short) 35);
		ItemMeta mmagehat = magehat.getItemMeta();
		mmagehat.setDisplayName(ChatColor.GRAY + "Wizard's Hat");
		magehat.setItemMeta(mmagehat);
		
		ItemStack paladinweapon = new ItemStack(Material.STONE_AXE, 1);
		paladinweapon.setDurability((short) 27);
		ItemMeta mpaladinweapon = paladinweapon.getItemMeta();
		mpaladinweapon.setDisplayName(ChatColor.GRAY + "Stone Hammer");
		mpaladinweapon.setLore(Arrays.asList("Homemade"));
		paladinweapon.setItemMeta(mpaladinweapon);
		
		ItemStack paladinlegs = new ItemStack(Material.IRON_LEGGINGS, 1);
		paladinlegs.setDurability((short) 64);
		ItemMeta mpaladinlegs = paladinlegs.getItemMeta();
		mpaladinlegs.setDisplayName(ChatColor.GRAY + "Father's Old Legguards");
		paladinlegs.setItemMeta(mpaladinlegs);
		
		
		// LOG THE PLAYER FOR CHOOSING CLASS
		List<String> classed_players = getClassesLog().getStringList("Classed_players");
		List<String> warriors = getClassesLog().getStringList("Warriors");
		List<String> mages = getClassesLog().getStringList("Mages");
		List<String> paladins = getClassesLog().getStringList("Paladins");
		getClassesLog().set("Mages", mages);
		getClassesLog().set("Warriors", warriors);
		getClassesLog().set("Paladins", paladins);
		getClassesLog().set("Classed_players", classed_players);
		plugin.savePlayerLogging();
		
		if(cmd.getName().equalsIgnoreCase("class")) {
			
			if(args.length < 1) {
				
				p.sendMessage(ChatColor.RED + "Invalid arguments! /class <class>");
				p.sendMessage(ChatColor.RED + "Avalible classes: Warrior, Mage & Paladin");
				
			} else if(args.length == 1) {
				if(getClassesLog().getStringList("Classed_players").contains(p.getUniqueId().toString())) {
					
					p.sendMessage(ChatColor.RED + "You've already chosen a class!");
					
				}
				if(!getClassesLog().getStringList("Classed_players").contains(p.getUniqueId().toString())) {
					
					if(args[0].equalsIgnoreCase("warrior")) {
							p.sendMessage(ChatColor.YELLOW + "You chose Warrior as your class and were given starter-gear for Warriors.");
							p.getInventory().addItem(new ItemStack(warriorweapon));
							p.getInventory().setChestplate(warriorchest);
							p.playSound(p.getLocation(), Sound.ANVIL_USE, 1, 1);
							classed_players.add(p.getUniqueId().toString());
							warriors.add(p.getUniqueId().toString());
							plugin.savePlayerLogging();
					}
				
					if(args[0].equalsIgnoreCase("mage")) {
						p.sendMessage(ChatColor.YELLOW + "You chose Mage as your class and were given starter-gear for Mages.");
						p.getInventory().addItem(new ItemStack(mageweapon));
						p.getInventory().setHelmet(new ItemStack(magehat));
						p.playSound(p.getLocation(), Sound.FIRE, 1, 1);
						classed_players.add(p.getUniqueId().toString());
						mages.add(p.getUniqueId().toString());
						plugin.savePlayerLogging();
					}
					
					if(args[0].equalsIgnoreCase("paladin")) {
						p.sendMessage(ChatColor.YELLOW + "You chose Paladin as your class and were given starter-gear for Paladins.");
						p.getInventory().addItem(new ItemStack(paladinweapon));
						p.getInventory().setLeggings(new ItemStack(paladinlegs));
						p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1, 1);
						classed_players.add(p.getUniqueId().toString());
						paladins.add(p.getUniqueId().toString());
						plugin.savePlayerLogging();
					}
					
				}
				
			} 
			if(args.length == 2) {
				@SuppressWarnings("deprecation")
				Player target = Bukkit.getServer().getPlayerExact(args[1]);
				if(target == null) {
					p.sendMessage(ChatColor.RED + "Player " + args[1] + " could not be found.");
					return true;
				}
				if(args[0].equalsIgnoreCase("deluser")) {
					if(p.hasPermission("mcrpg.admin")) {
						
						if(classed_players.contains(target.getUniqueId().toString())) {
							classed_players.remove(target.getUniqueId().toString());
							mages.remove(target.getUniqueId().toString());
							paladins.remove(target.getUniqueId().toString());
							warriors.remove(target.getUniqueId().toString());
							p.sendMessage(ChatColor.YELLOW + target.getName() + " has been removed from the class.");
							target.sendMessage(ChatColor.YELLOW + "A admin has removed you from your class. You may choose again by doing /class <class>");
							plugin.savePlayerLogging();
						} else if(!classed_players.contains(target.getUniqueId().toString())) {
							p.sendMessage(ChatColor.RED + target.getName() + " has not selected a class or has already been removed from the list.");
						}
						
					} else {
						p.sendMessage(ChatColor.RED + "§lYou do not have permission to do that.");
					}
				}
				
			}
			else if(args.length > 2) {
				p.sendMessage(ChatColor.RED + "Too many arguments! /class <class>");
				p.sendMessage(ChatColor.RED + "Avalible classes: Warrior, Mage & Paladin");
			}
			return true;
		}
		
		return false;
	}

}
