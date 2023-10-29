package googy.betterwithtrinkets.trinket;

import googy.betterwithtrinkets.BetterWithTrinkets;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.util.helper.Color;

public class TrinketEffect
{
	public final int id;
	public String key;

	public final EffectTarget target;
	public int color;

	public TrinketEffect(String name, int id, EffectTarget target, int color)
	{
		this.id = id;
		setKey(name);
		this.target = target;
		this.color = color;
	}

	public TrinketEffect setKey(String s)
	{
		key = "enchantment." + BetterWithTrinkets.MOD_ID + "." + s;
		TrinketEffects.nameToIdMap.put(key, id);
		return this;
	}

	public String getName()
	{
		return I18n.getInstance().translateKey(key + ".name");
	}
}
