package net.tefyer.eclipseallot.datagen.recipes.custom;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.tefyer.eclipseallot.api.chemical.ChemicalHelper;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.tag.TagPrefix;
import net.tefyer.eclipseallot.datagen.recipes.VanillaRecipeHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static net.tefyer.eclipseallot.api.tag.TagPrefix.*;

public class OreRecipeHandler {

    public static void run(@NotNull Consumer<FinishedRecipe> provider, @NotNull Material material) {
        processRawOre(provider,rawOre,material);
    }

    private static void processRawOre(@NotNull Consumer<FinishedRecipe> provider, TagPrefix prefix, @NotNull Material material) {
        ItemStack ingotStack = ChemicalHelper.get(ingot, material);

        VanillaRecipeHelper.addSmeltingRecipe(provider,"smelt_"+ prefix.name() +"_" + material.getName()+"_to_ingot",
                ChemicalHelper.getTag(prefix,material), ingotStack);
    }
}
