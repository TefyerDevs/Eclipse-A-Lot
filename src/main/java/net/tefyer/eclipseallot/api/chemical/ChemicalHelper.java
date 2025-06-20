package net.tefyer.eclipseallot.api.chemical;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.materials.MaterialEntry;
import net.tefyer.eclipseallot.api.property.PropertyKey;
import net.tefyer.eclipseallot.api.tag.TagPrefix;
import net.tefyer.eclipseallot.registry.ItemRegistry;
import net.tefyer.eclipseallot.registry.MaterialItemRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ChemicalHelper {
    public static ItemStack get(MaterialEntry materialEntry, int size) {
        if(MaterialItemRegistry.MATERIAL_ITEMS.contains(materialEntry.tagPrefix(),materialEntry.material())){
            return new ItemStack(Objects.requireNonNull(MaterialItemRegistry.MATERIAL_ITEMS.get(materialEntry.tagPrefix(), materialEntry.material())).get(),size);
        }
        return ItemStack.EMPTY;
    }


    public static ItemStack get(TagPrefix orePrefix, Material material, int stackSize) {
        return get(new MaterialEntry(orePrefix, material), stackSize);
    }

    public static ItemStack get(TagPrefix orePrefix, Material material) {
        return get(orePrefix, material, 1);
    }

    public static TagKey<Item>[] getTags(TagPrefix orePrefix, @NotNull Material material) {
        return orePrefix.getItemTags(material);
    }


    @Nullable
    public static TagKey<Item> getTag(TagPrefix orePrefix, @NotNull Material material) {
        var tags = orePrefix.getItemTags(material);
        if (tags.length > 0) {
            return tags[0];
        }
        return null;
    }
}
