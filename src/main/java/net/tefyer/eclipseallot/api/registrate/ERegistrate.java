package net.tefyer.eclipseallot.api.registrate;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.item.Item;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.materials.MaterialItem;
import net.tefyer.eclipseallot.api.materials.MaterialProperties;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ERegistrate extends Registrate {
    public List<Material> materials = new ArrayList<>();
    public Object2ObjectOpenHashMap<String, ItemEntry<MaterialItem>> materialItems = new Object2ObjectOpenHashMap<>();

    public ERegistrate(String modid) {
        super(modid);
    }


    @Override
    public <T extends Item> @NotNull ItemBuilder<T, Registrate> item(String name,
                                                                     NonNullFunction<Item.Properties, T> factory) {
        return super.item(name, factory).lang(APIUtils.Formatting.toEnglishName(name.replaceAll("\\.", "_")));
    }

    public Material addMaterials(Material mat){
        materials.add(mat);
        return mat;
    }

    public void registerMaterials() {
        for(Material material : materials){
            material.register();
        }
    }

    public void addItem(String id,ItemEntry<MaterialItem> materialItemItemEntry) {
        materialItems.put(id, materialItemItemEntry);
    }
}
