package googy.betterwithtrinkets.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import org.lwjgl.opengl.GL11;

public class ItemRenderUtils
{
	public static void renderGlint(int x, int y, int w, int h, int color)
	{
		Minecraft mc = Minecraft.getMinecraft(Minecraft.class);

		GL11.glDepthFunc(516);
		GL11.glDisable(2896);
		GL11.glDepthMask(false);
		GL11.glEnable(3042);
		GL11.glBlendFunc(774, 774);
		//GL11.glColor4f(0.5F, 0.25F, 0.8F, 1.0F);
		GL11.glColor4f(((color >> 16) & 255) / 255f, ((color >> 8) & 255) / 255f, (color & 255) / 255f, 1f);

		mc.renderEngine.bindTexture(mc.renderEngine.getTexture("assets/gui/glint.png"));

		for (int i = 0; i < 2; ++i)
		{
			GL11.glBlendFunc(768, 1);

			float time = (float)(System.currentTimeMillis() % (long)(3000 + i * 1873)) / (3000.0F + (float)(i * 1873)) * 256.0F;
			Tessellator tessellator = Tessellator.instance;
			float f = 4.0F;

			double zLevel = 0;

			if (i == 1)
			{
				f = -1.0F;
			}

			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(x, y + h, zLevel, (time + h * f) * 0.00390625F, h * 0.00390625F);
			tessellator.addVertexWithUV(x + w,  y + h, zLevel, (time + w + h * f) * 0.00390625F,  0.00390625F);
			tessellator.addVertexWithUV(x + w, y, zLevel, (time + w) * 0.00390625F, 0);
			tessellator.addVertexWithUV(x, y, zLevel, time * 0.00390625F, 0);
			tessellator.draw();

		}

		GL11.glDisable(3042);
		GL11.glDepthMask(true);
		GL11.glEnable(2896);
		GL11.glDepthFunc(515);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glBlendFunc(770, 771);
	}
}
