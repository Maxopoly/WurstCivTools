package com.github.maxopoly.WurstCivTools.effect;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;

public class SoulTakerEffect extends AbstractEnchantmentEffect {
	
	public void handleKillPlayerForHeldItem(Player attacker, Player victim, PlayerDeathEvent e) {
		if (victim.getBedSpawnLocation() != null) {
			victim.setBedSpawnLocation(null);
			victim.sendMessage(ChatColor.RED + "You have forgotten your bed!");
		}
	}

	@Override
	public CustomEnchantment getEnchant() {
		return CustomEnchantment.SOUL_TAKER;
	}
}
