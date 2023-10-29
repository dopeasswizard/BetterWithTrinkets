package googy.betterwithtrinkets.trinket;


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

	public static TrinketEffect haste = new TrinketEffect("haste", 1, EffectTarget.DIGGER, Color.GREEN.getRGB());
	public static TrinketEffect fury = new TrinketEffect("fury", 2, EffectTarget.SWORD, Color.RED.getRGB());
}
