package googy.betterwithtrinkets.trinket;


import net.minecraft.core.net.command.TextFormatting;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TrinketEffects
{
	public static final Map<String, Integer> nameToIdMap = new HashMap<String, Integer>();

	public static TrinketEffect[] effects = new TrinketEffect[128];

	public static TrinketEffect getById(int id)
	{
		return effects[id];
	}

	public static void registerEffect(TrinketEffect effect)
	{
		if (effects[effect.id] == null)
			effects[effect.id] = effect;
	}

	public static TrinketEffect haste = new TrinketEffect("haste", 1, EffectTarget.DIGGER, TextFormatting.LIME, 0x55FF55);
	public static TrinketEffect fury = new TrinketEffect("fury", 2, EffectTarget.SWORD, TextFormatting.RED, 0xFF5555);
	public static TrinketEffect flame = new TrinketEffect("flame", 3, EffectTarget.WEAPON, TextFormatting.YELLOW, 0xFFFF55);
}
