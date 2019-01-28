package com.github.maxopoly.WurstCivTools.effect;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

public class SoulTakerEffect extends AbstractEnchantmentEffect {

	private String resetMessage;

	public void handleKillPlayerForHeldItem(Player attacker, Player victim, PlayerDeathEvent e) {
		if (victim.getBedSpawnLocation() != null) {
			victim.setBedSpawnLocation(null);
			victim.sendMessage(resetMessage);
		}
	}

	@Override
	public boolean parseParameters(ConfigurationSection config) {
		resetMessage = config.getString("resetMessage", ChatColor.RED + "You have forgotten your bed!");
		return true;
	}

	@Override
	public String getIdentifier() {
		return "BED_REST_ON_KILL";
	}
}
