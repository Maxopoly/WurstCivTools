package com.github.maxopoly.WurstCivTools.effect.orbs;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import com.github.maxopoly.WurstCivTools.WurstCivTools;
import com.github.maxopoly.WurstCivTools.enchantment.AppliedCustomEnchantment;
import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;
import com.github.maxopoly.WurstCivTools.enchantment.EnchantableType;
import com.github.maxopoly.WurstCivTools.tags.EnchantmentLoreTag;

public class EnchantingOrb extends AbstractOrbEffect {

	private EnchantableType applicableType;
	private Map<AppliedCustomEnchantment, Integer> enchantWeight;
	private int enchantsApplied;

	@Override
	protected void apply(ItemStack orb, ItemStack toApplyTo) {
		for (int i = 0; i < enchantsApplied; i++) {
			AppliedCustomEnchantment enchant = getEnchantToApply(toApplyTo);
			if (enchant == null) {
				logger.warning("Failed to find enchant to apply to " + toApplyTo.toString());
				continue;
			}
		}
	}

	private AppliedCustomEnchantment getEnchantToApply(ItemStack toApplyTo) {
		TreeMap<Integer, AppliedCustomEnchantment> possibleEnchants = new TreeMap<>();
		int sum = 0;
		for (Entry<AppliedCustomEnchantment, Integer> entry : enchantWeight.entrySet()) {
			int existingLevel = new EnchantmentLoreTag(entry.getKey().getEnchant()).getLevel(toApplyTo);
			if (existingLevel == 0) {
				possibleEnchants.put(sum, entry.getKey());
				sum += entry.getValue();
			}
		}
		return possibleEnchants.floorEntry(rng.nextInt(sum)).getValue();
	}

	@Override
	public boolean parseParameter(ConfigurationSection config) {
		String typeString = config.getString("appliesTo");
		if (typeString == null) {
			logger.warning("No applicable type specified at " + config.getCurrentPath());
			return false;
		}
		applicableType = WurstCivTools.getPlugin().getEnchantableTypeManager().getEnchantment(typeString);
		if (applicableType == null) {
			logger.warning("Applicable type specified at " + config.getCurrentPath() + " was not known");
			return false;
		}
		if (!config.isConfigurationSection("chances")) {
			logger.warning("No chances specified at " + config.getCurrentPath());
			return false;
		}
		enchantsApplied = config.getInt("amount", 1);
		ConfigurationSection chanceSection = config.getConfigurationSection("chances");
		enchantWeight = new HashMap<AppliedCustomEnchantment, Integer>();
		for (String key : chanceSection.getKeys(false)) {
			if (!chanceSection.isConfigurationSection(key)) {
				continue;
			}
			ConfigurationSection current = chanceSection.getConfigurationSection(key);
			String enchantString = current.getString("enchant");
			if (enchantString == null) {
				logger.warning("No enchant specified at " + config.getCurrentPath() + ", skipped");
				continue;
			}
			CustomEnchantment enchant = WurstCivTools.getPlugin().getCustomEnchantManager()
					.getEnchantment(enchantString);
			if (enchant == null) {
				logger.warning("Enchant specified at " + config.getCurrentPath() + " was not known, skipped");
				continue;
			}
			int level = current.getInt("level", 1);
			int weight = current.getInt("weight", 1);
			AppliedCustomEnchantment appEnchant = new AppliedCustomEnchantment(enchant, level);
			enchantWeight.put(appEnchant, weight);
		}
		return true;
	}

	@Override
	public String getIdentifier() {
		return "ENCHANTING_ORB";
	}

}
