package com.github.maxopoly.WurstCivTools.enchantment;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.maxopoly.WurstCivTools.misc.Util;

import org.bukkit.ChatColor;

public class CustomEnchantmentManager {

	/**
	 * Removes all custom and vanilla enchantments fron an item.
	 * 
	 * @param stack
	 * @return
	 */
	public static ItemStack removeAllEnchantments(ItemStack stack) {
		stack = removeVanillaEnchantments(stack);
		stack = removeLoreEnchantments(stack);
		return stack;
	}

	/**
	 * Removes all vanilla enchants from an item.
	 * 
	 * @param stack
	 * @return
	 */
	private static ItemStack removeVanillaEnchantments(ItemStack stack) {
		ItemMeta meta = stack.getItemMeta();

		for (Enchantment e : meta.getEnchants().keySet()) {
			if (!e.equals(Enchantment.DAMAGE_ALL) && !e.equals(Enchantment.ARROW_DAMAGE)
					&& !e.equals(Enchantment.PROTECTION_ENVIRONMENTAL) && !e.equals(Enchantment.DIG_SPEED))
				meta.removeEnchant(e);
		}
		stack.setItemMeta(meta);
		return stack;
	}

	/**
	 * Removes all custom enchantments from an item.
	 * 
	 * @param stack
	 * @return
	 */
	private static ItemStack removeLoreEnchantments(ItemStack stack) {
		Map<CustomEnchantment, Integer> enchants = getLoreTagEnchantments(stack);
		ItemMeta meta = stack.getItemMeta();
		if (meta.hasLore()) {
			List<String> lore = meta.getLore();

			Bukkit.getLogger().info(ChatColor.RED + "Item has enchantments:");
			Util.printMap(enchants);

			for (CustomEnchantment ench : enchants.keySet()) {

				if (ench.equals(CustomEnchantment.INFUSION))
					continue;

				String level = Util.getRomanNumeral(enchants.get(ench));
				Bukkit.getLogger().info("toremove: " + ChatColor.GRAY + ench.getName() + " " + level);
				lore.remove(ChatColor.GRAY + ench.getName() + " " + level);
			}
			meta.setLore(lore);

			for (String line : lore) {
				Bukkit.getLogger().info(line);
			}
		}
		stack.setItemMeta(meta);
		return stack;
	}

	/**
	 * Manages custom enchantment addition.
	 * 
	 * @param stack ItemStack to add enchantment to.
	 * @param ench  CustomEnchantment to add.
	 * @param level Level of the enchantment.
	 * @return ItemStack with enchantment.
	 */
	public static ItemStack addCustomEnchantment(ItemStack stack, CustomEnchantment ench, int level) {
		ItemMeta meta = stack.getItemMeta();
		if (ench.isVanillaEnchant()) {
			meta.addEnchant(CustomEnchantment.getVanillaEquivalent(ench), level, true);
		} else {
			addLoreTagEnchantment(meta, ench, level);
		}
		stack.setItemMeta(meta);
		return stack;
	}

	/**
	 * Manages enchantment weight tables for all items.
	 * 
	 * @param stack
	 * @return
	 */
	public static Map<CustomEnchantment, Integer> getEnchantmentTable(ItemStack stack) {
		Map<CustomEnchantment, Integer> ret = new HashMap<CustomEnchantment, Integer>();

		if (Util.isDurable(stack)) {
			ret.put(CustomEnchantment.UNBREAKING, 1);
		}

		if (Util.isSword(stack)) {
			ret.put(CustomEnchantment.FIRE_ASPECT, 1);
			ret.put(CustomEnchantment.SMITE, 1);
			ret.put(CustomEnchantment.BANE_OF_ARTHROPODS, 1);
			ret.put(CustomEnchantment.KNOCKBACK, 1);
			ret.put(CustomEnchantment.LOOTING, 1);
			ret.put(CustomEnchantment.SWEEPING_EDGE, 1);

			ret.put(CustomEnchantment.LIFESTEAL, 1);
			ret.put(CustomEnchantment.RAGE, 1);
			ret.put(CustomEnchantment.CORROSIVE, 1);
			ret.put(CustomEnchantment.HELLFIRE, 1);
			ret.put(CustomEnchantment.SOUL_TAKER, 1);
			ret.put(CustomEnchantment.LIGHTBANE, 1);
			ret.put(CustomEnchantment.BERSERKING, 1);
			ret.put(CustomEnchantment.PLAGUEBANE, 1);
			ret.put(CustomEnchantment.AQUATIC_COMBATANT, 1);
			ret.put(CustomEnchantment.HEADHUNTER, 1);

		}

		if (Util.isArmor(stack)) {

			ret.put(CustomEnchantment.FIRE_PROTECTION, 1);
			ret.put(CustomEnchantment.PROJECTILE_PROTECTION, 1);
			ret.put(CustomEnchantment.BLAST_PROTECTION, 1);
			;
			ret.put(CustomEnchantment.UNBREAKING, 1);

			ret.put(CustomEnchantment.ENDURANCE, 1);
			// ret.put(CustomEnchantment.VIGOR, 1);
			ret.put(CustomEnchantment.VITALITY, 1);
			ret.put(CustomEnchantment.EVASIVE, 1);
			// ret.put(CustomEnchantment.LAST_STAND, 1);
			// ret.put(CustomEnchantment.DIVINE_INTERVENTION, 1);

		}

		if (Util.isHelm(stack)) {
			ret.put(CustomEnchantment.RESPIRATION, 1);
			ret.put(CustomEnchantment.AQUA_AFFINITY, 1);
			ret.put(CustomEnchantment.PROTECTOR_OF_THE_SANDS, 1);
		}

		if (Util.isChestplate(stack)) {
			ret.put(CustomEnchantment.TURTLE, 1);
			ret.put(CustomEnchantment.SURVIVALIST, 1);
			ret.put(CustomEnchantment.PROTECTOR_OF_THE_SANDS, 1);
			ret.put(CustomEnchantment.THORNS, 1);
			ret.put(CustomEnchantment.BLUNTING, 1);
		}

		if (Util.isLeggings(stack)) {
			ret.put(CustomEnchantment.SURVIVALIST, 1);

		}

		if (Util.isBoots(stack)) {
			ret.put(CustomEnchantment.FEATHER_FALLING, 1);
			ret.put(CustomEnchantment.DEPTH_STRIDER, 1);
		}

		if (Util.isTool(stack)) {
			ret.put(CustomEnchantment.FORTUNE, 1);
			ret.put(CustomEnchantment.SILK_TOUCH, 1);

			ret.put(CustomEnchantment.MUTANDIS, 1);
			ret.put(CustomEnchantment.NATURES_BOUNTY, 1);
		}

		if (Util.isPickaxe(stack)) {
			ret.put(CustomEnchantment.GOLD_AFFINITY, 1);
			ret.put(CustomEnchantment.IRON_AFFINITY, 1);
			ret.put(CustomEnchantment.AUTO_SMELT, 1);
			ret.put(CustomEnchantment.DEMOLISHING, 1);
			ret.put(CustomEnchantment.CRYSTAL_ATTUNEMENT, 1);
			ret.put(CustomEnchantment.EMERALD_RESONANCE, 1);
			ret.put(CustomEnchantment.STONEMASON, 1);
			ret.put(CustomEnchantment.PROFICIENT, 1);
			ret.put(CustomEnchantment.CRYSTAL_RESTORATION, 1);
			ret.put(CustomEnchantment.PROSPERITY, 1);
			ret.put(CustomEnchantment.UMBRAL, 1);

		}

		if (Util.isAxe(stack)) {
			ret.put(CustomEnchantment.TIMBER, 1);
			ret.put(CustomEnchantment.WOODSMAN, 1);
			ret.put(CustomEnchantment.CARPENTRY, 1);
			ret.put(CustomEnchantment.APPLESEED, 1);
		}

		if (Util.isBow(stack)) {
			ret.put(CustomEnchantment.PUNCH, 1);
			ret.put(CustomEnchantment.FLAME, 1);

			ret.put(CustomEnchantment.FAR_SHOT, 1);
			ret.put(CustomEnchantment.POINT_BLANK, 1);
			ret.put(CustomEnchantment.TRUE_SHOT, 1);
			ret.put(CustomEnchantment.HUNTERS_BLESSING, 1);
			ret.put(CustomEnchantment.HUNTERS_MARK, 1);
			ret.put(CustomEnchantment.MULTISHOT, 1);
		}

		if (Util.isHoe(stack)) {

			ret.put(CustomEnchantment.GREEN_THUMB, 1);

		}

		return ret;
	}

	/**
	 * Returns the max level of the enchantment specified.
	 * 
	 * @param e
	 * @return
	 */
	public static int getMaxLevel(CustomEnchantment e) {
		return e.getMaxLevel();
	}

	public static int getEnchantmentLevel(ItemStack stack, CustomEnchantment enchant) {
		Map<CustomEnchantment, Integer> enchants = getCustomEnchantments(stack);
		Integer level = enchants.get(enchant);
		if (level == null) {
			return -1;
		}
		return level;
	}

	/**
	 * Returns all enchantments on an item.
	 * 
	 * @param stack
	 * @return
	 */
	public static Map<CustomEnchantment, Integer> getAllEnchantments(ItemStack stack) {
		Map<CustomEnchantment, Integer> ret = new HashMap<CustomEnchantment, Integer>();
		ret.putAll(getVanillaEnchantments(stack));
		ret.putAll(getLoreTagEnchantments(stack));
		return ret;
	}

	/**
	 * Gets all vanilla enchantments on an item
	 * 
	 * @param stack ItemStack to get enchants for
	 * @return Map of all vanilla enchants on the item as key and their respective
	 *         level as value in a map
	 */
	private static Map<CustomEnchantment, Integer> getVanillaEnchantments(ItemStack stack) {
		Map<CustomEnchantment, Integer> ret = new HashMap<CustomEnchantment, Integer>();
		if (stack.hasItemMeta()) {
			ItemMeta meta = stack.getItemMeta();
			Map<Enchantment, Integer> enchants = meta.getEnchants();
			for (Enchantment e : enchants.keySet()) {
				ret.put(CustomEnchantment.toCustomEnchantment(e), enchants.get(e));
			}
		}
		return ret;
	}

	/**
	 * Gets all custom lore tag enchantments on an item
	 * 
	 * @param stack ItemStack to get enchants for
	 * @return Map of all lore tag enchants on the item as key and their respective
	 *         level as value in a map
	 */
	public static Map<CustomEnchantment, Integer> getLoreTagEnchantments(ItemStack stack) {
		Map<CustomEnchantment, Integer> ret = new HashMap<CustomEnchantment, Integer>();
		ItemMeta meta = stack.getItemMeta();
		List<String> lore = meta.getLore();

		// fix this set to remove vanilla enchants
		EnumSet<CustomEnchantment> enchantments = EnumSet.allOf(CustomEnchantment.class);
		// Bukkit.getLogger().info(enchantments.toString());
		for (CustomEnchantment ench : enchantments) {
			if (Util.contiansPrefixTag(meta, ChatColor.GRAY + ench.getName())) {

				String temp = Util.getLevelFromPrefixTag(meta, ChatColor.GRAY + ench.getName() + " ");
				ret.put(ench, Util.fromNumeral(temp));
			}
		}

		return ret;
	}
}
