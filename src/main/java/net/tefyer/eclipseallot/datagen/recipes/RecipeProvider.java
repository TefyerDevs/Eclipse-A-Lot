package net.tefyer.eclipseallot.datagen.recipes;

import com.google.common.collect.Table;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.tefyer.eclipseallot.api.chemical.ChemicalHelper;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.property.PropertyKey;
import net.tefyer.eclipseallot.api.tag.TagPrefix;
import net.tefyer.eclipseallot.api.tag.TagPrefixItem;
import net.tefyer.eclipseallot.registry.MaterialItemRegistry;
import net.tefyer.eclipseallot.registry.MaterialRegistry;

import java.util.Map;

public class RecipeProvider{

    public static void init(RegistrateRecipeProvider recipe) {
        for(Map.Entry<String, Material> entry : MaterialRegistry.MATERIAL.entrySet()){
            if(entry.getValue().hasProperty(PropertyKey.INGOT, PropertyKey.ORE)){
                recipe.smelting(DataIngredient.items(ChemicalHelper.get(TagPrefix.rawOre, entry.getValue()).getItem()),
                        RecipeCategory.MISC, ()->ChemicalHelper.get(TagPrefix.ingot, entry.getValue()).getItem(),
                        200);
            }
        }

    }
}
