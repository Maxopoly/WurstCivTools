package com.github.maxopoly.WurstCivTools.effect;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;

public class PointBlankEffect extends AbstractArrowTagEffect {
	
	private static final double maxDistance = 10;
	private static final double medDistance = 7;
	private static final double minDistance = 3;

	@Override
	public void arrowWithEnchantHit(Projectile projectile, int enchantLevel, EntityDamageByEntityEvent e) {
		double distance = ((Entity) (projectile.getShooter())).getLocation().distance(e.getEntity().getLocation());
		float dmg;
		if (distance <= minDistance) {
			dmg = enchantLevel + 2;
		}
		else if (distance <= medDistance) {
			dmg = enchantLevel + 1;
		}
		else if (distance <= maxDistance) {
			dmg = enchantLevel;
		}
		else {
			return;
		}
		e.setDamage(e.getDamage() + dmg);
	}

	@Override
	public CustomEnchantment getEnchant() {
		return CustomEnchantment.POINT_BLANK;
	}

}
