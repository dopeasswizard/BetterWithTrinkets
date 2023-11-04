package googy.betterwithtrinkets.utils;

public class IDUtils
{
	private static int blockID;
	private static int itemID;

	public static void init(int blockID, int itemID) {
		IDUtils.blockID = blockID;
		IDUtils.itemID = itemID;
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
