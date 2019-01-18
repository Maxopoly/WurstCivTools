package com.github.maxopoly.WurstCivTools.enchantment;

import org.bukkit.enchantments.Enchantment;

public enum CustomEnchantment {

	NO_ENCHANTMENT("null", 0), INFUSION("Infusion", 5),

	// Vanilla Enchantments

	AQUA_AFFINITY("Aqua Affinity", 1), BANE_OF_ARTHROPODS("Bane of Arthropods", 3),
	BLAST_PROTECTION("Blast Protection", 4), DEPTH_STRIDER("Depth Strider", 3), FEATHER_FALLING("Feather Falling", 3),
	FIRE_ASPECT("Fire Aspect", 2), FIRE_PROTECTION("Fire Protection", 4), FLAME("Flame", 2), FORTUNE("Fortune", 3),
	FROST_WALKER("Frost Walker", 1), INFINITY("Infinity", 1), KNOCKBACK("Knockback", 3), LOOTING("Looting", 3),
	LUCK_OF_THE_SEA("Luck of the Sea", 3), LURE("Lure", 3), PROJECTILE_PROTECTION("Projectile Protection", 3),
	PUNCH("Punch", 1), RESPIRATION("Respiration", 3), SILK_TOUCH("Silk Touch", 1), SMITE("Smite", 4),
	SWEEPING_EDGE("Sweeping Edge", 3), THORNS("Thorns", 3), UNBREAKING("Unbreaking", 3),

	// Custom Enchantments

	// Tool Enchants
	MUTANDIS("Mutandis", 3), NATURES_BOUNTY("Nature's Bounty", 5), // TODO unimplemented

	// Hoe Enchants
	GREEN_THUMB("Green Thumb", 3), // DISABLED

	// Axe Enchants
	TIMBER("Timber", 5), // TODO unimplemented
	WOODSMAN("Woodsman", 5), // TODO unimplemented
	CARPENTRY("Carpentry", 5), // TODO unimplemented
	APPLESEED("Appleseed", 3),

	// Shovel Enchants
	SIFTING("Sifting", 5), SHIFTING_SANDS("Shifting Sands", 1), BRICKLAYER("Bricklayer", 5),

	// Pickaxe Enchants
	IRON_AFFINITY("Iron Affinity", 5), GOLD_AFFINITY("Gold Affinity", 5), AUTO_SMELT("Auto Smelt", 3),
	DEMOLISHING("Demolishing", 4), CRYSTAL_ATTUNEMENT("Crystal Attunement", 3),
	CRYSTAL_RESTORATION("Crystal Restoration", 3), EMERALD_RESONANCE("Emerald Resonance", 3),
	PROFICIENT("Proficient", 3), PROSPERITY("Prosperity", 2), STONEMASON("Stonemason", 5), UMBRAL("Umbral", 3),

	// Melee Enchants
	LIFESTEAL("Lifesteal", 2), // TODO unimplemented
	RAGE("Rage", 3), // TODO unimplemented
	SOUL_TAKER("Soul Taker", 3), // TODO unimplemented
	CORROSIVE("Corrosive", 3), // TODO unimplemented
	LIGHTBANE("Lightbane", 3), // TODO unimplemented
	HELLFIRE("Hellfire", 5), // TODO unimplemented
	BERSERKING("Berserking", 3), PLAGUEBANE("Plaguebane", 4), AQUATIC_COMBATANT("Aquatic Combatant", 2),
	HEADHUNTER("Headhunter", 3),

	// Ranged Enchants
	FAR_SHOT("Far Shot", 3), POINT_BLANK("Point Blank", 3), TRUE_SHOT("True Shot", 3), // TODO unimplemented
	HUNTERS_BLESSING("Hunter's Blessing", 3), // TODO unimplemented
	HUNTERS_MARK("Hunter's Mark", 3), CRIPPLING("Crippling", 3), // TODO unimplemented
	MULTISHOT("Multishot", 2), // TODO unimplemented
	PIERCING("Piercing", 1), // TODO unimplemented
	HARPOONING("Harpooning", 3), // TODO unimplemented

	// Shield Enchants
	VANGUARD("Vanguard", 5), // DISABLED

	// Armor Enchants
	EVASIVE("Evasive", 3), // TODO unimplemented
	BLUNTING("Blunting", 3), TURTLE("Turtle", 4), VITALITY("Vitality", 3), // TODO unimplemented
	VIGOR("Vigor", 4), // TODO unimplemented
	SECOND_WIND("Second Wind", 3), // TODO unimplemented
	LAST_STAND("Last Stand", 3), DIVINE_INTERVENTION("Divine Intervention", 3), ADRENALINE("Adrenaline", 3),
	SURVIVALIST("Survivalist", 3), // TODO unimplemented
	ICE_AEGIS("Ice Aegis", 3), // TODO unimplemented
	PROTECTOR_OF_THE_SANDS("Protector of the Sands", 3), // TODO unimplemented
	ENDURANCE("Endurance", 3); // TODO unimplemented

	private String name;
	private int maxlvl;

	CustomEnchantment(String name, int maxLevel) {
		this.name = name;
		this.maxlvl = maxLevel;
	}

	public int getMaxLevel() {
		return maxlvl;
	}

	public String getName() {
		return name;
	}

	public static Enchantment getVanillaEquivalent(CustomEnchantment ench) {
		switch (ench) {
		case AQUA_AFFINITY:
			return Enchantment.OXYGEN;
		case BANE_OF_ARTHROPODS:
			return Enchantment.DAMAGE_ARTHROPODS;
		case BLAST_PROTECTION:
			return Enchantment.PROTECTION_EXPLOSIONS;
		case DEPTH_STRIDER:
			return Enchantment.DEPTH_STRIDER;
		case FEATHER_FALLING:
			return Enchantment.PROTECTION_FALL;
		case FIRE_ASPECT:
			return Enchantment.FIRE_ASPECT;
		case FIRE_PROTECTION:
			return Enchantment.PROTECTION_FIRE;
		case FLAME:
			return Enchantment.ARROW_FIRE;
		case FORTUNE:
			return Enchantment.LOOT_BONUS_BLOCKS;
		case FROST_WALKER:
			return Enchantment.FROST_WALKER;
		case INFINITY:
			return Enchantment.ARROW_INFINITE;
		case KNOCKBACK:
			return Enchantment.KNOCKBACK;
		case LOOTING:
			return Enchantment.LOOT_BONUS_MOBS;
		case LUCK_OF_THE_SEA:
			return Enchantment.LUCK;
		case LURE:
			return Enchantment.LURE;
		case PROJECTILE_PROTECTION:
			return Enchantment.PROTECTION_PROJECTILE;
		case PUNCH:
			return Enchantment.ARROW_KNOCKBACK;
		case RESPIRATION:
			return Enchantment.OXYGEN;
		case SILK_TOUCH:
			return Enchantment.SILK_TOUCH;
		case SMITE:
			return Enchantment.DAMAGE_UNDEAD;
		case SWEEPING_EDGE:
			return Enchantment.SWEEPING_EDGE;
		case THORNS:
			return Enchantment.THORNS;
		case UNBREAKING:
			return Enchantment.DURABILITY;
		default:
			throw new IllegalArgumentException(ench.getName() + " is not a vanilla enchant");
		}
	}

	public boolean isVanillaEnchant() {
		// ok I know this is dirty, but it works and saves a lot of code
		try {
			getVanillaEquivalent(this);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	public static CustomEnchantment toCustomEnchantment(Enchantment e) {
		if (e.equals(Enchantment.WATER_WORKER))
			return CustomEnchantment.AQUA_AFFINITY;
		if (e.equals(Enchantment.DAMAGE_ARTHROPODS))
			return CustomEnchantment.BANE_OF_ARTHROPODS;
		if (e.equals(Enchantment.DEPTH_STRIDER))
			return CustomEnchantment.DEPTH_STRIDER;
		if (e.equals(Enchantment.PROTECTION_FALL))
			return CustomEnchantment.FEATHER_FALLING;
		if (e.equals(Enchantment.PROTECTION_EXPLOSIONS))
			return CustomEnchantment.BLAST_PROTECTION;
		if (e.equals(Enchantment.FIRE_ASPECT))
			return CustomEnchantment.FIRE_ASPECT;
		if (e.equals(Enchantment.PROTECTION_FIRE))
			return CustomEnchantment.FIRE_PROTECTION;
		if (e.equals(Enchantment.ARROW_FIRE))
			return CustomEnchantment.FLAME;
		if (e.equals(Enchantment.LOOT_BONUS_BLOCKS))
			return CustomEnchantment.FORTUNE;
		if (e.equals(Enchantment.FROST_WALKER))
			return CustomEnchantment.FROST_WALKER;
		if (e.equals(Enchantment.ARROW_INFINITE))
			return CustomEnchantment.INFINITY;
		if (e.equals(Enchantment.KNOCKBACK))
			return CustomEnchantment.KNOCKBACK;
		if (e.equals(Enchantment.LOOT_BONUS_MOBS))
			return CustomEnchantment.LOOTING;
		if (e.equals(Enchantment.LUCK))
			return CustomEnchantment.LUCK_OF_THE_SEA;
		if (e.equals(Enchantment.LURE))
			return CustomEnchantment.LURE;
		if (e.equals(Enchantment.PROTECTION_PROJECTILE))
			return CustomEnchantment.PROJECTILE_PROTECTION;
		if (e.equals(Enchantment.ARROW_KNOCKBACK))
			return CustomEnchantment.PUNCH;
		if (e.equals(Enchantment.OXYGEN))
			return CustomEnchantment.RESPIRATION;
		if (e.equals(Enchantment.SILK_TOUCH))
			return CustomEnchantment.SILK_TOUCH;
		if (e.equals(Enchantment.DAMAGE_UNDEAD))
			return CustomEnchantment.SMITE;
		if (e.equals(Enchantment.SWEEPING_EDGE))
			return CustomEnchantment.SWEEPING_EDGE;
		if (e.equals(Enchantment.THORNS))
			return CustomEnchantment.THORNS;
		if (e.equals(Enchantment.DURABILITY))
			return CustomEnchantment.UNBREAKING;
		if (e.equals(Enchantment.DIG_SPEED) || e.equals(Enchantment.DAMAGE_ALL) || e.equals(Enchantment.ARROW_DAMAGE)
				|| e.equals(Enchantment.PROTECTION_ENVIRONMENTAL))
			return CustomEnchantment.INFUSION;
		return CustomEnchantment.NO_ENCHANTMENT;
	}
}