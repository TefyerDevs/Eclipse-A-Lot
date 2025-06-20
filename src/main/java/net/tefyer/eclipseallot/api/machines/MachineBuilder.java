package net.tefyer.eclipseallot.api.machines;

import lombok.Getter;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.awt.*;
import java.util.function.Supplier;

public class MachineBuilder {

    public ResourceLocation id;
    public Supplier<MenuType<?>> menu;
    public EnumProperty<Direction.Axis> rotational;

    public MachineBuilder id(ResourceLocation id) {

        return this;
    }

    public MachineBuilder setRotatable(EnumProperty<Direction.Axis> horizontalAxis) {
        return this;
    }

    public MachineBuilder setMenu(Supplier<MenuType<?>> menu) {
        return this;
    }

    public Machine build(){
        return new Machine(id,menu,rotational);
    }

}
