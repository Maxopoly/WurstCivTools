package com.github.maxopoly.WurstCivTools.effect;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;

public class HuntersMarkEffect extends AbstractArrowTagEffect {

	private static final int baseTiming = 2;

	@Override
	public void arrowWithEnchantHit(Projectile projectile, int enchantLevel, EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof LivingEntity)) {
			return;
		}
		((LivingEntity) e.getEntity())
				.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20 * (enchantLevel + baseTiming), 1));
	}

	@Override
	public CustomEnchantment getEnchant() {
		return CustomEnchantment.HUNTERS_MARK;
	}

}
