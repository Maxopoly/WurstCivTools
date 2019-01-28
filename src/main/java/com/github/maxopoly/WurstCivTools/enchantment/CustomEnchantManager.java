package com.github.maxopoly.WurstCivTools.enchantment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomEnchantManager {
	
	private Map<String, CustomEnchantment> enchantmentsById;
	
	public CustomEnchantManager() {
		this.enchantmentsById = new HashMap<String, CustomEnchantment>();
	}
	
	public CustomEnchantment getEnchantment(String id) {
		return enchantmentsById.get(id);
	}
	
	public void registerEnchantment(CustomEnchantment enchant) {
		enchantmentsById.put(enchant.getIdentifier(), enchant);
	}
	
	public Collection<CustomEnchantment> getAllEnchants() {
		return enchantmentsById.values();
	}

}
