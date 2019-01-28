package com.github.maxopoly.WurstCivTools.effect.orbs;

import org.bukkit.block.Bed;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CommandBlock;
import org.bukkit.block.Container;
import org.bukkit.command.Command;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.github.maxopoly.WurstCivTools.effect.WurstEffect;

public abstract class AbstractOrbEffect extends WurstEffect {

	public void handleInteractOffhand(Player p, PlayerInteractEvent e) {
		if (e.getClickedBlock() != null) {
			Block b = e.getClickedBlock();
			BlockState bs = b.getState();
			if (bs instanceof Container) {
				return;
			}
		}
		apply(p.getInventory().getItemInOffHand(), p.getInventory().getItemInMainHand());
		e.setCancelled(true);
	}

	public void handleClickOnItem(ItemStack cursor, ItemStack clickedOn, Player player, InventoryClickEvent e) {
		apply(cursor, clickedOn);
		e.setCancelled(true);

	}
	
	protected abstract void apply(ItemStack orb, ItemStack toApplyTo);

}
