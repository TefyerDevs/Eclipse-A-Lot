package net.tefyer.eclipseallot.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.machines.MachineDefinition;
import net.tefyer.eclipseallot.api.registry.ERegistry;

public class ERegistries {

    public static final ERegistry.RL<MachineDefinition> MACHINES = new ERegistry.RL<>(Eclipseallot.id("machine"));

    public static final MachineDefinition registerMachine(ResourceLocation id, MachineDefinition machineDefinition){
        return MACHINES.register(id, machineDefinition);
    }

    public static void init(IEventBus eventBus) {
    }
}
