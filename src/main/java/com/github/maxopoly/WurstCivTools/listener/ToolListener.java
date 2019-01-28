package com.github.maxopoly.WurstCivTools.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import com.github.maxopoly.WurstCivTools.TagManager;
import com.github.maxopoly.WurstCivTools.WurstCivTools;
import com.github.maxopoly.WurstCivTools.tags.Tag;

public class ToolListener implements Listener {
	private TagManager manager;

	public ToolListener() {
		this.manager = WurstCivTools.getManager();
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void blockBreak(BlockBreakEvent e) {
		ItemStack is = e.getPlayer().getInventory().getItemInMainHand();
		for (Tag tag : manager.getTagsFor(is)) {
			tag.getEffect().handleBreak(e.getPlayer(), e);
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void damageEntity(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Projectile) {
			Projectile proj = (Projectile) e.getDamager();
			for (Tag tag : manager.getAllTags()) {
				tag.getEffect().handleProjectileHit(proj, e);
			}
			return;
		}
		if (e.getDamager().getType() != EntityType.PLAYER) {
			// TOD events for getting attacked by mobs
			return;
		}
		Player p = (Player) e.getDamager();
		ItemStack is = p.getInventory().getItemInMainHand();
		if (e.getEntity().getType() == EntityType.PLAYER) {
			Player victim = (Player) e.getEntity();
			for (Tag tag : manager.getTagsFor(is)) {
				tag.getEffect().handleDamagePlayerForHeldItem(p, victim, e);
			}
			for (ItemStack armor : victim.getEquipment().getArmorContents()) {
				for (Tag tag : manager.getTagsFor(armor)) {
					tag.getEffect().handleDamageReceivedPlayerForEquippedItem(p, victim, e);
				}
			}
		} else {
			for (Tag tag : manager.getTagsFor(is)) {
				tag.getEffect().handleDamageNPCForHeldItem(p, e.getEntity(), e);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void projectileLaunch(ProjectileLaunchEvent e) {
		if (e.getEntity() != null && (e.getEntity() instanceof Projectile)) {
			Projectile proj = (Projectile) e.getEntity();
			if (proj.getShooter() != null && proj.getShooter() instanceof Player) {
				Player shooter = (Player) proj.getShooter();
				for (Tag tag : manager.getTagsFor(shooter.getInventory().getItemInMainHand())) {
					tag.getEffect().handleProjectileShot(shooter, e);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void interact(PlayerInteractEvent e) {
		for (Tag tag : manager.getTagsFor(e.getItem())) {
			tag.getEffect().handleInteract(e.getPlayer(), e);
		}
		ItemStack offHand = e.getPlayer().getInventory().getItemInOffHand();
		for (Tag tag : manager.getTagsFor(offHand)) {
			tag.getEffect().handleInteractOffhand(e.getPlayer(), e);
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void invClick(InventoryClickEvent e) {
		if (e.getClick() == ClickType.LEFT || e.getClick() == ClickType.RIGHT) {
			if (e.getCurrentItem() != null && e.getCursor() != null) {
				for (Tag tag : manager.getTagsFor(e.getCursor())) {
					tag.getEffect().handleClickOnItem(e.getCursor(), e.getCurrentItem(), (Player) e.getWhoClicked(), e);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void itemSelect(PlayerItemHeldEvent e) {
		Player p = e.getPlayer();
		ItemStack olditem = p.getInventory().getItem(e.getPreviousSlot());
		for (Tag tag : manager.getTagsFor(olditem)) {
			tag.getEffect().handleItemDeselect(p, e);
		}

		ItemStack newitem = p.getInventory().getItem(e.getNewSlot());
		for (Tag tag : manager.getTagsFor(newitem)) {
			tag.getEffect().handleItemSelect(p, e);
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void switchItemOffhand(PlayerSwapHandItemsEvent e) {
		for (Tag tag : manager.getTagsFor(e.getMainHandItem())) {
			tag.getEffect().handleSwapToMainHand(e.getPlayer(), e);
		}

		for (Tag tag : manager.getTagsFor(e.getOffHandItem())) {
			tag.getEffect().handleSwapToOffHand(e.getPlayer(), e);
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player victim = event.getEntity();
		if (event.getEntity().getKiller().getType() == EntityType.PLAYER) {
			Player killer = (Player) event.getEntity().getKiller();
			for (Tag tag : manager.getTagsFor(killer.getInventory().getItemInMainHand())) {
				tag.getEffect().handleKillPlayerForHeldItem(killer, victim, event);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onEntityDeath(EntityDeathEvent e) {
		if (e.getEntityType() == EntityType.PLAYER) {
			// handled in the PlayerDeathEvent
			return;
		}
		if (e.getEntity().getKiller().getType() == EntityType.PLAYER) {
			Player killer = (Player) e.getEntity().getKiller();
			for (Tag tag : manager.getTagsFor(killer.getInventory().getItemInMainHand())) {
				tag.getEffect().handleKillNPCForHeldItem(killer, e.getEntity(), e);
			}
		}
	}
}
