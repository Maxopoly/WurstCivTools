package com.github.maxopoly.WurstCivTools.misc;

import org.bukkit.configuration.ConfigurationSection;

public class MultiplierConfig {

	private final double flatBonus;
	private final double levelMultiplier;
	private final double totalMultiplier;

	public MultiplierConfig(double flatBonus, double levelMultiplier, double totalMultiplier) {
		this.flatBonus = flatBonus;
		this.levelMultiplier = levelMultiplier;
		this.totalMultiplier = totalMultiplier;
	}

	public MultiplierConfig(ConfigurationSection config) {
		if (config == null) {
			flatBonus = 0;
			levelMultiplier = 1;
			totalMultiplier = 1;
			return;
		}
		this.flatBonus = config.getDouble("flatBonus", 0);
		this.levelMultiplier = config.getDouble("levelMultiplier", 1.0);
		this.totalMultiplier = config.getDouble("totalMultiplier", 1.0);
	}

	public double apply(int level) {
		return (flatBonus + levelMultiplier * ((double) level)) * totalMultiplier;
	}

	public double apply(double preExisting, int level) {
		return (preExisting + flatBonus + levelMultiplier * ((double) level)) * totalMultiplier;
	}
}
