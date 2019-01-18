package com.github.maxopoly.WurstCivTools.effect;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;

public class FarShotEffect extends AbstractArrowTagEffect {
	
	private static final double maxDistance = 90;
	private static final double medDistance = 60;
	private static final double minDistance = 50;

	@Override
	public CustomEnchantment getEnchant() {
		return CustomEnchantment.FAR_SHOT;
	}

	@Override
	public void arrowWithEnchantHit(Projectile projectile, int enchantLevel, EntityDamageByEntityEvent e) {
		double distance = ((Entity) (projectile.getShooter())).getLocation().distance(e.getEntity().getLocation());
		float dmg;
		if (distance >= maxDistance) {
			dmg = 2 + 2*enchantLevel;
		}
		else if (distance >= medDistance) {
			dmg = 2 + enchantLevel;
		}
		else if (distance >= minDistance) {
			dmg = 1 + (0.5f * (float) enchantLevel);
		}
		else {
			return;
		}
		e.setDamage(e.getDamage() + dmg);
	}

}
