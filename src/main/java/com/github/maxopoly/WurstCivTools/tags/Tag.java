package com.github.maxopoly.WurstCivTools.tags;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.inventory.ItemStack;

import com.github.maxopoly.WurstCivTools.effect.WurstEffect;
import com.github.maxopoly.WurstCivTools.enchantment.EnchantableType;

public abstract class Tag {
	
	protected Set<EnchantableType> materials;
	protected WurstEffect effect;
	
	public Tag(Collection<EnchantableType> mats) {
		materials = new HashSet<>(mats);
	}
	
	public abstract boolean appliedOn(ItemStack is);
	
	public Set<EnchantableType> getApplicableTypes() {
		return materials;
	}
	
	public WurstEffect getEffect() {
		return effect;
	}
	
	public void setEffect(WurstEffect effect) {
		this.effect = effect;
	}

}
