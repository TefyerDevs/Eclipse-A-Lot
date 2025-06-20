package net.tefyer.eclipseallot;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.proxy.ClientProxy;
import net.tefyer.eclipseallot.proxy.CommonProxy;
import org.slf4j.Logger;

import java.nio.file.Path;

@Mod(Eclipseallot.MODID)
public class Eclipseallot {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "eclipseallot";
    public static final String NAME = "Eclipse A Lot";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public Eclipseallot() {
        Eclipseallot.init();
        APIUtils.instance = this;
        DistExecutor.unsafeRunForDist(()-> ClientProxy::new, ()-> CommonProxy::new);
    }

    private static void init() {
        LOGGER.info("{} is initializing---", NAME);
    }

    public static ResourceLocation id(String name){
        return ResourceLocation.fromNamespaceAndPath(MODID, APIUtils.Formatting.toLowerCaseUnder(name));
    }


    /**
     * @return if the FML environment is a client
     */
    public static boolean isClientSide() {
        return FMLEnvironment.dist.isClient();
    }


    /**
     * @return the path to the minecraft instance directory
     */
    public static Path getGameDir() {
        return FMLPaths.GAMEDIR.get();
    }



    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
