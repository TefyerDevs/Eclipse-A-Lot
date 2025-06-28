package net.tefyer.eclipseallot.proxy;

import com.lowdragmc.shimmer.client.postprocessing.PostProcessing;
import com.lowdragmc.shimmer.client.shader.RenderUtils;
import com.lowdragmc.shimmer.config.Bloom;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.client.LightWrapper;

import java.util.List;

public class ClientProxy extends CommonProxy{
    public ClientProxy() {
        super();
    }


    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
        if (ModList.get().isLoaded("shimmer")) {
            LightWrapper.shimmer();
        }
    }

}
