package com.github.maxopoly.WurstCivTools.enchantment;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;

public class EnchantableType {
	
	private String identifier;
	private Set<Material> allowedMats;
	
	public EnchantableType(String identifier, Collection<Material> allowedMats) {
		this.allowedMats = new HashSet<>(allowedMats);
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public boolean isAllowed(Material mat) {
		return allowedMats.contains(mat);
	}
	
	public Material getRepresentative() {
		if (allowedMats.isEmpty()) {
			return null;
		}
		return allowedMats.iterator().next();
	}
	
	public Set<Material> getAllMaterials() {
		return new HashSet<>(allowedMats);
	}

}
