package com.github.maxopoly.WurstCivTools.misc;

import java.util.Random;

import org.bukkit.configuration.ConfigurationSection;

public class ChanceConfig {
	
	private double baseChance;
	private double levelMultiplier;
	private Random rng;
	
	public ChanceConfig(ConfigurationSection config) {
		this.baseChance = config.getDouble("baseChance", 1);
		this.levelMultiplier = config.getDouble("levelChanceMultiplier", 0);
	}
	
	public boolean roll(int level) {
		return rng.nextDouble() <= baseChance + (((double) level) * levelMultiplier);
	}
	
	public boolean roll() {
		return rng.nextDouble() <= baseChance;
	}

}
