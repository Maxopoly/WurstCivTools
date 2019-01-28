package com.github.maxopoly.WurstCivTools;

import org.bukkit.Bukkit;

import com.github.maxopoly.WurstCivTools.anvil.AnvilHandler;
import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantManager;
import com.github.maxopoly.WurstCivTools.enchantment.EnchantableType;
import com.github.maxopoly.WurstCivTools.enchantment.EnchantableTypeManager;
import com.github.maxopoly.WurstCivTools.misc.NMSLoadHelper;
import com.github.maxopoly.WurstCivTools.nms.INmsManager;

import vg.civcraft.mc.civmodcore.ACivMod;

public class WurstCivTools extends ACivMod {

	private static WurstCivTools instance;
	private static TagManager manager;
	private static AnvilHandler anvilHandler;

	private static INmsManager nmsManager;

	public static INmsManager getNmsManager() {
		return nmsManager;
	}

	public String getPluginName() {
		return "WurstCivTools";
	}

	public void onEnable() {
		instance = this;
		NMSLoadHelper.init(Bukkit.getServer(), "com.github.maxopoly.WurstCivTools.nms.%s.NmsManager");
		ConfigParser cp = new ConfigParser();
		manager = cp.parse();
		anvilHandler = cp.getAnvilHandler();
		nmsManager = new com.github.maxopoly.WurstCivTools.nms.v1_12_R1.NmsManager();
	}

	public void onDisable() {

	}

	public static WurstCivTools getPlugin() {
		return instance;
	}
	
	public CustomEnchantManager getCustomEnchantManager() {
		return null;
	}
	
	public EnchantableTypeManager getEnchantableTypeManager() {
		return null;
	}

	public static TagManager getManager() {
		return manager;
	}

	public static AnvilHandler getAnvilHandler() {
		return anvilHandler;
	}
}
