package net.tefyer.eclipseallot.api.tag;


import lombok.Getter;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.api.materials.Material;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public class TagType {

    private final String tagPath;
    private boolean isParentTag = false;
    private BiFunction<TagPrefix, Material, TagKey<Item>> formatter;
    private Predicate<Material> filter;

    private TagType(String tagPath) {
        this.tagPath = tagPath;
    }

    /**
     * Create a tag with a specified path, with the "default" formatter, meaning
     * that there is 1 "%s" format character in the path, intended for the Material name.
     */
    public static TagType withDefaultFormatter(String tagPath, boolean isVanilla) {
        TagType type = new TagType(tagPath);
        type.formatter = (prefix, mat) -> APIUtils.Tag.createItemTag(type.tagPath.formatted(mat.getName()), isVanilla);
        return type;
    }

    /**
     * Create a tag with a specified path, with the "default" formatter, meaning
     * that there is 2 "%s" format characters in the path, with the first being the
     * prefix name, and the second being the material name.
     */
    public static TagType withPrefixFormatter(String tagPath) {
        TagType type = new TagType(tagPath);
        type.formatter = (prefix, mat) -> APIUtils.Tag.createItemTag(
                type.tagPath.formatted(APIUtils.Formatting.toLowerCaseUnderscore(prefix.name), mat.getName()));
        return type;
    }

    /**
     * Create a tag with a specified path, with the "default" formatter, meaning
     * that there is 1 "%s" format character in the path, intended for the prefix name.
     */
    public static TagType withPrefixOnlyFormatter(String tagPath) {
        TagType type = new TagType(tagPath);
        type.formatter = (prefix, mat) -> APIUtils.Tag
                .createItemTag(type.tagPath.formatted(APIUtils.Formatting.toLowerCaseUnderscore(prefix.name)));
        type.isParentTag = true;
        return type;
    }

    public static TagType withNoFormatter(String tagPath, boolean isVanilla) {
        TagType type = new TagType(tagPath);
        type.formatter = (prefix, material) -> APIUtils.Tag.createItemTag(type.tagPath, isVanilla);
        type.isParentTag = true;
        return type;
    }

    public static TagType withCustomFormatter(String tagPath, BiFunction<TagPrefix, Material, TagKey<Item>> formatter) {
        TagType type = new TagType(tagPath);
        type.formatter = formatter;
        return type;
    }

    public static TagType withCustomFilter(String tagPath, boolean isVanilla, Predicate<Material> filter) {
        TagType type = new TagType(tagPath);
        type.filter = filter;
        type.formatter = (prefix, material) -> APIUtils.Tag.createItemTag(type.tagPath, isVanilla);
        return type;
    }

    public boolean isParentTag() {
        return isParentTag;
    }

    public TagKey<Item> getTag(TagPrefix prefix, @NotNull Material material) {
        if (filter != null && !material.isNull() && !filter.test(material)) return null;
        return formatter.apply(prefix, material);
    }
}
