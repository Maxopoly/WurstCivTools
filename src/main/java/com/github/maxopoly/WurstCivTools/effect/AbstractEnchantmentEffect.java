package com.github.maxopoly.WurstCivTools.effect;

import org.bukkit.inventory.ItemStack;

import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;
import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantmentManager;

public abstract class AbstractEnchantmentEffect extends WurstEffect {

	public abstract CustomEnchantment getEnchant();

	protected int getEnchantLevel(ItemStack stack) {
		return CustomEnchantmentManager.getEnchantmentLevel(stack, getEnchant());
	}

}
