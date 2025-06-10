package net.tefyer.eclipseallot.proxy;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tefyer.eclipseallot.registry.CreativeModeTabs;
import net.tefyer.eclipseallot.registry.ItemRegistry;
import net.tefyer.eclipseallot.registry.MaterialRegistry;

public class CommonProxy {
    public CommonProxy() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.register(this);

    }
    public static void init(){
        MaterialRegistry.init();
        CreativeModeTabs.init();
        ItemRegistry.init();
    }

    @SubscribeEvent
    public void modConstruct(FMLConstructModEvent event){
        event.enqueueWork(CommonProxy::init);
    }
}
