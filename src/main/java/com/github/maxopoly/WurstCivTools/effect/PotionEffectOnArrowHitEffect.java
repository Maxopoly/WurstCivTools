package com.github.maxopoly.WurstCivTools.effect;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionEffectOnArrowHitEffect extends AbstractArrowTagEffect {

	private PotionEffectType effect;
	private double baseDurationBonus;
	private double totalDurationMultiplier;
	private double enchantLevelDurationMultiplier;
	private double baseLevelBonus;
	private double totalLevelMultiplier;
	private double enchantLevelLevelMultiplier;
	private boolean extendExistingEffect;

	@Override
	public void arrowWithEnchantHit(Projectile projectile, int enchantLevel, EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof LivingEntity)) {
			return;
		}
		LivingEntity entity = (LivingEntity) e.getEntity();
		int level = (int) ((baseLevelBonus + enchantLevelLevelMultiplier * ((double)  enchantLevel))
				* totalLevelMultiplier);
		int duration = (int) ((baseDurationBonus + enchantLevelDurationMultiplier * ((double)  enchantLevel))
				* totalDurationMultiplier);
		if (extendExistingEffect) {
			for(PotionEffect pot : entity.getActivePotionEffects()) {
				if (pot.getType() == effect) {
					if (pot.getAmplifier() == level) {
						entity.addPotionEffect(new PotionEffect(effect, pot.getDuration() + duration, level));
						return;
					}
					break;
				}
			}
		}
		entity.addPotionEffect(new PotionEffect(effect, duration, level));
	}

	@Override
	public boolean parseParameters(ConfigurationSection config) {
		try {
		effect = PotionEffectType.getByName(config.getString("effect", "GLOWING").toUpperCase().trim());
		}
		catch (IllegalArgumentException e) {
			logger.warning("Failed to parse potion effect type at " + config.getCurrentPath());
			return false;
		}
		baseDurationBonus = config.getDouble("flatDuration", 1);
		totalDurationMultiplier = config.getDouble("durationMultiplier", 20);
		enchantLevelDurationMultiplier = config.getDouble("durationPerLevel", 1);
		baseLevelBonus = config.getDouble("amplifier", 0);
		totalLevelMultiplier = config.getDouble("amplifierPerLevel", 0);
		enchantLevelLevelMultiplier = config.getDouble("amplifierPerLevel", 0);
		extendExistingEffect = config.getBoolean("extendExisting", false);
		return true;
	}

	@Override
	public String getIdentifier() {
		return "POTION_EFFECT_ARROW";
	}

}
