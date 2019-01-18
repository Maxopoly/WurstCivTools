package com.github.maxopoly.WurstCivTools.effect;

import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;

public class TrueShotEffect extends AbstractArrowTagEffect {

	@Override
	public void arrowWithEnchantHit(Projectile projectile, int enchantLevel, EntityDamageByEntityEvent e) {
		e.setDamage(e.getDamage() + 1.0f);
	}

	@Override
	public CustomEnchantment getEnchant() {
		return CustomEnchantment.TRUE_SHOT;
	}
	

}
