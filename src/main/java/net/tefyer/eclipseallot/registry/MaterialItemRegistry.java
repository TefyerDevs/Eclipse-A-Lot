package net.tefyer.eclipseallot.registry;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.tefyer.eclipseallot.api.data.ItemMaterialInfo;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.tag.TagPrefix;
import net.tefyer.eclipseallot.api.tag.TagPrefixItem;

import java.util.List;
import java.util.Map;

import static net.tefyer.eclipseallot.registry.CreativeModeTabs.MATERIAL_ITEM;
import static net.tefyer.eclipseallot.proxy.CommonProxy.REGISTRATE;

public class MaterialItemRegistry {

    // Reference Table Builders
    static ImmutableTable.Builder<TagPrefix, Material, ItemEntry<TagPrefixItem>> MATERIAL_ITEMS_BUILDER = ImmutableTable
            .builder();

    public static Table<TagPrefix, Material, ItemEntry<TagPrefixItem>> MATERIAL_ITEMS;

    /** Used for custom material data for items that do not fall into the normal "prefix, material" pair */
    public static final Map<Item, ItemMaterialInfo> ITEM_MATERIAL_INFO = new Object2ObjectOpenHashMap<>();

    public static void registerMaterialInfo(ItemLike item, ItemMaterialInfo materialInfo) {
        ITEM_MATERIAL_INFO.put(item.asItem(), materialInfo);
    }

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
