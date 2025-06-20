package net.tefyer.eclipseallot.api.machines;

import lombok.Getter;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.function.Supplier;

public class Machine {
    @Getter
    public ResourceLocation id;
    @Getter
    public Supplier<MenuType<?>> menu;
    @Getter
    public EnumProperty<Direction.Axis> rotational;

    public Machine(ResourceLocation id, Supplier<MenuType<?>> menu, EnumProperty<Direction.Axis> rotational) {
        this.id = id;
        this.menu = menu;
        this.rotational = rotational;
    }
}
