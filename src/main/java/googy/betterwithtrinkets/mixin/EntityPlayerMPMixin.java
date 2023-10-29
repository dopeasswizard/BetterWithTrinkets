package googy.betterwithtrinkets.mixin;

import googy.betterwithtrinkets.BetterWithTrinkets;
import googy.betterwithtrinkets.block.entity.TileEntityFuser;
import googy.betterwithtrinkets.interfaces.mixins.IEntityPlayer;
import googy.betterwithtrinkets.inventory.ContainerFuser;
import net.minecraft.core.crafting.ICrafting;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.net.packet.Packet100OpenWindow;
import net.minecraft.core.world.World;
import net.minecraft.server.entity.player.EntityPlayerMP;
import net.minecraft.server.net.handler.NetServerHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = EntityPlayerMP.class, remap = false)
public abstract class EntityPlayerMPMixin extends EntityPlayer implements IEntityPlayer, ICrafting
{
	public EntityPlayerMPMixin(World world)
	{
		super(world);
	}

	@Shadow
	protected abstract void getNextWindowId();

	@Shadow
	public NetServerHandler playerNetServerHandler;

	@Shadow
	private int currentWindowId;


	@Override
	public void displayGUIFuser(TileEntityFuser fuser)
	{
		getNextWindowId();

		playerNetServerHandler.sendPacket(
			new Packet100OpenWindow(
				currentWindowId,
				BetterWithTrinkets.config.getInt("fuser_inventory_id"),
				fuser.getInvName(),
				fuser.getSizeInventory()
		));

		craftingInventory = new ContainerFuser(inventory, fuser);
		craftingInventory.windowId = currentWindowId;
		craftingInventory.onContainerInit(this);
	}
}
