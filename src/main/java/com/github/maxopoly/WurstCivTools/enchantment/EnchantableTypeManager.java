package com.github.maxopoly.WurstCivTools.enchantment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EnchantableTypeManager {
	
	private Map<String, EnchantableType> enchantableTypesById;
	
	public EnchantableTypeManager() {
		this.enchantableTypesById = new HashMap<String, EnchantableType>();
	}
	
	public EnchantableType getEnchantment(String id) {
		return enchantableTypesById.get(id);
	}
	
	public void registerType(EnchantableType enchant) {
		enchantableTypesById.put(enchant.getIdentifier(), enchant);
	}
	
	public Collection<EnchantableType> getAllEnchantableTypes() {
		return enchantableTypesById.values();
	}

}

