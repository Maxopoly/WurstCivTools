package com.github.maxopoly.WurstCivTools.effect;

import java.util.List;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import com.github.maxopoly.WurstCivTools.WurstCivTools;
import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;

public abstract class AbstractArrowTagEffect extends AbstractEnchantmentEffect {

	@Override
	public void handleProjectileShot(Player p, ProjectileLaunchEvent e) {
		if (e.getEntity().getType() == EntityType.ARROW) {
			Arrow arrow = (Arrow) e.getEntity();
			arrow.setMetadata(getEnchant().getName(), new FixedMetadataValue(WurstCivTools.getPlugin(),
					getEnchantLevel(p.getInventory().getItemInMainHand())));
		}
	}
	
	@Override
	public void handleProjectileHit(Projectile proj, EntityDamageByEntityEvent e) {
		if (proj.getType() != EntityType.ARROW) {
			return;
		}
		Arrow arrow = (Arrow) e.getEntity();
		int level = getEnchantLevelFromArrow(arrow);
		if (level != -1) {
			arrowWithEnchantHit(proj, level, e);
		}
	}
	
	public abstract void arrowWithEnchantHit(Projectile projectile, int enchantLevel, EntityDamageByEntityEvent e);
	
	protected int getEnchantLevelFromArrow(Arrow arrow) {
		return getEnchantLevelFromArrow(arrow, getEnchant());
	}
	
	protected int getEnchantLevelFromArrow(Arrow arrow, CustomEnchantment enchant) {
		List<MetadataValue> values = arrow.getMetadata(enchant.getName());
		if (values == null || values.size() == 0) {
			return -1;
		}
		return values.get(0).asInt();
	}

}
