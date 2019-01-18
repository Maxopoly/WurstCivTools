package com.github.maxopoly.WurstCivTools.effect;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;

public class HeadHunterEffect extends AbstractEnchantmentEffect {

	private static final int chanceBound = 99;

	public void handleKillNPCForHeldItem(Player attacker, Entity victim, EntityDeathEvent event) {
		ItemStack headDrop = null;
		if (!roll(attacker.getInventory().getItemInMainHand())) { // each lvl increments chance by 3%, so max lvl has 9%
																	// chance of drop
			return;
		}
		switch (victim.getType()) {
		case SKELETON:
			headDrop = new ItemStack(Material.SKULL_ITEM, 1, (short) 0);
			break;
		case ZOMBIE:
			headDrop = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
			break;
		case CREEPER:
			headDrop = new ItemStack(Material.SKULL_ITEM, 1, (short) 4);
			break;
		default:
			break;
		}
		if (headDrop != null) {
			event.getDrops().add(headDrop);
		}
	}

	@Override
	public void handleKillPlayerForHeldItem(Player attacker, Player victim, PlayerDeathEvent e) {
		if (!roll(attacker.getInventory().getItemInMainHand())) {
			return;
		}
		ItemStack drop = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

		// Make head appear to be killed's head
		SkullMeta skullMeta = (SkullMeta) drop.getItemMeta();
		skullMeta.setOwningPlayer(victim);
		skullMeta.setDisplayName(ChatColor.RED + victim.getName() + "s head");
		drop.setItemMeta(skullMeta);

		// The above method might be buggy
		// People online were having a hard time aswell, it seems

		e.getDrops().add(drop);
	}

	private boolean roll(ItemStack itemUsed) {
		int roll = rng.nextInt(chanceBound) + 1;
		int chance = getEnchantLevel(itemUsed);
		return roll <= chance;
	}

	@Override
	public CustomEnchantment getEnchant() {
		return CustomEnchantment.HEADHUNTER;
	}

}
