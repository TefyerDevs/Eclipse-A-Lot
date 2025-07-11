package net.tefyer.eclipseallot.api.tag;

import com.google.common.collect.Table;
import com.lowdragmc.lowdraglib.utils.LocalizationUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.materials.MaterialIconType;
import net.tefyer.eclipseallot.api.property.PropertyKey;
import net.tefyer.eclipseallot.api.tiers.TechTiers;
import net.tefyer.eclipseallot.client.tag.CustomTags;
import net.tefyer.eclipseallot.registry.MaterialRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static net.tefyer.eclipseallot.api.tag.TagPrefix.Conditions.*;

@SuppressWarnings("unused")
@Accessors(chain = true, fluent = true)
public class TagPrefix {

    public static Map<String, TagPrefix> PREFIXES = new HashMap<>();

    public static void init(){

    }


    // consisting out of 1/8 Ingot or 1/4 Stick.
    public static final TagPrefix bolt = new TagPrefix("bolt")
            .defaultTagPath("bolts/%s")
            .unformattedTagPath("bolts")
            .materialAmount(APIUtils.Number.M / 8)
            .materialIconType(MaterialIconType.bolt)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.BOLT));

    // consisting out of 1/2 Stick.
    public static final TagPrefix ring = new TagPrefix("ring")
            .defaultTagPath("rings/%s")
            .unformattedTagPath("rings")
            .materialAmount(APIUtils.Number.M / 4)
            .materialIconType(MaterialIconType.ring)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.RING));

    // Consisting of 4 Plates.
    public static final TagPrefix gear = new TagPrefix("gear")
            .defaultTagPath("gears/%s")
            .unformattedTagPath("gears")
            .materialAmount(APIUtils.Number.M * 4)
            .maxStackSize(16)
            .materialIconType(MaterialIconType.gear)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(mat -> mat.hasProperty(PropertyKey.GEAR));

    // ========================================== //
    //             COMPONENT ITEMS                //
    // ========================================== //

    public static final TagPrefix piston = new TagPrefix("piston")
            .defaultTagPath("piston/%s")
            .unformattedTagPath("piston")
            .materialAmount(APIUtils.Number.M)
            .materialIconType(MaterialIconType.piston)
            .unificationEnabled(true)
            .isComponent(true)
            .enableRecycling();
    public static final TagPrefix mechanical_arm = new TagPrefix("mechanical_arm")
            .defaultTagPath("mechanical_arm/%s")
            .unformattedTagPath("mechanical_arm")
            .materialAmount(APIUtils.Number.M)
            .materialIconType(MaterialIconType.mechanical_arm)
            .unificationEnabled(true)
            .isComponent(true)
            .enableRecycling();

    public static final TagPrefix pump = new TagPrefix("pump")
            .defaultTagPath("pump/%s")
            .unformattedTagPath("pump")
            .materialAmount(APIUtils.Number.M)
            .materialIconType(MaterialIconType.pump)
            .unificationEnabled(true)
            .isComponent(true)
            .enableRecycling();


    // ========================================== //
    //              MATERIAL ITEMS                //
    // ========================================== //

    // Regular Plate made of one Ingot/Dust.
    public static final TagPrefix plate = new TagPrefix("plate")
            .defaultTagPath("plates/%s")
            .unformattedTagPath("plates")
            .materialAmount(APIUtils.Number.M)
            .materialIconType(MaterialIconType.plate)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true);

    public static final TagPrefix dust = new TagPrefix("dust")
            .defaultTagPath("dust/%s")
            .unformattedTagPath("dust")
            .generateItem(true)
            .materialIconType(MaterialIconType.dust)
            .generationCondition(hasDustProperty);

    public static final TagPrefix ingot = new TagPrefix("ingot")
            .defaultTagPath("ingots/%s")
            .unformattedTagPath("ingots")
            .generateItem(true)
            .materialIconType(MaterialIconType.ingot)
            .generationCondition(hasIngotProperty);


    public static final TagPrefix rawOre = new TagPrefix("raw", true)
            .idPattern("raw_%s")
            .defaultTagPath("raw_materials/%s")
            .unformattedTagPath("raw_materials")
            .langValue("Raw %s")
            .materialIconType(MaterialIconType.rawOre)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(hasOreProperty);

    // A hot Ingot, which has to be cooled down by a Vacuum Freezer.
    public static final TagPrefix ingotHot = new TagPrefix("hotIngot")
            .idPattern("hot_%s_ingot")
            .defaultTagPath("hot_ingots/%s")
            .unformattedTagPath("hot_ingots")
            .langValue("Hot %s Ingot")
            .materialIconType(MaterialIconType.ingotHot)
            .unificationEnabled(true)
            .generationCondition(hasHotIngotProperty);

    public static final TagPrefix NULL_PREFIX = new TagPrefix("null");


    @Setter
    private Supplier<Table<TagPrefix, Material, ? extends Supplier<? extends ItemLike>>> itemTable;

    @Getter
    @Setter
    private @Nullable Predicate<Material> generationCondition;

    @Nullable
    @Getter
    @Setter
    private BiConsumer<Material, List<Component>> tooltip;
    private final Map<Material, Supplier<? extends ItemLike>[]> ignoredMaterials = new HashMap<>();
    protected final List<TagType> tags = new ArrayList<>();
    @Getter
    String name;

    @Setter
    @Getter
    private boolean generateRecycling = false;
    @Getter
    @Setter
    public String idPattern;

    @Setter
    @Getter
    private boolean unificationEnabled;

    @Setter
    @Getter
    private boolean isComponent = false;

    @Getter
    String unformattedTagPath;

    @Getter
    boolean generateItem;
    @Getter
    @Setter
    private long materialAmount = -1;
    @Getter
    public final boolean invertedName;

    @Getter
    MaterialIconType materialIconType;

    @Setter
    @Getter
    public int maxStackSize;

    @Setter
    @Getter
    public String langValue;

    public TagPrefix(String name) {
        this(name, false);
    }

    public TagPrefix(String name, boolean invertedName) {
        this.name = name;
        this.idPattern = "%s_" + APIUtils.Formatting.toLowerCaseUnder(name);
        this.invertedName = invertedName;
        this.langValue = "%s " + APIUtils.Formatting.toEnglishName(APIUtils.Formatting.toLowerCaseUnder(name));
        if(PREFIXES == null)
            PREFIXES = new HashMap<>();
        PREFIXES.put(name, this);
    }
    public static Collection<TagPrefix> values() {
        return PREFIXES.values();
    }

    public static TagPrefix get(String name) {
        return PREFIXES.get(name);
    }


    private TagPrefix materialIconType(MaterialIconType materialIconType) {
        this.materialIconType = materialIconType;
        return this;
    }

    private TagPrefix generateItem(boolean doGenerate) {
        this.generateItem = doGenerate;
        return this;
    }

    private TagPrefix unformattedTagPath(String unformattedTagPath) {
        this.unformattedTagPath = unformattedTagPath;
        return this;
    }

    @SuppressWarnings("unchecked")
    public void setIgnored(Material material) {
        this.ignoredMaterials.put(material, new Supplier[0]);
    }

    public void removeIgnored(Material material) {
        ignoredMaterials.remove(material);
    }

    public boolean doGenerateItem() {
        return generateItem;
    }

    public boolean doGenerateItem(Material material) {
        return generateItem && !isIgnored(material) &&
                (generationCondition == null || generationCondition.test(material)) ||
                (hasItemTable() && this.itemTable.get() != null && getItemFromTable(material) != null);
    }

    public boolean isIgnored(Material material) {
        return ignoredMaterials.containsKey(material);
    }

    public boolean hasItemTable() {
        return itemTable != null;
    }

    @SuppressWarnings("unchecked")
    public Supplier<ItemLike> getItemFromTable(Material material) {
        return (Supplier<ItemLike>) itemTable.get().get(this, material);
    }
    @SuppressWarnings("unchecked")
    public TagKey<Item>[] getItemTags(@NotNull Material mat) {
        return tags.stream().filter(type -> !type.isParentTag()).map(type -> type.getTag(this, mat))
                .filter(Objects::nonNull)
                .toArray(TagKey[]::new);
    }

    public TagPrefix enableRecycling() {
        this.generateRecycling = true;
        return this;
    }

    public String getUnlocalizedName() {
        return "tagprefix." + APIUtils.Formatting.toLowerCaseUnderscore(name);
    }

    public MutableComponent getLocalizedName(Material material) {
        return Component.translatable(getUnlocalizedName(material), material.getLocalizedName());
    }

    public MutableComponent getLocalizedName(TechTiers techtier) {
        return Component.translatable(getUnlocalizedName(techtier), techtier.getLocalizedName());
    }

    public String getUnlocalizedName(Material material) {
        String formattedPrefix = APIUtils.Formatting.toLowerCaseUnderscore(this.name);
        String matSpecificKey = String.format("item.%s.%s", material.getModid(),
                this.idPattern.formatted(material.getName()));
        if (LocalizationUtils.exist(matSpecificKey)) {
            return matSpecificKey;
        }
        if (material.hasProperty(PropertyKey.POLYMER)) {
            String localizationKey = String.format("tagprefix.polymer.%s", formattedPrefix);
            // Not every polymer tag prefix gets a special name
            if (LocalizationUtils.exist(localizationKey)) {
                return localizationKey;
            }
        }

        return getUnlocalizedName();
    }

    public String getUnlocalizedName(TechTiers techtier) {
        String formattedPrefix = APIUtils.Formatting.toLowerCaseUnderscore(this.name);
        String matSpecificKey = String.format("item.%s.%s", Eclipseallot.MODID,
                this.idPattern.formatted(techtier.getName()));
        if (LocalizationUtils.exist(matSpecificKey)) {
            return matSpecificKey;
        }
        return getUnlocalizedName();
    }


    @SuppressWarnings("unchecked")
    public TagKey<Item>[] getItemParentTags() {
        return tags.stream().filter(TagType::isParentTag).map(type -> type.getTag(this, MaterialRegistry.NULL))
                .toArray(TagKey[]::new);
    }


    public static class Conditions {
        public static final Predicate<Material> hasHotIngotProperty = mat -> mat.hasProperty(PropertyKey.HOT_INGOT);
        public static final Predicate<Material> hasOreProperty = mat -> mat.hasProperty(PropertyKey.ORE);
        public static final Predicate<Material> hasIngotProperty = mat -> mat.hasProperty(PropertyKey.INGOT);
        public static final Predicate<Material> hasDustProperty = mat -> mat.hasProperty(PropertyKey.DUST);
    }
    public TagPrefix defaultTagPath(String path) {
        return this.defaultTagPath(path, false);
    }

    public TagPrefix defaultTagPath(String path, boolean isVanilla) {
        this.tags.add(TagType.withDefaultFormatter(path, isVanilla));
        return this;
    }

    public boolean isEmpty() {
        return this == NULL_PREFIX;
    }
}
