package net.tefyer.eclipseallot.api.machines;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.awt.*;

public class MachineBuilder {

    public MachineBuilder id(ResourceLocation alloyFurnace) {

        return this;
    }

    public MachineBuilder setRotatable(EnumProperty<Direction.Axis> horizontalAxis) {
        return this;
    }

    public MachineBuilder setMenu(MenuType<?> menu) {
        return this;
    }

    public Machine build(){
        return new Machine();
    }

}
