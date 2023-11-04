package googy.betterwithtrinkets.trinket;

import googy.betterwithtrinkets.BetterWithTrinkets;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.net.command.TextFormatting;

public class TrinketEffect
{
	public final int id;
	public String key;

	public final EffectTarget target;

	public int color;
	public TextFormatting formatting;

	public TrinketEffect(String name, int id, EffectTarget target, TextFormatting formatting, int color)
	{
		this.id = id;
		TrinketEffects.registerEffect(this);
		setKey(name);
		this.target = target;
		this.color = color;
		this.formatting = formatting;
	}

	public TrinketEffect setKey(String s)
	{
		key = "trinket." + BetterWithTrinkets.MOD_ID + "." + s;
		TrinketEffects.nameToIdMap.put(key, id);
		return this;
	}

	public boolean canApply(Item item)
	{
		return target.canApply(item);
	}

	public boolean canApply(ItemStack stack)
	{
		if (stack == null || stack.getItem() == null || stack.stackSize <= 0)
			return false;

		return target.canApply(stack.getItem());
	}

	public String getName()
	{
		return I18n.getInstance().translateKey(key + ".name");
	}
}
