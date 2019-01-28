package com.github.maxopoly.WurstCivTools.effect;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.github.maxopoly.WurstCivTools.misc.MultiplierConfig;

public class LifestealEffect extends AbstractEnchantmentEffect {
	
	private MultiplierConfig multiplier;

	@Override
	public void handleDamagePlayerForHeldItem(Player attacker, Player victim, EntityDamageByEntityEvent e) {
		int level = getEnchantLevel(attacker.getInventory().getItemInMainHand());
		if (!chanceConfig.roll(level)) {
			return;
		}
		attacker.setHealth(Math.min(attacker.getMaxHealth(), multiplier.apply(attacker.getHealth(), level)));
	}

	@Override
	public boolean parseParameters(ConfigurationSection config) {
		multiplier = new MultiplierConfig(config);
		return true;
	}

	@Override
	public String getIdentifier() {
		return "LIFESTEAL";
	}

}
