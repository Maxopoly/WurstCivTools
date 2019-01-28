package com.github.maxopoly.WurstCivTools.effect;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.github.maxopoly.WurstCivTools.WurstCivTools;
import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantManager;
import com.github.maxopoly.WurstCivTools.enchantment.CustomEnchantment;
import com.github.maxopoly.WurstCivTools.misc.MultiplierConfig;

public class ExtraBlockDropEffect extends AbstractEnchantmentEffect {

	private MultiplierConfig dropConfig;
	private ItemStack item;
	private boolean removeNormalDrops;
	private Set<Material> appliedBlocks;
	private List<CustomEnchantment> disallowedEnchants;

	@Override
	public void handleBreak(Player p, BlockBreakEvent e) {
		if (appliedBlocks != null && !appliedBlocks.contains(e.getBlock().getType())) {
			return;
		}
		for(CustomEnchantment disEnchant : disallowedEnchants) {
			if (getEnchantLevel(p.getInventory().getItemInMainHand(), disEnchant) > 0) {
				return;
			}
		}
		if (removeNormalDrops) {
			e.setDropItems(false);
		}
		int enchantLevel = getEnchantLevel(p.getInventory().getItemInMainHand());
		int amount = (int) dropConfig.apply(enchantLevel);
		ItemStack is = new ItemStack(item);
		is.setAmount(amount);
		// Schedule the item to drop 1 tick later
		Bukkit.getScheduler().scheduleSyncDelayedTask(WurstCivTools.getPlugin(), new Runnable() {
			@Override
			public void run() {
				e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation().add(0.5, 0.5, 0.5), is)
						.setVelocity(new Vector(0, 0.05, 0));
			}
		}, 1);
	}

	@Override
	public boolean parseParameters(ConfigurationSection config) {
		dropConfig = new MultiplierConfig(config);
		item = config.getItemStack("item");
		if (item == null) {
			logger.warning("No item supplied for effect at " + config.getCurrentPath());
			return false;
		}
		removeNormalDrops = config.getBoolean("removeNormalDrops", false);
		if (config.isList("blocks")) {
			appliedBlocks = new HashSet<>();
			for (String s : config.getStringList("blocks")) {
				try {
					Material m = Material.valueOf(s.toUpperCase());
					appliedBlocks.add(m);
				} catch (IllegalArgumentException e) {
					logger.warning("Could not parse material " + s + " at " + config.getCurrentPath());
				}
			}
		}
		disallowedEnchants = new LinkedList<CustomEnchantment>();
		CustomEnchantManager enchManager = WurstCivTools.getPlugin().getCustomEnchantManager();
		if (config.isList("disallowedEnchants")) {
			for (String s : config.getStringList("disallowedEnchants")) {
				CustomEnchantment disEnchant = enchManager.getEnchantment(s);
				if (disEnchant == null) {
					logger.warning("Could not parse disallowed enchantment " + s + " at " + config.getCurrentPath());
					continue;
				}
				disallowedEnchants.add(disEnchant);
			}
		}
		return true;

	}

	@Override
	public String getIdentifier() {
		return "BLOCKDROP_REPLACE";
	}

}
