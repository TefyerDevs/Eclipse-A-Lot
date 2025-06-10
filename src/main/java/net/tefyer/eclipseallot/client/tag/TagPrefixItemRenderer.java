package net.tefyer.eclipseallot.client.tag;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.model.DelegatedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.tefyer.eclipseallot.api.materials.MaterialIconSet;
import net.tefyer.eclipseallot.api.materials.MaterialIconType;

import java.util.HashSet;
import java.util.Set;

public class TagPrefixItemRenderer {

    private static final Set<TagPrefixItemRenderer> MODELS = new HashSet<>();

    public static void create(Item item, MaterialIconType type, MaterialIconSet iconsSet){
        MODELS.add(new TagPrefixItemRenderer(item, type, iconsSet));
    }

    private final Item item;
    private final MaterialIconType type;
    private final MaterialIconSet iconSet;

    private TagPrefixItemRenderer(Item item, MaterialIconType type, MaterialIconSet iconSet){
        this.item = item;
        this.type = type;
        this.iconSet = iconSet;
    }

    public static void reinitModels() {
        for(TagPrefixItemRenderer model : MODELS){
            ResourceLocation itemID = BuiltInRegistries.ITEM.getKey(model.item);
            DynamicResourcePack.addItemModel(itemID, new DelegatedModel(model.type.getItemModelPath(model.iconSet, true)));
        }
    }
}
