package com.github.maxopoly.WurstCivTools.tags;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;

public class EnchantmentLoreTag extends Tag{
	private CustomEnchantment enchant;
	private String name = ChatColor.GRAY + enchant.getName();
	
	public EnchantmentLoreTag(Material mat, CustomEnchantment enchant) {
		super(mat);
		this.enchant = enchant;
	}
	
	public boolean appliedOn(ItemStack is) {
		if (is == null) {
			return false;
		}
		ItemMeta im = is.getItemMeta();
		List <String> appliedLore = im.getLore();
		if (appliedLore == null) {
			return false;
		}
		for(String s : appliedLore) {
			if (s.startsWith(name)) {
				return true;
			}
		}
		return false;
	}
}
