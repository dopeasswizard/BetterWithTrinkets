package googy.betterwithtrinkets.item;

import googy.betterwithtrinkets.trinket.TrinketEffect;
import net.minecraft.core.item.Item;

public class ItemTrinket extends Item
{
	public TrinketEffect effect;

	public ItemTrinket(String name, int id, TrinketEffect effect)
	{
		super("trinket." + name, id);
		setMaxStackSize(1);
		this.effect = effect;
	}


}
