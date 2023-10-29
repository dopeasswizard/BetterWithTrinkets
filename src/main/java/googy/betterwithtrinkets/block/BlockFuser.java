package googy.betterwithtrinkets.block;

import googy.betterwithtrinkets.block.entity.TileEntityFuser;
import googy.betterwithtrinkets.interfaces.mixins.IEntityPlayer;
import net.minecraft.core.block.BlockTileEntity;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;

public class BlockFuser extends BlockTileEntity
{
	public BlockFuser(String key, int id)
	{
		super(key, id, Material.stone);
	}

	@Override
	protected TileEntity getNewBlockEntity()
	{
		return new TileEntityFuser();
	}


	@Override
	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		if (world.isClientSide) return true;

		TileEntityFuser tile = (TileEntityFuser) world.getBlockTileEntity(x, y, z);
		if (tile != null)
			((IEntityPlayer)player).displayGUIFuser(tile);

		return true;
	}
}
