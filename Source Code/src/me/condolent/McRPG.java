package me.condolent;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class McRPG extends JavaPlugin {
	
	public Permission admin = new Permission("mcrpg.admin");
	
	private static Plugin plugin;
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public void onEnable() {
		getLogger().info("McRPG successfully enabled! :)");
		this.getServer().getPluginManager().registerEvents(new Events(this), this);
		
		getCommand("supply").setExecutor(new SupplyItems(this));
		getCommand("rpg").setExecutor(new RpgCmd(this));
		getCommand("w").setExecutor(new Whisper(this));
		getCommand("y").setExecutor(new Yell(this));
		getCommand("heal").setExecutor(new SimpleCommands(this));
		getCommand("ci").setExecutor(new SimpleCommands(this));
		getCommand("setspawn").setExecutor(new SimpleCommands(this));
		getCommand("spawn").setExecutor(new SimpleCommands(this));
		getCommand("class").setExecutor(new Classes(this));
		getCommand("gm").setExecutor(new GameMode(this));
		plugin = this;
		
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		saveDefaultPlayerLogging();
		
		// Sword of a Thousand Truths
		ItemStack stt = new ItemStack(Material.DIAMOND_SWORD, 1);
		stt.addEnchantment(Enchantment.DAMAGE_ALL, 3);
		ItemMeta mstt = stt.getItemMeta();
		mstt.setDisplayName(ChatColor.DARK_PURPLE + "Sword of a Thousand Truths");
		mstt.setLore(Arrays.asList("Foretold by Saltzman."));
		stt.setItemMeta(mstt);
		
		ShapedRecipe rstt = new ShapedRecipe(stt);
		rstt.shape(" # ", "!#!", " ! ").setIngredient('#', Material.DIAMOND_BLOCK).setIngredient('!', Material.STICK);
		
		// Stick of truth
		ItemStack st = new ItemStack(Material.STICK, 1);
		ItemMeta mst = st.getItemMeta();
		mst.setDisplayName(ChatColor.DARK_PURPLE + "Stick of Truth");
		mst.setLore(Arrays.asList("The one who possesses the stick of truth", "has the ability to control", "the universe!"));
		st.setItemMeta(mst);
		
		ShapedRecipe rst = new ShapedRecipe(st);
		rst.shape(" ! ", "¤¤¤", " ! ").setIngredient('¤', Material.DIAMOND_BLOCK).setIngredient('!', Material.STICK);
		
		// Malevolent Mail PVP gear
		// Head
		ItemStack mailpvphead = new ItemStack(Material.CHAINMAIL_HELMET, 1);
		mailpvphead.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		mailpvphead.addEnchantment(Enchantment.OXYGEN, 1);
		mailpvphead.addEnchantment(Enchantment.THORNS, 1);
		ItemMeta mpvphead = mailpvphead.getItemMeta();
		mpvphead.setDisplayName(ChatColor.DARK_PURPLE + "Malevolent Gladiator's Chain Helm");
		mpvphead.setLore(Arrays.asList("Season 1", "+43 PvP Power", "+48 Mastery"));
		mailpvphead.setItemMeta(mpvphead);
			
		ShapedRecipe rmailpvphead = new ShapedRecipe(mailpvphead);
		rmailpvphead.shape("###", "#¤#", "   ").setIngredient('#', Material.IRON_INGOT).setIngredient('¤', Material.DIAMOND);
		
		// Chest
		ItemStack mailpvpchest = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
		mailpvpchest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		mailpvpchest.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
		mailpvpchest.addEnchantment(Enchantment.THORNS, 2);
		ItemMeta mpvpchest = mailpvpchest.getItemMeta();
		mpvpchest.setDisplayName(ChatColor.DARK_PURPLE + "Malevolent Gladiator's Chain Armor");
		mpvpchest.setLore(Arrays.asList("Season 1", "+43 PvP Power", "+72 Haste"));
		mailpvpchest.setItemMeta(mpvpchest);
		
		ShapedRecipe rmailpvpchest = new ShapedRecipe(mailpvpchest);
		rmailpvpchest.shape("#¤#", "###", "###").setIngredient('#', Material.IRON_INGOT).setIngredient('¤', Material.DIAMOND);
		
		// Legs
		ItemStack mailpvplegs = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
		mailpvplegs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		mailpvplegs.addEnchantment(Enchantment.PROTECTION_FIRE, 2);
		ItemMeta mpvplegs = mailpvplegs.getItemMeta();
		mpvplegs.setDisplayName(ChatColor.DARK_PURPLE + "Malevolent Gladiator's Chain Leggings");
		mpvplegs.setLore(Arrays.asList("Season 1", "+43 PvP Power"));
		mailpvplegs.setItemMeta(mpvplegs);
		
		ShapedRecipe rmailpvplegs = new ShapedRecipe(mailpvplegs);
		rmailpvplegs.shape("###", "#¤#", "#¤#").setIngredient('#', Material.IRON_INGOT).setIngredient('¤', Material.DIAMOND);
		
		// Feet
		ItemStack mailpvpfeet = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
		mailpvpfeet.addEnchantment(Enchantment.PROTECTION_FALL, 2);
		mailpvpfeet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		mailpvpfeet.addEnchantment(Enchantment.PROTECTION_FIRE, 2);
		ItemMeta mpvpfeet = mailpvpfeet.getItemMeta();
		mpvpfeet.setDisplayName(ChatColor.DARK_PURPLE + "Malevolent Gladiator's Chain Boots");
		mpvpfeet.setLore(Arrays.asList("Season 1", "+43 PvP Power"));
		mailpvpfeet.setItemMeta(mpvpfeet);
		
		ShapedRecipe rmailpvpfeet = new ShapedRecipe(mailpvpfeet);
		rmailpvpfeet.shape("#¤#", "#¤#", "   ").setIngredient('#', Material.IRON_INGOT).setIngredient('¤', Material.DIAMOND);
		
		// Plate PVE gear
		// Head
		ItemStack platepvehead = new ItemStack(Material.IRON_HELMET, 1);
		platepvehead.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		ItemMeta mplatepvehead = platepvehead.getItemMeta();
		mplatepvehead.setDisplayName(ChatColor.DARK_PURPLE + "Greathelm of the Warchief");
		mplatepvehead.setLore(Arrays.asList("Season 1", "Still has some stains of orc-blood."));
		platepvehead.setItemMeta(mplatepvehead);
		
		ShapedRecipe rplatepvehead = new ShapedRecipe(platepvehead);
		rplatepvehead.shape("!!!", "!#!", "¤#%").setIngredient('!', Material.IRON_INGOT).setIngredient('#', Material.OBSIDIAN).setIngredient('¤', Material.BONE).setIngredient('%', Material.ROTTEN_FLESH);
		
		// Chest
		ItemStack platepvechest = new ItemStack(Material.IRON_CHESTPLATE, 1);
		ItemMeta mplatepvechest = platepvechest.getItemMeta();
		mplatepvechest.setDisplayName(ChatColor.DARK_PURPLE + "Icy Blood Chestplate");
		mplatepvechest.setLore(Arrays.asList("Season 1", "+13 Mastery"));
		platepvechest.setItemMeta(mplatepvechest);
		
		ShapedRecipe rplatepvechest = new ShapedRecipe(platepvechest);
		rplatepvechest.shape("   ", " # ", "   ").setIngredient('#', Material.DIAMOND_CHESTPLATE);
		
		// Add recipes
		getServer().addRecipe(rplatepvechest);
		getServer().addRecipe(rplatepvehead);
		getServer().addRecipe(rstt);
		getServer().addRecipe(rmailpvpchest);
		getServer().addRecipe(rst);
		getServer().addRecipe(rmailpvphead);
		getServer().addRecipe(rmailpvplegs);
		getServer().addRecipe(rmailpvpfeet);
	}
	
	public void onDisable() {
		getLogger().info("Disabling McRPG...");
		getServer().clearRecipes();
	}
	
	
	// 	PlayerClasses.YML
	private FileConfiguration PlayerLogging = null;
	private File PlayerLoggingFile = null;
	
	public void reloadPlayerLogging() {
	    if (PlayerLoggingFile == null) {
	    	PlayerLoggingFile = new File(getDataFolder(), "PlayerClasses.yml");
	    }
	    PlayerLogging = YamlConfiguration.loadConfiguration(PlayerLoggingFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = this.getResource("PlayerClasses.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        PlayerLogging.setDefaults(defConfig);
	    }
	}
	
	public FileConfiguration getPlayerLogging() {
	    if (PlayerLogging == null) {
	        reloadPlayerLogging();
	    }
	    return PlayerLogging;
	}
	
	public void savePlayerLogging() {
	    if (PlayerLogging == null || PlayerLoggingFile == null) {
	        return;
	    }
	    try {
	        getPlayerLogging().save(PlayerLoggingFile);
	    } catch (IOException ex) {
	        getLogger().log(Level.SEVERE, "Could not save config to " + PlayerLoggingFile, ex);
	    }
	}
	
	public void saveDefaultPlayerLogging() {
	    if (PlayerLoggingFile == null) {
	    	PlayerLoggingFile = new File(getDataFolder(), "PlayerClasses.yml");
	    }
	    if (!PlayerLoggingFile.exists()) {            
	         plugin.saveResource("PlayerClasses.yml", false);
	     }
	}
	
}