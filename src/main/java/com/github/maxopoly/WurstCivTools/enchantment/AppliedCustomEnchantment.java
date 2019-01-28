package com.github.maxopoly.WurstCivTools.enchantment;

import java.util.Objects;

public class AppliedCustomEnchantment {
	
	private int level;
	private CustomEnchantment enchant;
	
	public AppliedCustomEnchantment(CustomEnchantment enchant, int level) {
		this.enchant = enchant;
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public CustomEnchantment getEnchant() {
		return enchant;
	}
	
	public int hashCode() {
		return Objects.hash(level, enchant);
	}

}
