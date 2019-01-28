package com.github.maxopoly.WurstCivTools.effect;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadHunterEffect extends AbstractEnchantmentEffect {

	public void handleKillNPCForHeldItem(Player attacker, Entity victim, EntityDeathEvent event) {
		ItemStack headDrop = null;
		int level = getEnchantLevel(attacker.getInventory().getItemInMainHand());
		if (!chanceConfig.roll(level)) {
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
		int level = getEnchantLevel(attacker.getInventory().getItemInMainHand());
		if (!chanceConfig.roll(level)) {
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

	@Override
	public boolean parseParameters(ConfigurationSection config) {
		return true;
	}

	@Override
	public String getIdentifier() {
		return "HEADDROP";
	}

}
