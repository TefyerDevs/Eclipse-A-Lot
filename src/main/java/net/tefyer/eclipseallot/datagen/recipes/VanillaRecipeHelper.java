package net.tefyer.eclipseallot.datagen.recipes;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.datagen.recipes.builder.SmeltingRecipeBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class VanillaRecipeHelper {

    public static void addSmeltingRecipe(Consumer<FinishedRecipe> provider, @NotNull String regName, TagKey<Item> input,
                                         ItemStack output) {
        addSmeltingRecipe(provider, Eclipseallot.id(regName), input, output);
    }

    public static void addSmeltingRecipe(Consumer<FinishedRecipe> provider, @NotNull ResourceLocation regName,
                                         TagKey<Item> input, ItemStack output) {
        addSmeltingRecipe(provider, regName, input, output, 0.0f);
    }

    public static void addSmeltingRecipe(Consumer<FinishedRecipe> provider, @NotNull String regName, TagKey<Item> input,
                                         ItemStack output, float experience) {
        addSmeltingRecipe(provider, Eclipseallot.id(regName), input, output, experience);
    }

    public static void addSmeltingRecipe(Consumer<FinishedRecipe> provider, @NotNull String regName, Ingredient input,
                                         ItemStack output, float experience) {
        addSmeltingRecipe(provider, Eclipseallot.id(regName), input, output, experience);
    }

    public static void addSmeltingRecipe(Consumer<FinishedRecipe> provider, @NotNull ResourceLocation regName,
                                         Ingredient input, ItemStack output, float experience) {
        new SmeltingRecipeBuilder(regName).input(input).output(output).cookingTime(200).experience(experience)
                .save(provider);
    }

    public static void addSmeltingRecipe(Consumer<FinishedRecipe> provider, @NotNull ResourceLocation regName,
                                         TagKey<Item> input, ItemStack output, float experience) {
        new SmeltingRecipeBuilder(regName).input(input).output(output).cookingTime(200).experience(experience)
                .save(provider);
    }


}
