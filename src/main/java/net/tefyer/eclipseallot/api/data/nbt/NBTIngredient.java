package net.tefyer.eclipseallot.api.data.nbt;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.StrictNBTIngredient;

public class NBTIngredient {

    public static Ingredient createNBTIngredient(ItemStack itemStack) {
        return StrictNBTIngredient.of(itemStack);
    }
}
