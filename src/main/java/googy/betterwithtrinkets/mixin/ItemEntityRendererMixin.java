package googy.betterwithtrinkets.mixin;

import googy.betterwithtrinkets.utils.ItemRenderUtils;
import googy.betterwithtrinkets.utils.TrinketUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.model.BlockModel;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.core.Global;
import net.minecraft.core.block.Block;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.tag.ItemTags;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.util.helper.MathHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemEntityRenderer.class, remap = false)
public class ItemEntityRendererMixin
{
	/**
	 * @author me
	 * @reason make rendering the trinket glint possible, by setting the z axis to 1
	 */
	@Overwrite
	public void renderTexturedQuad(int x, int y, int tileX, int tileY, int tileWidth, int tileHeight)
	{
		float z = 1; // <- the fix
		float offsetX = 1.0f / (float)(Global.TEXTURE_ATLAS_WIDTH_TILES * tileWidth);
		float offsetY = 1.0f / (float)(Global.TEXTURE_ATLAS_WIDTH_TILES * tileHeight);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x, y + 16, z, tileX * offsetX, (tileY + tileHeight) * offsetY);
		tessellator.addVertexWithUV(x + 16, y + 16, z, (tileX + tileWidth) * offsetX, (tileY + tileHeight) * offsetY);
		tessellator.addVertexWithUV(x + 16, y, z, (tileX + tileWidth) * offsetX, tileY * offsetY);
		tessellator.addVertexWithUV(x, y, z, tileX * offsetX, tileY * offsetY);
		tessellator.draw();
	}


	// inside containers
	@Inject(method = "renderItemIntoGUI(Lnet/minecraft/client/render/FontRenderer;Lnet/minecraft/client/render/RenderEngine;Lnet/minecraft/core/item/ItemStack;IIFF)V", at = @At("TAIL"))
	public void drawItemIntoGui(FontRenderer fontrenderer, RenderEngine renderengine, ItemStack stack, int x, int y, float brightness, float alpha, CallbackInfo ci)
	{
		drawGlint(stack, x, y);
	}

	// inside the hotbar
	@Inject(method = "renderItemIntoGUI(Lnet/minecraft/client/render/FontRenderer;Lnet/minecraft/client/render/RenderEngine;Lnet/minecraft/core/item/ItemStack;IIF)V", at = @At("TAIL"))
	public void drawItemIntoGui(FontRenderer fontrenderer, RenderEngine renderengine, ItemStack stack, int x, int y, float alpha, CallbackInfo ci)
	{
		drawGlint(stack, x, y);
	}

	void drawGlint(ItemStack stack, int x, int y)
	{
		if (stack == null) return;
		if (!TrinketUtils.hasTrinkets(stack)) return;

		ItemRenderUtils.renderGlint(x - 2, y - 2, 20, 20, TrinketUtils.getTrinketColor(stack));
	}




}
