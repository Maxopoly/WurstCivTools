package com.github.maxopoly.WurstCivTools.effect;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;
import com.github.maxopoly.WurstCivTools.misc.ChanceConfig;
import com.github.maxopoly.WurstCivTools.tags.EnchantmentLoreTag;

public abstract class AbstractEnchantmentEffect extends WurstEffect {

	protected CustomEnchantment enchant;
	protected ChanceConfig chanceConfig;

	public CustomEnchantment getEnchant() {
		return enchant;
	}

	protected int getEnchantLevel(ItemStack stack) {
		return new EnchantmentLoreTag(enchant).getLevel(stack);
	}
	
	protected int getEnchantLevel(ItemStack stack, CustomEnchantment cEnchant) {
		return new EnchantmentLoreTag(cEnchant).getLevel(stack);
	}

	/**
	 * Should only be used during config parsing. Sets which enchant this effect is
	 * tied to
	 * 
	 * @param enchant Enchant to apply the effect for
	 */
	public void setEnchant(CustomEnchantment enchant) {
		this.enchant = enchant;
	}
	
	public boolean parseParameter(ConfigurationSection config) {
		chanceConfig = new ChanceConfig(config);
		return parseParameters(config);
	}
	
	public abstract boolean parseParameters(ConfigurationSection config);

}
