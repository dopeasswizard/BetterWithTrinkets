package googy.betterwithtrinkets.utils;

public class IDUtil
{
	private static int blockID;
	private static int itemID;

	public static void init(int blockID, int itemID) {
		IDUtil.blockID = blockID;
		IDUtil.itemID = itemID;
	}


	public static int nextBlock()
	{
		return blockID++;
	}

	public static int nextItem()
	{
		return itemID++;
	}
}
