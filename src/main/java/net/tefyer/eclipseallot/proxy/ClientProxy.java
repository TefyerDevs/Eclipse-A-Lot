package net.tefyer.eclipseallot.proxy;

import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterItemDecorationsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.tefyer.eclipseallot.Eclipseallot;

@Mod.EventBusSubscriber(modid = Eclipseallot.MODID,value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientProxy extends CommonProxy{
    public ClientProxy() {

    }

    @SubscribeEvent
    public void onRegisterItemDecoration(RegisterItemDecorationsEvent event){

    }

    
}
