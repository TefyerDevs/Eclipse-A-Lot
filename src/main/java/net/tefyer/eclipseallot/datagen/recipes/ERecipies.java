package net.tefyer.eclipseallot.datagen.recipes;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.property.PropertyKey;
import net.tefyer.eclipseallot.datagen.recipes.custom.OreRecipeHandler;
import net.tefyer.eclipseallot.registry.MaterialRegistry;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class ERecipies {

    public static final Set<ResourceLocation> RECIPE_FILTERS = new ObjectOpenHashSet<>();

    public static void recipeRemoval() {
        RECIPE_FILTERS.clear();

        RecipeRemoval.init(RECIPE_FILTERS::add);
        //AddonFinder.getAddons().forEach(addon -> addon.removeRecipes(RECIPE_FILTERS::add));
    }

    public static void recipeAddition(Consumer<FinishedRecipe> originalConsumer) {
        for(Map.Entry<String, Material> material : MaterialRegistry.MATERIAL.entrySet()){
            if(material.getValue().hasProperty(PropertyKey.RAW_INGOT) && material.getValue().hasProperty(PropertyKey.INGOT)){
                OreRecipeHandler.run(originalConsumer,material.getValue());
            }

        }
    }
}
