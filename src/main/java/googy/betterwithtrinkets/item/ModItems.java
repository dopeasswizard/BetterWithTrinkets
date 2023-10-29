package googy.betterwithtrinkets.item;

import googy.betterwithtrinkets.BetterWithTrinkets;
import googy.betterwithtrinkets.trinket.TrinketEffects;
import googy.betterwithtrinkets.utils.IDUtil;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemHelper;

public class ModItems
{
	public static final Item hasteTrinket = ItemHelper.createItem(
		BetterWithTrinkets.MOD_ID,
		new ItemTrinket("haste", IDUtil.nextItem(), TrinketEffects.haste),
		"trinket.haste"
	);

	public static final Item furyTrinket = ItemHelper.createItem(
		BetterWithTrinkets.MOD_ID,
		new ItemTrinket("fury", IDUtil.nextItem(), TrinketEffects.haste),
		"trinket.fury"
	);

	public static void register() {}
}
