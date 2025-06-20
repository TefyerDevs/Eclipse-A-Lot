package net.tefyer.eclipseallot.datagen.recipes;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class RecipeRemoval {

    public static void init(Consumer<ResourceLocation> registry) {
        generalRemovals(registry);
    }

    private static void generalRemovals(Consumer<ResourceLocation> registry) {
        registry.accept(new ResourceLocation("minecraft:soul_torch"));
        registry.accept(new ResourceLocation("minecraft:soul_lantern"));
        registry.accept(new ResourceLocation("minecraft:leather_horse_armor"));
    }
}
