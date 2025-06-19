package net.tefyer.eclipseallot.registry;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.tag.TagPrefix;
import net.tefyer.eclipseallot.api.tag.TagPrefixItem;

import java.util.Map;

import static net.tefyer.eclipseallot.registry.CreativeModeTabs.MATERIAL_ITEM;
import static net.tefyer.eclipseallot.proxy.CommonProxy.REGISTRATE;

public class MaterialItemRegistry {

    // Reference Table Builders
    static ImmutableTable.Builder<TagPrefix, Material, ItemEntry<TagPrefixItem>> MATERIAL_ITEMS_BUILDER = ImmutableTable
            .builder();

    public static Table<TagPrefix, Material, ItemEntry<TagPrefixItem>> MATERIAL_ITEMS;

    private static void generateMaterialItem(TagPrefix tagPrefix, Material material) {
        MATERIAL_ITEMS_BUILDER.put(tagPrefix,material, REGISTRATE
                .item(tagPrefix.idPattern.formatted(material.getName()),
                        properties -> new TagPrefixItem(properties,tagPrefix,material))
                .color(() -> TagPrefixItem::tintColor)
                .onRegister(TagPrefixItem::onRegister)
                .model(NonNullBiConsumer.noop())
                .properties(p -> p.stacksTo(tagPrefix.maxStackSize()))
                .setData(ProviderType.LANG, NonNullBiConsumer.noop())
                .register());
    }

    public static void generateMaterialItems(){
        REGISTRATE.creativeModeTab(()-> MATERIAL_ITEM);
        for(var tagPrefix : TagPrefix.values()){
            if(tagPrefix.doGenerateItem()){
                for(Map.Entry<String, Material> entry : MaterialRegistry.MATERIAL.entrySet()){
                    Material material = entry.getValue();
                    if(tagPrefix.doGenerateItem(material) && !tagPrefix.isComponent()){
                        generateMaterialItem(tagPrefix, material);
                    }
                }
            }
        }
        MATERIAL_ITEMS = MATERIAL_ITEMS_BUILDER.build();
    }
}
