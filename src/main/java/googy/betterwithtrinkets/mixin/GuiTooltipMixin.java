package googy.betterwithtrinkets.mixin;

import googy.betterwithtrinkets.trinket.TrinketEffect;
import googy.betterwithtrinkets.utils.TrinketUtils;
import net.minecraft.client.gui.GuiTooltip;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.command.TextFormatting;
import net.minecraft.core.player.inventory.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = GuiTooltip.class, remap = false)
public class GuiTooltipMixin
{
	@Inject(at = @At("TAIL"), method = "getTooltipText(Lnet/minecraft/core/item/ItemStack;ZLnet/minecraft/core/player/inventory/slot/Slot;)Ljava/lang/String;", cancellable = true)
	public void getTooltipText(ItemStack stack, boolean showDescription, Slot slot, CallbackInfoReturnable<String> info)
	{
		StringBuilder toolTip = new StringBuilder(info.getReturnValue());

		List<TrinketEffect> trinkets = TrinketUtils.getTrinkets(stack);

		for (TrinketEffect trinket : trinkets)
		{
			toolTip.append("\n");
			toolTip.append(TextFormatting.formatted(trinket.getName(), trinket.formatting));
		}

		info.setReturnValue(toolTip.toString());
	}

}
