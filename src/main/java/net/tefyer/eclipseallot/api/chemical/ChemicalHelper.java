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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ChemicalHelper {
    public static List<ItemLike> getItems(MaterialEntry materialEntry){
        if(materialEntry.material().isNull()) return new ArrayList<>();
        return ItemRegistry.MATERIAL_ENTRY_ITEM_MAP.computeIfAbsent(materialEntry, entry -> {
            var items = new ArrayList<Supplier<? extends Item>>();
            for(TagKey<Item> tag : getTags(entry.tagPrefix(), entry.material())){
                for(Holder<Item> itemHolder : BuiltInRegistries.ITEM.getTagOrEmpty(tag)){
                    items.add(itemHolder::value);
                }
            }
            TagPrefix prefix = entry.tagPrefix();
            if(items.isEmpty() && prefix.hasItemTable() && prefix.doGenerateItem(entry.material()))
                return List.of(() -> prefix.getItemFromTable(entry.material()).get().asItem());
            return items;
        }).stream().map(Supplier::get).collect(Collectors.toList());
    }
    public static ItemStack get(MaterialEntry materialEntry, int size) {
        var list = getItems(materialEntry);
        if (list.isEmpty()) return ItemStack.EMPTY;
        var stack = list.get(0).asItem().getDefaultInstance();
        stack.setCount(size);
        return stack;
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
}
