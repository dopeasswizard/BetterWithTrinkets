package googy.betterwithtrinkets.block.entity;

import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import googy.betterwithtrinkets.item.ItemTrinket;
import googy.betterwithtrinkets.utils.TrinketUtils;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;

public class TileEntityFuser extends TileEntity implements IInventory
{
	public final int FUEL_SLOT = 0;
	public final int ITEM_SLOT = 1;
	public final int TRINKET_SLOT = 2;
	public final int OUTPUT_SLOT = 3;

	protected ItemStack[] items = new ItemStack[4];

	public int maxBurnTime = 20 * 2; // 3 seconds
	public int burnTime = 0;

	public int maxFuseTime = 20 * 16; // 15 seconds
	public int fuseTime = 0;

	@Override
	public void updateEntity()
	{
		if (isBurning())
			burnTime--;

		if (worldObj.isClientSide) return;
		if (!canFuse())
		{
			fuseTime = 0;
			return;
		}

		if (!isBurning() && items[FUEL_SLOT] != null)
		{
			// set burn time
			burnTime = maxBurnTime;

			// decrease fuel stack size
			items[FUEL_SLOT].stackSize--;
			if (items[FUEL_SLOT].stackSize <= 0)
				items[FUEL_SLOT] = null;
		}

		if (isBurning())
		{
			fuseTime++;

			if (fuseTime >= maxFuseTime)
			{
				fuseTime = 0;
				fuseItem();
			}
		}
		else
		{
			fuseTime = 0;
		}

	}

	private void fuseItem()
	{
		ItemStack stackToFuse = items[ITEM_SLOT];
		ItemStack trinketStack = items[TRINKET_SLOT];

		ItemTrinket trinketItem = (ItemTrinket) trinketStack.getItem();
		if (trinketItem == null) return;

		ItemStack fusedStack = new ItemStack(stackToFuse);
		TrinketUtils.addTrinket(fusedStack, trinketItem.effect);

		items[ITEM_SLOT] = null;
		items[TRINKET_SLOT] = null;

		items[OUTPUT_SLOT] = fusedStack;
	}

	private boolean canFuse()
	{
		if (items[ITEM_SLOT] == null || items[TRINKET_SLOT] == null) return false;
		if (items[OUTPUT_SLOT] != null) return false;

		ItemTrinket trinketItem = (ItemTrinket) items[TRINKET_SLOT].getItem();
		if (trinketItem == null) return false;

		return trinketItem.effect.canApply(items[ITEM_SLOT]);
	}

	public boolean isBurning()
	{
		return burnTime > 0;
	}

	public boolean isFusing()
	{
		return fuseTime > 0 && fuseTime < maxFuseTime;
	}

	@Override
	public void readFromNBT(CompoundTag tagCompound)
	{
		super.readFromNBT(tagCompound);

		burnTime = tagCompound.getShort("BurnTime");
		fuseTime = tagCompound.getShort("FuseTime");

		ListTag itemList = tagCompound.getList("Items");
		for (int i = 0; i < itemList.tagCount(); i++)
		{
			CompoundTag itemTag = (CompoundTag)itemList.tagAt(i);
			byte slot = itemTag.getByte("Slot");

			if (slot >= 0 && slot < items.length)
				items[slot] = ItemStack.readItemStackFromNbt(itemTag);
		}
	}

	@Override
	public void writeToNBT(CompoundTag tagCompound)
	{
		super.writeToNBT(tagCompound);

		tagCompound.putShort("BurnTime", (short) burnTime);
		tagCompound.putShort("FuseTime", (short) fuseTime);

		ListTag itemsTag = new ListTag();
		for (int i = 0; i < items.length; i++)
		{
			if (items[i] == null) continue;

			CompoundTag itemTag = new CompoundTag();
			itemTag.putByte("Slot", (byte)i);

			items[i].writeToNBT(itemTag);
			itemsTag.addTag(itemTag);
		}

		tagCompound.put("Items", itemsTag);
	}

	@Override
	public int getSizeInventory()
	{
		return 3;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		if (items[slot] == null) return null;

		if (items[slot].stackSize <= amount)
		{
			ItemStack itemstack = this.items[slot];
			items[slot] = null;
			return itemstack;
		}

		ItemStack itemstack = items[slot].splitStack(amount);
		if (items[slot].stackSize <= 0) {
			items[slot] = null;
		}

		return itemstack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		items[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName()
	{
		return "Fuser";
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		}

		return player.distanceToSqr(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64.0;
	}
}
