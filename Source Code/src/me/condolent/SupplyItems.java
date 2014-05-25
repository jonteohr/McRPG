package me.condolent;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SupplyItems implements CommandExecutor {
	
	public SupplyItems(McRPG plugin) {
		
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		// Sword of a Thousand Truths
		ItemStack stt = new ItemStack(Material.DIAMOND_SWORD, 1);
		stt.addEnchantment(Enchantment.DAMAGE_ALL, 3);
		ItemMeta mstt = stt.getItemMeta();
		mstt.setDisplayName(ChatColor.DARK_PURPLE + "Sword of a Thousand Truths");
		mstt.setLore(Arrays.asList("Foretold by Saltzman."));
		stt.setItemMeta(mstt);
		
		// Stick of Truth
		ItemStack st = new ItemStack(Material.STICK, 1);
		ItemMeta mst = st.getItemMeta();
		mst.setDisplayName(ChatColor.DARK_PURPLE + "Stick of Truth");
		mst.setLore(Arrays.asList("The one who possesses the stick of truth", "has the ability to control", "the universe!"));
		st.setItemMeta(mst);
		
		if(cmd.getName().equalsIgnoreCase("supply")) {
			if(p.hasPermission("mcrpg.admin")) {
				if(args.length < 2) {
					p.sendMessage(ChatColor.RED + "Invalid arguments. Usage: /supply <player> <item>");
					return true;
				}
				else if(args.length == 2) {
					@SuppressWarnings("deprecation")
					Player target = Bukkit.getServer().getPlayerExact(args[0]);
					if(target == null) {
						p.sendMessage(ChatColor.RED + "Player " + args[0] + " not found.");
						return true;
					}
					if(args[1].equalsIgnoreCase("stick")) {
						p.sendMessage(ChatColor.YELLOW + "You gave " + target.getName() + " x1 of " + ChatColor.DARK_PURPLE + "Stick of Truth" + ChatColor.YELLOW + ".");
						target.getInventory().addItem(st);
					}
					if(args[1].equalsIgnoreCase("sword")) {
						target.getInventory().addItem(stt);
						p.sendMessage(ChatColor.YELLOW + "You gave " + target.getName() + " x1 of " + ChatColor.DARK_PURPLE + "Sword of a Thousand Truths" + ChatColor.YELLOW + ".");
					}
					
					return true;
				}
			} else {
				p.sendMessage(ChatColor.RED + "§lThis command is only availible to admins.");
			}
		}
		
		return false;
	}

}
