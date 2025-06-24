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
        type.formatter = (prefix, material) ->
                APIUtils.Tag.createItemTag(type.tagPath.formatted(material.getName()), isVanilla);
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
