package googy.betterwithtrinkets.block.entity;

import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;

public class TileEntityFuser extends TileEntity implements IInventory
{
	protected ItemStack[] items = new ItemStack[4];

	@Override
	public void readFromNBT(CompoundTag tagCompound)
	{
		super.readFromNBT(tagCompound);

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
