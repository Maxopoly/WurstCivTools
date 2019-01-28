package com.github.maxopoly.WurstCivTools.enchantment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class CustomEnchantment {

	private String identifier;
	private String niceName;
	private int maxLevel;
	private List<EnchantableType> allowedItemTypes;
	private Enchantment vanillaEquivalent;

	public CustomEnchantment(String id, String niceName, int maxLevel, List<EnchantableType> allowedItemTypes,
			Enchantment vanillaEquivalent) {
		this.identifier = id;
		this.niceName = niceName;
		this.maxLevel = maxLevel;
		this.allowedItemTypes = allowedItemTypes;
		this.vanillaEquivalent = vanillaEquivalent;
	}
	
	public boolean isVanillaEnchant() {
		return vanillaEquivalent != null;
	}
	
	public Enchantment getVanillaEquivalent() {
		return vanillaEquivalent;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getNiceName() {
		return niceName;
	}

	public boolean canBeAppliedTo(Material mat) {
		for (EnchantableType type : allowedItemTypes) {
			if (type.isAllowed(mat)) {
				return true;
			}
		}
		return false;
	}
	
	public List <EnchantableType> getAllEnchantableTypes() {
		return new ArrayList<>(allowedItemTypes);
	}

	public int getMaxLevel() {
		return maxLevel;
	}
	
	public int hashCode() {
		return identifier.hashCode();
	}

}
