package googy.betterwithtrinkets.inventory;

import googy.betterwithtrinkets.block.entity.TileEntityFuser;
import googy.betterwithtrinkets.inventory.slot.FuserFuelSlot;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;

import java.util.List;

public class ContainerFuser extends Container
{
	public TileEntityFuser fuserEntity;

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
