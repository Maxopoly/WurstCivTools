package com.github.maxopoly.WurstCivTools;

import static vg.civcraft.mc.civmodcore.util.ConfigParsing.parseItemMapDirectly;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;

import com.github.maxopoly.WurstCivTools.anvil.AnvilHandler;
import com.github.maxopoly.WurstCivTools.effect.AbstractEnchantmentEffect;
import com.github.maxopoly.WurstCivTools.effect.WurstEffect;
import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantManager;
import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;
import com.github.maxopoly.WurstCivTools.enchantment.EnchantableType;
import com.github.maxopoly.WurstCivTools.enchantment.EnchantableTypeManager;
import com.github.maxopoly.WurstCivTools.tags.EnchantmentLoreTag;
import com.github.maxopoly.WurstCivTools.tags.Tag;

import vg.civcraft.mc.civmodcore.itemHandling.ItemMap;

public class ConfigParser {
	private WurstCivTools plugin;
	private TagManager manager;
	private AnvilHandler anvilHandler;

	public ConfigParser() {
		plugin = WurstCivTools.getPlugin();
	}

	public TagManager parse() {
		plugin.saveDefaultConfig();
		plugin.reloadConfig();
		FileConfiguration config = plugin.getConfig();
		EnchantableTypeManager typeMan = parseEnchantableTypes(config.getConfigurationSection("enchantableTypes"));
		CustomEnchantManager enchantMan = parseEnchantments(config.getConfigurationSection("enchants"), typeMan);
		manager = parseEffects(enchantMan, config.getConfigurationSection("effects"));
		parseCustomAnvilFunctionality(config.getConfigurationSection("anvil"));
		plugin.info("Parsed complete config");
		return manager;
	}

	public TagManager parseEffects(CustomEnchantManager enchantMan, ConfigurationSection config) {
		TagManager manager = new TagManager();
		if (config == null) {
			plugin.warning("No effect section found");
			return manager;
		}
		if (enchantMan == null) {
			plugin.warning("Could not parse any effects, because no enchants were specified");
			return manager;
		}
		EffectTypeManager effectTypeMan = new EffectTypeManager();
		for (String key : config.getKeys(false)) {
			if (!config.isConfigurationSection(key)) {
				plugin.warning("Skipped missplaced entry " + key + " at " + config.getCurrentPath());
				continue;
			}
			ConfigurationSection current = config.getConfigurationSection(key);
			if (!current.isString("type")) {
				plugin.warning("Skipping effect entry " + current.getCurrentPath() + ", because no type was specified");
				continue;
			}
			String typeString = current.getString("type");
			WurstEffect effect = effectTypeMan.getNewEffectInstance(typeString);
			if (effect == null) {
				plugin.warning("Skipping effect entry " + current.getCurrentPath() + ", its effect type was not known");
				continue;
			}
			if (!current.isString("enchant")) {
				plugin.warning(
						"Skipping effect entry " + current.getCurrentPath() + ", because no enchant was specified");
				continue;
			}
			String enchantString = current.getString("enchant");
			CustomEnchantment enchant = enchantMan.getEnchantment(enchantString);
			if (enchant == null) {
				plugin.warning("Skipping effect entry " + current.getCurrentPath() + ", because enchant with name "
						+ enchantString + " did not exist");
				continue;
			}
			((AbstractEnchantmentEffect) effect).setEnchant(enchant);
			ConfigurationSection parameterSection;
			if (!current.isConfigurationSection("parameter")) {
				// just an empty one
				current.createSection("parameter");
			}
			parameterSection = current.getConfigurationSection("parameter");
			effect.parseParameter(parameterSection);
			Tag tag = new EnchantmentLoreTag(enchant);
			tag.setEffect(effect);
			manager.addTag(tag);
		}
		return manager;
	}

	public CustomEnchantManager parseEnchantments(ConfigurationSection config, EnchantableTypeManager typeManager) {
		CustomEnchantManager man = new CustomEnchantManager();
		if (config == null) {
			plugin.warning("No enchantment section found");
			return man;
		}
		if (typeManager == null) {
			plugin.warning("Could not parse any enchantments, because no enchantable types were specified");
			return man;
		}
		for (String key : config.getKeys(false)) {
			if (!config.isConfigurationSection(key)) {
				plugin.warning("Skipped missplaced entry " + key + " at " + config.getCurrentPath());
				continue;
			}
			ConfigurationSection current = config.getConfigurationSection(key);
			if (!current.isString("id")) {
				plugin.warning("Skipping enchantment entry " + current.getCurrentPath() + " due to a lack of an id");
				continue;
			}
			String id = current.getString("id");
			if (!current.isString("name")) {
				plugin.warning("Skipping enchantment entry " + current.getCurrentPath() + " due to a lack of a name");
				continue;
			}
			String name = current.getString("name");
			int maxLevel = current.getInt("maxLevel", 1);
			if (!current.isList("allowedTypes")) {
				plugin.warning("Skipping enchantment entry " + current.getCurrentPath()
						+ ", because it has no applicable types");
				continue;
			}
			List<String> typeList = current.getStringList("allowedTypes");
			List<EnchantableType> parsedTypes = new LinkedList<>();
			for (String entry : typeList) {
				try {
					EnchantableType type = typeManager.getEnchantment(entry);
					parsedTypes.add(type);
				} catch (IllegalArgumentException e) {
					plugin.warning("Could not parse material " + entry + " at " + current.getCurrentPath());
					continue;
				}
			}
			Enchantment vanillaEquivalent = null;
			if (current.isString("vanilla")) {
				vanillaEquivalent = Enchantment.getByName(current.getString("vanilla").toUpperCase().trim());
			}
			CustomEnchantment enchant = new CustomEnchantment(id, name, maxLevel, parsedTypes, vanillaEquivalent);
			man.registerEnchantment(enchant);
		}
		return man;
	}

	public EnchantableTypeManager parseEnchantableTypes(ConfigurationSection config) {
		EnchantableTypeManager man = new EnchantableTypeManager();
		if (config == null) {
			plugin.warning("No enchantable type section found");
			return man;
		}
		for (String key : config.getKeys(false)) {
			if (!config.isConfigurationSection(key)) {
				plugin.warning("Skipped missplaced entry " + key + " at " + config.getCurrentPath());
				continue;
			}
			ConfigurationSection current = config.getConfigurationSection(key);
			if (!current.isString("id")) {
				plugin.warning(
						"Skipping enchantable type entry " + current.getCurrentPath() + " due to a lack of an id");
				continue;
			}
			String id = current.getString("id");
			if (!current.isList("materials")) {
				plugin.warning("Skipping enchantable type entry " + current.getCurrentPath()
						+ ", because no materials were specified");
				continue;
			}
			List<String> matStringList = current.getStringList("materials");
			List<Material> parsedMats = new LinkedList<>();
			for (String entry : matStringList) {
				try {
					Material mat = Material.valueOf(entry.toUpperCase().trim());
					parsedMats.add(mat);
				} catch (IllegalArgumentException e) {
					plugin.warning("Could not parse material " + entry + " at " + current.getCurrentPath());
					continue;
				}
			}
			EnchantableType type = new EnchantableType(id, parsedMats);
			man.registerType(type);
		}
		return man;
	}

	public void parseCustomAnvilFunctionality(ConfigurationSection config) {
		if (config == null) {
			return;
		}
		ConfigurationSection materialSection = config.getConfigurationSection("repairMaterials");
		if (materialSection == null) {
			plugin.warning("Could not find repair material section, skipping enabling custom anvil functionality");
			return;
		}
		boolean enabled = config.getBoolean("enabled", true);
		if (!enabled) {
			plugin.warning("Custom anvil functionality is disabled");
			return;
		}
		Map<ItemMap, Double> repairValues = new HashMap<ItemMap, Double>();
		for (String key : materialSection.getKeys(false)) {
			ConfigurationSection current = materialSection.getConfigurationSection(key);
			ItemMap item = parseItemMapDirectly(current.getConfigurationSection("item"));
			if (item.getTotalItemAmount() == 0) {
				plugin.warning(
						"No item specified for custom repair value specification at " + current.getCurrentPath());
				continue;
			}
			double value = current.getDouble("value", 1.0);
			repairValues.put(item, value);
		}

		ConfigurationSection enchantSection = config.getConfigurationSection("enchantCosts");
		if (enchantSection == null) {
			plugin.warning("Could not find enchants section, skipping enabling custom anvil functionality");
			return;
		}
		Map<Enchantment, Double> enchantCosts = new HashMap<Enchantment, Double>();
		for (String key : enchantSection.getKeys(false)) {
			ConfigurationSection current = enchantSection.getConfigurationSection(key);
			if (current == null) {
				plugin.warning("Found invalid value " + key + " in anvil enchant value section");
				continue;
			}
			String enchantName = current.getString("enchant");
			if (enchantName == null) {
				plugin.warning("No enchant specified for custom enchant weight at " + current.getCurrentPath()
						+ ". Skipping it");
				continue;
			}
			Enchantment enchant = Enchantment.getByName(enchantName);
			if (enchant == null) {
				plugin.warning("Invalid enchant name found at " + current.getCurrentPath() + ". Skipping it");
				continue;
			}
			double value = current.getDouble("value", 1.0);
			enchantCosts.put(enchant, value);
		}
		ConfigurationSection loreSection = config.getConfigurationSection("loreCosts");
		Map<String, Double> loreCosts = new HashMap<String, Double>();
		if (loreSection != null) {
			for (String key : loreSection.getKeys(false)) {
				ConfigurationSection current = loreSection.getConfigurationSection(key);
				if (current == null) {
					plugin.warning("Found invalid value " + key + " in anvil lore value section");
					continue;
				}
				String lore = current.getString("lore");
				if (lore == null) {
					plugin.warning("No lore specified for custom lore weight at " + current.getCurrentPath()
							+ ". Skipping it");
					continue;
				}
				double value = current.getDouble("value", 1.0);
				loreCosts.put(lore, value);
			}
		}
		ConfigurationSection matCostSection = config.getConfigurationSection("materialCosts");
		Map<Material, Double> matCosts = new HashMap<Material, Double>();
		if (matCostSection != null) {
			for (String key : matCostSection.getKeys(false)) {
				ConfigurationSection current = matCostSection.getConfigurationSection(key);
				if (current == null) {
					plugin.warning("Found invalid value " + key + " in anvil material value section");
					continue;
				}
				String matName = current.getString("material");
				if (matName == null) {
					plugin.warning("No material specified for material anvil weight at " + current.getCurrentPath()
							+ ". Skipping it");
					continue;
				}
				Material mat;
				try {
					mat = Material.valueOf(matName);
				} catch (IllegalArgumentException e) {
					plugin.warning("Found invalid material " + matName + "specified for material anvil weight at "
							+ current.getCurrentPath() + ". Skipping it");
					continue;
				}
				double value = current.getDouble("value", 1.0);
				matCosts.put(mat, value);
			}
		}
		List<String> blacklisted = config.getStringList("blacklistedLore");
		boolean scaleWithMissingDura = config.getBoolean("scaleWithMissingDura", true);
		anvilHandler = new AnvilHandler(repairValues, enchantCosts, loreCosts, matCosts, scaleWithMissingDura,
				blacklisted);
	}

	public AnvilHandler getAnvilHandler() {
		return anvilHandler;
	}

}
