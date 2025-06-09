package net.tefyer.eclipseallot.proxy;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.tefyer.eclipseallot.Eclipseallot;

@Mod.EventBusSubscriber(modid = Eclipseallot.MODID,value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientProxy extends CommonProxy{
    public ClientProxy() {

    }

    
}
