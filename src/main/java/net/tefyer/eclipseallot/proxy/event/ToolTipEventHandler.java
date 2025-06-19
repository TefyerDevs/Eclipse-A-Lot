package net.tefyer.eclipseallot.proxy.event;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.APIUtils;

import java.util.List;

@Mod.EventBusSubscriber(modid = Eclipseallot.MODID,value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ToolTipEventHandler {
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        List<Component> tooltip = event.getToolTip();

        if(APIUtils.magic_database.NORMAL_ITEM_DATABASE.containsKey(item)){
            tooltip.add(Component.literal("Magical Power: " +
                    APIUtils.magic_database.NORMAL_ITEM_DATABASE.getInt(item)).withStyle(ChatFormatting.YELLOW));
        }
    }
}
