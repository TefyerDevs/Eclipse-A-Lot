package net.tefyer.eclipseallot.api.materials;

import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.lowdragmc.lowdraglib.utils.ResourceHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.tefyer.eclipseallot.Eclipseallot;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public record MaterialIconType(String name) {

    private static final Table<MaterialIconType, MaterialIconSet, ResourceLocation> ITEM_MODEL_CACHE = HashBasedTable
            .create();
    private static final Table<MaterialIconType, MaterialIconSet, ResourceLocation> ITEM_TEXTURE_CACHE = HashBasedTable
            .create();
    private static final Table<MaterialIconType, MaterialIconSet, ResourceLocation> ITEM_TEXTURE_CACHE_SECONDARY = HashBasedTable
            .create();
    private static final Table<MaterialIconType, MaterialIconSet, ResourceLocation> BLOCK_MODEL_CACHE = HashBasedTable
            .create();
    private static final Table<MaterialIconType, MaterialIconSet, ResourceLocation> BLOCK_TEXTURE_CACHE = HashBasedTable
            .create();
    private static final Table<MaterialIconType, MaterialIconSet, ResourceLocation> BLOCK_TEXTURE_CACHE_SECONDARY = HashBasedTable
            .create();

    public static final Map<String, MaterialIconType> ICON_TYPES = new HashMap<>();

    public static final MaterialIconType ingot = new MaterialIconType("ingot");
    public static final MaterialIconType dust = new MaterialIconType("dust");

    public MaterialIconType(String name) {
        this.name = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
        Preconditions.checkArgument(!ICON_TYPES.containsKey(this.name), "MaterialIconTyp "+name+" already registerd");
        ICON_TYPES.put(this.name, this);
    }

    public static void init() {

    }
    @NotNull
    public ResourceLocation getItemModelPath(@NotNull MaterialIconSet materialIconSet, boolean doReadCache) {
        if (doReadCache) {
            if (ITEM_MODEL_CACHE.contains(this, materialIconSet)) {
                return ITEM_MODEL_CACHE.get(this, materialIconSet);
            }
        }

        MaterialIconSet iconSet = materialIconSet;
        // noinspection ConstantConditions
        if (!iconSet.isRootIconset && Eclipseallot.isClientSide() && Minecraft.getInstance() != null &&
                Minecraft.getInstance().getResourceManager() != null) { // check minecraft for null for CI environments
            while (!iconSet.isRootIconset) {
                ResourceLocation location = Eclipseallot
                        .id(String.format("models/item/material_sets/%s/%s.json", iconSet.name, this.name));
                if (ResourceHelper.isResourceExist(location) || ResourceHelper.isResourceExistRaw(location))
                    break;
                iconSet = iconSet.parentIconset;
            }
        }

        ResourceLocation location = Eclipseallot.id(String.format("item/material_sets/%s/%s", iconSet.name, this.name));
        ITEM_MODEL_CACHE.put(this, materialIconSet, location);

        return location;
    }
}
