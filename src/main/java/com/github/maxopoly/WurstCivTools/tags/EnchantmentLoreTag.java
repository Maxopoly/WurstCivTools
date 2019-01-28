package com.github.maxopoly.WurstCivTools.tags;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;
import com.github.maxopoly.WurstCivTools.misc.EnchantUtil;

public class EnchantmentLoreTag extends Tag{
	private CustomEnchantment enchant;
	private String name = ChatColor.GRAY + enchant.getNiceName();
	
	public EnchantmentLoreTag(CustomEnchantment enchant) {
		super(enchant.getAllEnchantableTypes());
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
	
	public int getLevel(ItemStack is) {
		if (is == null) {
			return 0;
		}
		ItemMeta im = is.getItemMeta();
		List <String> appliedLore = im.getLore();
		if (appliedLore == null) {
			return 0;
		}
		for(String s : appliedLore) {
			if (s.startsWith(name)) {
				String level = s.substring(name.length(), s.length());
				return EnchantUtil.fromNumeral(level);
			}
		}
		return 0;
	}
}
