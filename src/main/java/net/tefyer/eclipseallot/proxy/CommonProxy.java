package net.tefyer.eclipseallot.proxy;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.format.ConfigFormats;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tefyer.eclipseallot.ConfigHolder;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.api.materials.MaterialIconSet;
import net.tefyer.eclipseallot.api.materials.MaterialIconType;
import net.tefyer.eclipseallot.api.registry.ERegistrate;
import net.tefyer.eclipseallot.api.tag.TagPrefix;
import net.tefyer.eclipseallot.datagen.EDataGen;
import net.tefyer.eclipseallot.client.pack.DynamicResourcePack;
import net.tefyer.eclipseallot.client.pack.EPackSource;
import net.tefyer.eclipseallot.networking.ModMessages;
import net.tefyer.eclipseallot.registry.*;

public class CommonProxy {
    public static final ERegistrate REGISTRATE = new ERegistrate(Eclipseallot.MODID);

    public CommonProxy() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.register(this);

        ERegistries.init(eventBus);
        ConfigHolder.init();


    }
    public static void init(){
        APIUtils.magic_database.init();
        TagPrefix.init();
        MaterialIconSet.init();
        MaterialIconType.init();
        MaterialRegistry.init();
        CreativeModeTabs.init();
        ItemRegistry.init();
        BlockRegistry.init();
        BlockEntityRegistry.init();

        MachineRegistry.init();
        MachineUtilsRegistry.init();

        REGISTRATE.registerRegistrate();

        EDataGen.init();
        ModMessages.register();
    }

    @SubscribeEvent
    public void modConstruct(FMLConstructModEvent event){
        event.enqueueWork(CommonProxy::init);
    }

    @SubscribeEvent
    public void registerPackFinders(AddPackFindersEvent event){
        if(event.getPackType() == PackType.CLIENT_RESOURCES){
            DynamicResourcePack.clearClient();

            event.addRepositorySource(new EPackSource(Eclipseallot.MODID+":dynamic_assets",
                    event.getPackType(),
                    Pack.Position.BOTTOM,
                    DynamicResourcePack::new));
        }
    }
}
