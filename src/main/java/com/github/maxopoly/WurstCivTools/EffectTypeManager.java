package com.github.maxopoly.WurstCivTools;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.github.maxopoly.WurstCivTools.effect.NpcNameBonusDamageEffect;
import com.github.maxopoly.WurstCivTools.effect.WurstEffect;

public class EffectTypeManager {
	
	private Map<String, Class<? extends WurstEffect>> effectClasses;
	
	public EffectTypeManager() {
		this.effectClasses = new HashMap<>();
		registerAllEffects();
	}
	
	private void registerAllEffects() {
		registerEffect(new NpcNameBonusDamageEffect());
	}
	
	
	public WurstEffect getNewEffectInstance(String id) {
		Class <? extends WurstEffect> effectClass =  effectClasses.get(id);
		if (effectClass == null) {
			return null;
		}
		try {
			return (WurstEffect) effectClass.getConstructors() [0].newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			WurstCivTools.getPlugin().severe("Failed to instanciate effect " + effectClass.getName(), e);
			return null;
		}
	}
	
	public void registerEffect(WurstEffect effect) {
		effectClasses.put(effect.getIdentifier(), effect.getClass());
	}
 }
