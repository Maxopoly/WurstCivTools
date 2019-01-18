package com.github.maxopoly.WurstCivTools.effect;

import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public abstract class WurstEffect {
	
	protected Random rng = new Random();

	public Listener getListener() {
		return null;
	}

	public void handleBreak(Player p, BlockBreakEvent e) {

	}

	public void handleInteract(Player p, PlayerInteractEvent e) {

	}

	public void handleItemSelect(Player p, PlayerItemHeldEvent e) {

	}

	public void handleItemDeselect(Player p, PlayerItemHeldEvent e) {

	}

	public void handleSwapToMainHand(Player p, PlayerSwapHandItemsEvent e) {

	}

	public void handleSwapToOffHand(Player p, PlayerSwapHandItemsEvent e) {

	}

	public void handleProjectileHit(Projectile projectile, EntityDamageByEntityEvent e) {
	}

	public void handleProjectileShot(Player p, ProjectileLaunchEvent e) {
	}

	public void handleDamagePlayerForHeldItem(Player attacker, Player victim, EntityDamageByEntityEvent e) {

	}

	/**
	 * Called for the item used to kill another player
	 * 
	 * @param attacker Killer
	 * @param victim   Being killed
	 * @param e        Event
	 */
	public void handleKillPlayerForHeldItem(Player attacker, Player victim, PlayerDeathEvent e) {

	}

	/**
	 * Called for the item used to kill an NPC
	 * 
	 * @param attacker Killer holding the item
	 * @param victim   Entity killed
	 * @param e        Event
	 */
	public void handleKillNPCForHeldItem(Player attacker, Entity victim, EntityDeathEvent e) {

	}

	/**
	 * Called for armor items worn when being hit by another player
	 * 
	 * @param attacker Attacking player
	 * @param victim   Player wearing the armor and being hit
	 * @param e        Event
	 */
	public void handleDamageReceivedPlayerForEquippedItem(Player attacker, Player victim, EntityDamageByEntityEvent e) {

	}

}
