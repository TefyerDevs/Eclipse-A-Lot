package net.tefyer.eclipseallot.api.tag;

import com.google.common.collect.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.materials.MaterialIconType;
import net.tefyer.eclipseallot.api.property.PropertyKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static net.tefyer.eclipseallot.api.tag.TagPrefix.Conditions.hasDustProperty;
import static net.tefyer.eclipseallot.api.tag.TagPrefix.Conditions.hasIngotProperty;

@SuppressWarnings("unused")
@Accessors(chain = true, fluent = true)
public class TagPrefix {

    public static Map<String, TagPrefix> PREFIXES = new HashMap<>();

    public static void init(){

    }

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

    public static final TagPrefix NULL_PREFIX = new TagPrefix("null");


    @Setter
    private Supplier<Table<TagPrefix, Material, ? extends Supplier<? extends ItemLike>>> itemTable;

    @Getter
    @Setter
    private @Nullable Predicate<Material> generationCondition;

    private final Map<Material, Supplier<? extends ItemLike>[]> ignoredMaterials = new HashMap<>();
    protected final List<TagType> tags = new ArrayList<>();
    @Getter
    String name;

    @Getter
    public String idPattern;

    @Getter
    String unformattedTagPath;

    @Getter
    boolean generateItem;

    @Getter
    public final boolean invertedName;

    @Getter
    MaterialIconType materialIconType;

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

    public int maxStackSize() {
        return 64;
    }


    public static class Conditions {
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
