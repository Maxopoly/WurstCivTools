package com.github.maxopoly.WurstCivTools.effect;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ArrowDistanceDamageEffect extends AbstractArrowTagEffect {

	private List<BonusDamage> damageModifiers;

	@Override
	public void arrowWithEnchantHit(Projectile projectile, int enchantLevel, EntityDamageByEntityEvent e) {
		double distance = ((Entity) (projectile.getShooter())).getLocation().distance(e.getEntity().getLocation());
		for (BonusDamage modi : damageModifiers) {
			if (distance >= modi.minRange && distance <= modi.maxRange) {
				e.setDamage(modi.apply(e.getDamage(), enchantLevel));
				break;
			}
		}
	}

	@Override
	public boolean parseParameters(ConfigurationSection config) {
		ConfigurationSection ranges = config.getConfigurationSection("ranges");
		if (ranges == null) {
			logger.warning("Effect at " + config.getCurrentPath() + " had no ranges");
			return false;
		}
		damageModifiers = new LinkedList<ArrowDistanceDamageEffect.BonusDamage>();
		for (String key : ranges.getKeys(false)) {
			if (!ranges.isConfigurationSection(key)) {
				continue;
			}
			ConfigurationSection current = ranges.getConfigurationSection(key);
			double minRange = current.getDouble("minRange", 0.0);
			double maxRange = current.getDouble("maxRange", Double.MAX_VALUE);
			double enchantLevelMultiplier = current.getDouble("levelMultiplier", 0.5);
			double flatBonus = current.getDouble("flatBonus", 0.0);
			double totalMultiplier = current.getDouble("multiplier", 1.0);
			damageModifiers
					.add(new BonusDamage(minRange, maxRange, enchantLevelMultiplier, flatBonus, totalMultiplier));
		}
		return true;
	}

	@Override
	public String getIdentifier() {
		return "ARROW_DISTANCE_DAMAGE";
	}

	private class BonusDamage {

		private double minRange;
		private double maxRange;
		private double enchantLevelMultiplier;
		private double flatBonus;
		private double totalMultiplier;

		BonusDamage(double minRange, double maxRange, double enchantLevelMultiplier, double flatBonus,
				double totalMulttiplier) {
			this.minRange = minRange;
			this.maxRange = maxRange;
			this.enchantLevelMultiplier = enchantLevelMultiplier;
			this.flatBonus = flatBonus;
			this.totalMultiplier = totalMulttiplier;
		}

		public double apply(double base, int level) {
			return (base + flatBonus + ((double) level) * enchantLevelMultiplier) * totalMultiplier;
		}

	}
}
