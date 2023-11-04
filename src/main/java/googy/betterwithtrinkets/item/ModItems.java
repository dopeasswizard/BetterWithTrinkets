package googy.betterwithtrinkets.item;

import googy.betterwithtrinkets.BetterWithTrinkets;
import googy.betterwithtrinkets.trinket.TrinketEffects;
import googy.betterwithtrinkets.utils.IDUtils;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemHelper;

public class ModItems
{
	public static final Item hasteTrinket = ItemHelper.createItem(
		BetterWithTrinkets.MOD_ID,
		new ItemTrinket("haste", IDUtils.nextItem(), TrinketEffects.haste),
		"trinket.haste"
	);

	public static final Item furyTrinket = ItemHelper.createItem(
		BetterWithTrinkets.MOD_ID,
		new ItemTrinket("fury", IDUtils.nextItem(), TrinketEffects.fury),
		"trinket.fury"
	);

	public static final Item flameTrinket = ItemHelper.createItem(
		BetterWithTrinkets.MOD_ID,
		new ItemTrinket("flame", IDUtils.nextItem(), TrinketEffects.flame),
		"trinket.flame"
	);

	public static void register() {}
}
