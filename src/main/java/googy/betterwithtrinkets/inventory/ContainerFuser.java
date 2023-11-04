package googy.betterwithtrinkets.inventory;

import googy.betterwithtrinkets.block.entity.TileEntityFuser;
import googy.betterwithtrinkets.inventory.slot.FuserFuelSlot;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.crafting.ICrafting;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;

import java.util.List;

public class ContainerFuser extends Container
{
	public TileEntityFuser fuserEntity;

	private int maxBurnTime = 0;
	private int burnTime = 0;

	private int maxFuseTime = 0;
	private int fuseTime = 0;

	public ContainerFuser(InventoryPlayer inventory, TileEntityFuser fuserEntity)
	{
		this.fuserEntity = fuserEntity;

		addSlot(new FuserFuelSlot(fuserEntity, 0, 79, 53)); // fuel

		addSlot(new Slot(fuserEntity, 1, 56, 36)); // input
		addSlot(new Slot(fuserEntity, 2, 102, 36)); // input

		addSlot(new Slot(fuserEntity, 3, 79, 17)); // output

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public void updateInventory()
	{
		super.updateInventory();

		for (ICrafting crafter : this.crafters)
		{
			if (maxBurnTime != fuserEntity.maxBurnTime)
				crafter.updateCraftingInventoryInfo(this, 0, fuserEntity.maxBurnTime);

			if (burnTime != fuserEntity.burnTime)
				crafter.updateCraftingInventoryInfo(this, 1, fuserEntity.burnTime);

			if (maxFuseTime != fuserEntity.maxFuseTime)
				crafter.updateCraftingInventoryInfo(this, 2, fuserEntity.maxFuseTime);

			if (fuseTime != fuserEntity.fuseTime)
				crafter.updateCraftingInventoryInfo(this, 3, fuserEntity.fuseTime);
		}

		maxBurnTime = fuserEntity.maxBurnTime;
		burnTime = fuserEntity.burnTime;
		maxFuseTime = fuserEntity.maxFuseTime;
		fuseTime = fuserEntity.fuseTime;
	}

	@Override
	public void updateClientProgressBar(int id, int value)
	{
		if (id == 0) fuserEntity.maxBurnTime = value;
		if (id == 1) fuserEntity.burnTime = value;
		if (id == 2) fuserEntity.maxFuseTime = value;
		if (id == 3) fuserEntity.fuseTime = value;
	}

	@Override
	public List<Integer> getMoveSlots(InventoryAction inventoryAction, Slot slot, int i, EntityPlayer entityPlayer)
	{
		return null;
	}

	@Override
	public List<Integer> getTargetSlots(InventoryAction inventoryAction, Slot slot, int i, EntityPlayer entityPlayer)
	{
		return null;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		return fuserEntity.canInteractWith(player);
	}
}
