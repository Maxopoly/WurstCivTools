package com.github.maxopoly.WurstCivTools.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;

public class Util {

	private static final Set<Material> swords = new HashSet<>(Arrays.asList(new Material[] { Material.DIAMOND_SWORD,
			Material.IRON_SWORD, Material.GOLD_SWORD, Material.STONE_SWORD, Material.WOOD_SWORD }));

	private static final Set<Material> armors = new HashSet<>(Arrays.asList(new Material[] { Material.DIAMOND_HELMET,
			Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.CHAINMAIL_HELMET,
			Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.IRON_HELMET,
			Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.GOLD_HELMET,
			Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, Material.LEATHER_HELMET,
			Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS }));

	private static final Set<Material> pickaxes = new HashSet<>(Arrays.asList(new Material[] { Material.DIAMOND_PICKAXE,
			Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.STONE_PICKAXE, Material.WOOD_PICKAXE }));

	private static final Set<Material> axes = new HashSet<>(Arrays.asList(new Material[] { Material.DIAMOND_AXE,
			Material.IRON_AXE, Material.GOLD_AXE, Material.STONE_AXE, Material.WOOD_AXE }));

	private static final Set<Material> hoes = new HashSet<>(Arrays.asList(new Material[] { Material.DIAMOND_HOE,
			Material.IRON_HOE, Material.GOLD_HOE, Material.STONE_HOE, Material.WOOD_HOE }));

	private static final Set<Material> shovels = new HashSet<>(Arrays.asList(new Material[] { Material.DIAMOND_SPADE,
			Material.IRON_SPADE, Material.GOLD_SPADE, Material.STONE_SPADE, Material.WOOD_SPADE }));

	private static final Set<Material> helmets = new HashSet<>(Arrays.asList(new Material[] { Material.DIAMOND_HELMET,
			Material.IRON_HELMET, Material.GOLD_HELMET, Material.LEATHER_HELMET }));

	private static final Set<Material> boots = new HashSet<>(Arrays.asList(new Material[] { Material.DIAMOND_BOOTS,
			Material.IRON_BOOTS, Material.GOLD_BOOTS, Material.LEATHER_BOOTS }));

	private static final Set<Material> chestplates = new HashSet<>(
			Arrays.asList(new Material[] { Material.DIAMOND_CHESTPLATE, Material.IRON_CHESTPLATE,
					Material.GOLD_CHESTPLATE, Material.LEATHER_CHESTPLATE }));

	private static final Set<Material> leggings = new HashSet<>(Arrays.asList(new Material[] {
			Material.DIAMOND_LEGGINGS, Material.IRON_LEGGINGS, Material.GOLD_LEGGINGS, Material.LEATHER_LEGGINGS }));

	public static ItemStack generateItem(String s, int amt) {
		ItemStack stack = null;
		if (s.equalsIgnoreCase("EMERALD_FRAGMENT")) {
			stack = new ItemStack(Material.IRON_NUGGET, amt);
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName(ChatColor.YELLOW + "Emerald Fragment");
			ArrayList<String> lore = new ArrayList<>();
			lore.add(ChatColor.BLUE + "Combine 9 to make an emerald.");
			meta.setLore(lore);
			stack.setItemMeta(meta);
		}
		if (s.equalsIgnoreCase("IRON_FRAGMENT")) {
			stack = new ItemStack(Material.IRON_NUGGET, amt);
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName(ChatColor.YELLOW + "Iron Ore Fragment");
			ArrayList<String> lore = new ArrayList<>();
			lore.add(ChatColor.BLUE + "Combine 4 to make an Iron Ore Chunk, then smelt.");
			meta.setLore(lore);
			stack.setItemMeta(meta);
		}
		if (s.equalsIgnoreCase("GOLD_FRAGMENT")) {
			stack = new ItemStack(Material.IRON_NUGGET, amt);
			ItemMeta meta = stack.getItemMeta();
			meta.setDisplayName(ChatColor.YELLOW + "Gold Ore Fragment");
			ArrayList<String> lore = new ArrayList<>();
			lore.add(ChatColor.BLUE + "Combine 4 to make a Gold Ore Chunk, then smelt.");
			meta.setLore(lore);
			stack.setItemMeta(meta);
		}
		return stack;
	}

	public static void reducePotionDuration(Player p, PotionEffectType effect, int duration) {

		for (PotionEffect reducedEffect : p.getActivePotionEffects()) {

			if (reducedEffect.getType() == effect) {

				PotionEffect newPotion = new PotionEffect(effect, reducedEffect.getDuration() - duration,
						reducedEffect.getAmplifier());
				p.removePotionEffect(reducedEffect.getType());
				p.addPotionEffect(newPotion);
			}
		}
	}

	public static boolean isDurable(ItemStack stack) {
		return isTool(stack) || isArmor(stack) || isSword(stack) || isTool(stack) || isBow(stack);
	}

	public static boolean isBow(ItemStack stack) {
		return stack.getType() == Material.BOW;
	}

	public static boolean isTool(ItemStack stack) {
		return isPickaxe(stack) || isAxe(stack) || isHoe(stack) || isShovel(stack);
	}

	public static boolean isSword(ItemStack stack) {
		return swords.contains(stack.getType());
	}

	public static boolean isHelm(ItemStack stack) {
		return helmets.contains(stack.getType());
	}

	public static boolean isChestplate(ItemStack stack) {
		return chestplates.contains(stack.getType());
	}

	public static boolean isLeggings(ItemStack stack) {
		return leggings.contains(stack.getType());
	}

	public static boolean isBoots(ItemStack stack) {
		return boots.contains(stack.getType());
	}

	public static boolean isPickaxe(ItemStack stack) {
		return pickaxes.contains(stack.getType());
	}

	public static boolean isAxe(ItemStack stack) {
		return axes.contains(stack.getType());
	}

	public static boolean isHoe(ItemStack stack) {
		return hoes.contains(stack.getType());
	}

	public static boolean isShovel(ItemStack stack) {
		return shovels.contains(stack.getType());
	}

	public static boolean isArmor(ItemStack stack) {
		return armors.contains(stack.getType());
	}

	public static boolean decrementOffhand(Player p) {
		if (p.getInventory().getItemInOffHand() != null) {
			ItemStack stack = p.getInventory().getItemInOffHand();
			stack.setAmount(stack.getAmount() - 1);
			p.getInventory().setItemInOffHand(stack);
		}

		if (p.getInventory().getItemInOffHand() != null) {
			return true;
		} else
			return false;
	}

	public static boolean chance(int attempts, int bound) {
		if (attempts >= bound) {
			return true;
		}
		Random rand = new Random();
		if (rand.nextInt(bound) <= attempts - 1) {
			return true;
		} else
			return false;
	}

	public static String getRomanNumeral(int i) {
		switch (i) {
		case 1:
			return "I";
		case 2:
			return "II";
		case 3:
			return "III";
		case 4:
			return "IV";
		case 5:
			return "V";
		case 6:
			return "VI";
		case 7:
			return "VII";
		case 8:
			return "VIII";
		case 9:
			return "IX";
		case 10:
			return "X";
		case 11:
			return "XI";
		default:
			return "0";
		}
	}

	public static int fromNumeral(String numeral) {
		switch (numeral) {
		case "I":
			return 1;
		case "II":
			return 2;
		case "III":
			return 3;
		case "IV":
			return 4;
		case "V":
			return 5;
		case "VI":
			return 6;
		case "VII":
			return 7;
		case "VIII":
			return 8;
		case "IX":
			return 9;
		case "X":
			return 10;
		case "XI":
			return 11;
		default:
			return 0;
		}
	}

	public static void printMap(Map<CustomEnchantment, Integer> map) {
		Bukkit.getLogger().info("CustomEnchantment map contains: ");
		for (CustomEnchantment e : map.keySet()) {
			Bukkit.getLogger().info(e.getName() + ": " + map.get(e));
		}
	}
}
