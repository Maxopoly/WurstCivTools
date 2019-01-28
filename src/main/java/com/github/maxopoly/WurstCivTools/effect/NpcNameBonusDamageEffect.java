package com.github.maxopoly.WurstCivTools.effect;

import java.util.regex.Pattern;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class NpcNameBonusDamageEffect extends AbstractEnchantmentEffect {
	
	private float bonusDamagePerLevel;
	private String regex;

	@Override
	public void handleDamageNPCForHeldItem(Player attacker, Entity victim, EntityDamageByEntityEvent e) {
		if (victim.getCustomName() == null) {
			return;
		}
		String name = victim.getCustomName();
		if (Pattern.matches(regex, name)) {
			int lvl = getEnchantLevel(attacker.getInventory().getItemInMainHand());
			e.setDamage(e.getDamage() + bonusDamagePerLevel * (float) lvl);
		}
	}


	@Override
	public boolean parseParameters(ConfigurationSection config) {
		bonusDamagePerLevel = (float) config.getDouble("damage", 0.5f);
		regex = config.getString("regex");
		if (regex == null) {
			logger.warning("Effect at " + config.getCurrentPath() + " had no regex");
			return false;
		}
		return true;
	}


	@Override
	public String getIdentifier() {
		return "NPC_BONUS_DAMAGE";
	}

}
