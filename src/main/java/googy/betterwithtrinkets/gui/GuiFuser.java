package googy.betterwithtrinkets.gui;

import googy.betterwithtrinkets.block.entity.TileEntityFuser;
import googy.betterwithtrinkets.inventory.ContainerFuser;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.InventoryPlayer;
import org.lwjgl.opengl.GL11;

public class GuiFuser extends GuiContainer
{
	TileEntityFuser fuser;

	public GuiFuser(InventoryPlayer inventory, TileEntityFuser tileEntity)
	{
		super(new ContainerFuser(inventory, tileEntity));
		fuser = tileEntity;
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float delta)
	{
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

		int guiTexture = mc.renderEngine.getTexture("assets/gui/fuser.png");
		mc.renderEngine.bindTexture(guiTexture);

		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;

		// draw background
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		int strWidth = fontRenderer.getStringWidth(fuser.getInvName());
		fontRenderer.drawString(fuser.getInvName(), x + xSize / 2 - strWidth / 2, y + 6, 0x404040);
	}
}
