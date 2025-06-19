package net.tefyer.eclipseallot.registry;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.machines.Machine;
import net.tefyer.eclipseallot.api.machines.MachineBuilder;

public class MachineRegistry {

    public static final Machine ALLOY_FURNACE = new MachineBuilder()
            .id(Eclipseallot.id("alloy_furnace"))
            .setRotatable(BlockStateProperties.HORIZONTAL_AXIS)
            //.setMenu(MenuRegistry.ALLOY_FURNACE.get())
            .build();

    public static void init() {
    }
}
