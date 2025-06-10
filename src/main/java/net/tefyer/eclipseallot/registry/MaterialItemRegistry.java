package net.tefyer.eclipseallot.registry;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.materials.MaterialItem;
import net.tefyer.eclipseallot.api.materials.MaterialProperties;
import net.tefyer.eclipseallot.api.registrate.ERegistrate;
import net.tefyer.eclipseallot.api.tag.TagPrefix;
import net.tefyer.eclipseallot.api.tag.TagPrefixItem;

import static net.tefyer.eclipseallot.registry.CreativeModeTabs.MATERIAL_ITEM;
import static net.tefyer.eclipseallot.registry.ItemRegistry.REGISTRATE;

public class MaterialItemRegistry {

    // Reference Table Builders
    static ImmutableTable.Builder<TagPrefix, Material, ItemEntry<MaterialItem>> MATERIAL_ITEMS_BUILDER = ImmutableTable
            .builder();

    public static Table<TagPrefix, Material, ItemEntry<MaterialItem>> MATERIAL_ITEMS;

    private void registerItem(TagPrefix tagPrefix, MaterialItem item) {
        MATERIAL_ITEMS_BUILDER.put(tagPrefix,item.getMaterial(), REGISTRATE
                .item(tagPrefix.idPattern.formatted(item.getMaterial().getName(),
                        properties1 -> new TagPrefixItem(properties1, tagPrefix, item.getMaterial())))
                .color(() -> MaterialItem::tintColor).register());
    }

    public static void generateMaterialItems(){
        REGISTRATE.creativeModeTab(()-> MATERIAL_ITEM);
        for(var tagPrefix : TagPrefix.values()){
            if(tagPrefix.doGenerateItem()){

            }
        }
        MATERIAL_ITEMS = MATERIAL_ITEMS_BUILDER.build();
    }
}
