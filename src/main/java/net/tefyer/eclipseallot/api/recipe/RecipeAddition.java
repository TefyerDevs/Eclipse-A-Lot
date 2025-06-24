package net.tefyer.eclipseallot.api.recipe;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class RecipeAddition {
    public static void init(Consumer<FinishedRecipe> consumer){
        hardMiscRecipes(consumer);
    }

    private static void hardMiscRecipes(Consumer<FinishedRecipe> provider) {
    }
}
