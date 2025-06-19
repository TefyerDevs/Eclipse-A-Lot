package net.tefyer.eclipseallot.registry;

import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.materials.MaterialEntry;
import net.tefyer.eclipseallot.api.registrate.ERegistrate;
import net.tefyer.eclipseallot.api.tag.TagPrefix;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ItemRegistry {

    public static final Map<MaterialEntry, List<Supplier<? extends Item>>> MATERIAL_ENTRY_ITEM_MAP = new Object2ObjectOpenHashMap<>();

    public static void init(){
        MaterialItemRegistry.generateMaterialItems();

    }


}
