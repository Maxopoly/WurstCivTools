package com.github.maxopoly.WurstCivTools;

import org.bukkit.Bukkit;

import com.github.maxopoly.WurstCivTools.anvil.AnvilHandler;
import com.github.maxopoly.WurstCivTools.listener.AnvilListener;
import com.github.maxopoly.WurstCivTools.listener.ToolListener;

import vg.civcraft.mc.civmodcore.ACivMod;

public class WurstCivTools extends ACivMod {
	
	private static WurstCivTools instance;
	private static WurstManager manager;
	private static AnvilHandler anvilHandler;
	
	public String getPluginName() {
		return "WurstCivTools";
	}
	
	public void onEnable() {
		instance = this;
		ConfigParser cp = new ConfigParser();
		manager = cp.parse();
		anvilHandler = cp.getAnvilHandler();
		registerListeners();
	}
	
	public void onDisable() {
		
	}
	
	public static WurstCivTools getPlugin() {
		return instance;
	}
	
	public static WurstManager getManager() {
		return manager;
	}
	
	public static AnvilHandler getAnvilHandler() {
		return anvilHandler;
	}
	
	public void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new ToolListener(), this);
		if (anvilHandler != null) {
			Bukkit.getPluginManager().registerEvents(new AnvilListener(anvilHandler), this);
		}
	}
}
