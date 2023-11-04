package googy.betterwithtrinkets.utils;

import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.IntTag;
import com.mojang.nbt.ListTag;
import googy.betterwithtrinkets.trinket.TrinketEffect;
import googy.betterwithtrinkets.trinket.TrinketEffects;
import net.minecraft.core.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TrinketUtils
{
	public static final String TRINKET_DATA_KEY = "trinketData";
	public static final String TRINKET_LIST_KEY = "trinketIds";
	public static final String ID_KEY= "id";

	public static ListTag getTrinketListTag(ItemStack stack)
	{
		CompoundTag trinketData = stack.getData().getCompound(TRINKET_DATA_KEY);
		return trinketData.getList(TRINKET_LIST_KEY);
	}

	public static boolean hasTrinkets(ItemStack stack)
	{
		return getTrinketListTag(stack).tagCount() > 0;
	}

	public static boolean hasTrinket(ItemStack stack, TrinketEffect trinket)
	{
		ListTag list = getTrinketListTag(stack);

		for (int i = 0; i < list.tagCount(); i++)
		{
			int id = ((IntTag) list.tagAt(i)).getValue();
			if (trinket.id == id) return true;
		}

		return false;
	}

	public static void addTrinket(ItemStack stack, TrinketEffect trinket)
	{
		if (hasTrinket(stack, trinket)) return;

		ListTag trinketsList = getTrinketListTag(stack);
		trinketsList.addTag(new IntTag(trinket.id));

		CompoundTag trinketDataTag = new CompoundTag();
		trinketDataTag.putList(TRINKET_LIST_KEY, trinketsList);

		stack.getData().putCompound(TRINKET_DATA_KEY, trinketDataTag);
	}

	public static List<TrinketEffect> getTrinkets(ItemStack stack)
	{
		List<TrinketEffect> trinkets = new ArrayList<>();

		ListTag list = getTrinketListTag(stack);

		for (int i = 0; i < list.tagCount(); i++)
		{
			int id = ((IntTag) list.tagAt(i)).getValue();
			TrinketEffect trinket = TrinketEffects.getById(id);
			if (trinket == null) continue;

			trinkets.add(trinket);
		}

		return trinkets;
	}

	public static int getTrinketColor(ItemStack stack)
	{
		int r = 0, g = 0, b = 0;

		List<TrinketEffect> trinkets =  getTrinkets(stack);

		for (TrinketEffect effect : trinkets)
		{
			r += ((effect.color & 0xff0000) >> 16) / trinkets.size();
			g += ((effect.color & 0xff00) >> 8) / trinkets.size();
			b += (effect.color & 0xff) / trinkets.size();
		}

		return r << 16 | g << 8 | b;
	}

}
