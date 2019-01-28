package com.github.maxopoly.WurstCivTools.effect;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.github.maxopoly.WurstCivTools.misc.ChanceConfig;
import com.github.maxopoly.WurstCivTools.misc.MultiplierConfig;

public class DropMultiplierEffect extends AbstractEnchantmentEffect {

	private MultiplierConfig multiplier;
	private ItemStack item;
	private ChanceConfig chanceConfig;

	public void handleKillNPCForHeldItem(Player attacker, Entity victim, EntityDeathEvent event) {
		int level = getEnchantLevel(attacker.getInventory().getItemInMainHand());
		if (!chanceConfig.roll(level)) {
			return;
		}
		for (ItemStack drop : event.getDrops()) {
			if (drop.isSimilar(item)) {
				ItemStack newDrop = new ItemStack(item);
				newDrop.setAmount((int)multiplier.apply(level));
				if (newDrop.getAmount() > 0) {
					event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), newDrop);
				}
				break;
			}
		}
	}

	@Override
	public boolean parseParameters(ConfigurationSection config) {
		multiplier = new MultiplierConfig(config);
		item = config.getItemStack("item");
		return true;
	}

	@Override
	public String getIdentifier() {
		return "DROP_MULTIPLIER";
	}

}
