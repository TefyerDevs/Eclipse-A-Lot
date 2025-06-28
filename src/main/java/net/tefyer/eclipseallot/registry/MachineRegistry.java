package net.tefyer.eclipseallot.registry;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.proxy.CommonProxy;

import static net.tefyer.eclipseallot.proxy.CommonProxy.REGISTRATE;
import static net.tefyer.eclipseallot.registry.CreativeModeTabs.MACHINE;
import static net.tefyer.eclipseallot.registry.MachineUtilsRegistry.registerSimpleMachines;

public class MachineRegistry {

    static {
        REGISTRATE.creativeModeTab(() -> MACHINE);
        ERegistries.MACHINES.unfreeze();
    }

    public static final MachineDefinition[] ALLOY_FURNACE = registerSimpleMachines("alloy_furnace",
            ERecipeTypes.ALLOY_FURNACE_RECIPES);
    public static void init() {
    }
}
