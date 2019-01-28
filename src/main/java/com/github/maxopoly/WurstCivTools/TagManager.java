package com.github.maxopoly.WurstCivTools;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.github.maxopoly.WurstCivTools.effect.WurstEffect;
import com.github.maxopoly.WurstCivTools.enchantment.EnchantableType;
import com.github.maxopoly.WurstCivTools.tags.Tag;

public class TagManager {
	private WurstCivTools plugin;
	private Map<Material, List<Tag>> tags;
	private Set<Tag> allTags;

	public TagManager() {
		this.plugin = WurstCivTools.getPlugin();
		tags = new TreeMap<Material, List<Tag>>();
		allTags = new HashSet<>();
	}

	public List<Tag> getTagsFor(Material m) {
		return tags.get(m);
	}

	public void addTag(Tag tag) {
		allTags.add(tag);
		for (EnchantableType enchType : tag.getApplicableTypes()) {
			for (Material mat : enchType.getAllMaterials()) {
				List<Tag> existing = tags.get(mat);
				if (existing == null) {
					existing = new LinkedList<Tag>();
					tags.put(mat, existing);
				}
				if (!existing.contains(tag)) {
					existing.add(tag);
				}
			}
		}
	}

	public Set<Tag> getAllTags() {
		return allTags;
	}

	public HashSet<Tag> getTagsFor(ItemStack is) {
		HashSet<Tag> retrievedTags = new HashSet<Tag>();
		if (is == null) {
			return retrievedTags;
		}
		List<Tag> typetags = tags.get(is.getType());
		if (typetags == null) {
			return retrievedTags;
		}
		for (Tag tag : typetags) {
			if (tag.appliedOn(is)) {
				retrievedTags.add(tag);
			}
		}
		return retrievedTags;
	}

	public Tag getEffectTag(WurstEffect effect) {
		for (List<Tag> taglist : tags.values()) {
			for (Tag tag : taglist) {
				if (tag.getEffect().equals(effect)) {
					return tag;
				}
			}
		}
		return null;
	}
}
