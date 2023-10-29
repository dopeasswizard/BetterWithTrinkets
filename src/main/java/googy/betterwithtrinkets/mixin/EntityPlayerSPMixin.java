package googy.betterwithtrinkets.mixin;

import googy.betterwithtrinkets.block.entity.TileEntityFuser;
import googy.betterwithtrinkets.gui.GuiFuser;
import googy.betterwithtrinkets.interfaces.mixins.IEntityPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = EntityPlayerSP.class, remap = false)
public class EntityPlayerSPMixin implements IEntityPlayer
{
	@Shadow
	protected Minecraft mc;


	@Override
	public void displayGUIFuser(TileEntityFuser fuser)
	{
		mc.displayGuiScreen(new GuiFuser(this.mc.thePlayer.inventory, fuser));
	}
}
