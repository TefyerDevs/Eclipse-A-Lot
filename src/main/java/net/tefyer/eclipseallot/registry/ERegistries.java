package net.tefyer.eclipseallot.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.machines.Machine;
import net.tefyer.eclipseallot.api.machines.MachineBlock;
import net.tefyer.eclipseallot.api.registry.ERegistry;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ERegistries {

    public static final ERegistry.RL<Machine> MACHINES = new ERegistry.RL<>(Eclipseallot.id("machine"));

    public static void init(IEventBus eventBus) {
    }
}
