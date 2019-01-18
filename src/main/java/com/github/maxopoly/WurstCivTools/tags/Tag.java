package com.github.maxopoly.WurstCivTools.tags;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.github.maxopoly.WurstCivTools.effect.WurstEffect;

public abstract class Tag {
	
	protected Set<Material> materials;
	protected WurstEffect effect;
	
	public Tag(Material m) {
		materials = new TreeSet<>();
		materials.add(m);
	}
	
	public Tag(Collection<Material> mats) {
		materials = new HashSet<>(mats);
	}
	
	public abstract boolean appliedOn(ItemStack is);
	
	public Set<Material> getMaterials() {
		return materials;
	}
	
	public WurstEffect getEffect() {
		return effect;
	}
	
	public void setEffect(WurstEffect effect) {
		this.effect = effect;
	}

}
