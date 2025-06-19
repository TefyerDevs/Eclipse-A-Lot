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


    public static final MaterialIconType dustTiny = new MaterialIconType("dustTiny");
    public static final MaterialIconType dustSmall = new MaterialIconType("dustSmall");
    public static final MaterialIconType dust = new MaterialIconType("dust");
    public static final MaterialIconType dustImpure = new MaterialIconType("dustImpure");
    public static final MaterialIconType dustPure = new MaterialIconType("dustPure");

    public static final MaterialIconType rawOre = new MaterialIconType("rawOre");
    public static final MaterialIconType rawOreBlock = new MaterialIconType("rawOreBlock");
    public static final MaterialIconType crushed = new MaterialIconType("crushed");
    public static final MaterialIconType crushedPurified = new MaterialIconType("crushedPurified");
    public static final MaterialIconType crushedRefined = new MaterialIconType("crushedRefined");

    public static final MaterialIconType gem = new MaterialIconType("gem");
    public static final MaterialIconType gemChipped = new MaterialIconType("gemChipped");
    public static final MaterialIconType gemFlawed = new MaterialIconType("gemFlawed");
    public static final MaterialIconType gemFlawless = new MaterialIconType("gemFlawless");
    public static final MaterialIconType gemExquisite = new MaterialIconType("gemExquisite");

    public static final MaterialIconType nugget = new MaterialIconType("nugget");

    public static final MaterialIconType ingot = new MaterialIconType("ingot");
    public static final MaterialIconType ingotHot = new MaterialIconType("ingotHot");
    public static final MaterialIconType ingotDouble = new MaterialIconType("ingotDouble");
    public static final MaterialIconType ingotTriple = new MaterialIconType("ingotTriple");
    public static final MaterialIconType ingotQuadruple = new MaterialIconType("ingotQuadruple");
    public static final MaterialIconType ingotQuintuple = new MaterialIconType("ingotQuintuple");

    public static final MaterialIconType plate = new MaterialIconType("plate");
    public static final MaterialIconType plateDouble = new MaterialIconType("plateDouble");
    public static final MaterialIconType plateTriple = new MaterialIconType("plateTriple");
    public static final MaterialIconType plateQuadruple = new MaterialIconType("plateQuadruple");
    public static final MaterialIconType plateQuintuple = new MaterialIconType("plateQuintuple");
    public static final MaterialIconType plateDense = new MaterialIconType("plateDense");

    public static final MaterialIconType rod = new MaterialIconType("rod");
    public static final MaterialIconType lens = new MaterialIconType("lens");
    public static final MaterialIconType round = new MaterialIconType("round");
    public static final MaterialIconType bolt = new MaterialIconType("bolt");
    public static final MaterialIconType screw = new MaterialIconType("screw");
    public static final MaterialIconType ring = new MaterialIconType("ring");
    public static final MaterialIconType wireFine = new MaterialIconType("wireFine");
    public static final MaterialIconType gearSmall = new MaterialIconType("gearSmall");
    public static final MaterialIconType rotor = new MaterialIconType("rotor");
    public static final MaterialIconType rodLong = new MaterialIconType("rodLong");
    public static final MaterialIconType springSmall = new MaterialIconType("springSmall");
    public static final MaterialIconType spring = new MaterialIconType("spring");
    public static final MaterialIconType gear = new MaterialIconType("gear");
    public static final MaterialIconType foil = new MaterialIconType("foil");

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
