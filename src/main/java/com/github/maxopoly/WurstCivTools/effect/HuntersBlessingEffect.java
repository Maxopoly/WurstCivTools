package com.github.maxopoly.WurstCivTools.effect;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;
import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantmentManager;

public class HuntersBlessingEffect extends AbstractEnchantmentEffect {

	private Random rng = new Random();
	private static final int chanceBound = 60;

	public void handleKillNPCForHeldItem(Player attacker, Entity victim, EntityDeathEvent event) {
		if (victim instanceof Animals) {
			// TODO make stuff configurable
			int level = CustomEnchantmentManager
					.getEnchantmentLevel(attacker.getInventory().getItemInMainHand(),getEnchant());
			int roll = (rng.nextInt(chanceBound) + 1) / level;
			if (roll == 10) { // 1/60 chance on lvl 1, 1/30 on lvl 2, 1/15 on lvl 3
				for (ItemStack drop : event.getDrops()) {
					switch (drop.getType()) {
					case PORK:
						event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(),
								new ItemStack(Material.PORK, level));
						break;
					case RAW_BEEF:
						event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(),
								new ItemStack(Material.RAW_BEEF, level));
						break;
					case MUTTON:
						event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(),
								new ItemStack(Material.MUTTON, level));
						break;
					default:
						break;
					}

				}

			}
		}
	}

	@Override
	public CustomEnchantment getEnchant() {
		return CustomEnchantment.HUNTERS_BLESSING;
	}

}
