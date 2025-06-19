package net.tefyer.eclipseallot.registry;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.tefyer.eclipseallot.api.tag.TagPrefix;
import net.tefyer.eclipseallot.api.tag.TagPrefixComponentItem;
import net.tefyer.eclipseallot.api.tag.TagPrefixItem;
import net.tefyer.eclipseallot.api.tiers.TechTiers;

import static net.tefyer.eclipseallot.proxy.CommonProxy.REGISTRATE;
import static net.tefyer.eclipseallot.registry.CreativeModeTabs.MATERIAL_ITEM;

public class ComponentItemRegistry {

    // Reference Table Builders
    static ImmutableTable.Builder<TagPrefix, TechTiers,
            ItemEntry<TagPrefixComponentItem>> COMPONENT_ITEMS_BUILDER = ImmutableTable
            .builder();

    public static Table<TagPrefix, TechTiers, ItemEntry<TagPrefixComponentItem>> COMPONENT_ITEMS;

    public static void generateComponentItems() {
        REGISTRATE.creativeModeTab(()-> MATERIAL_ITEM);
        for(TechTiers techTiers : TechTiers.values()){
            for(TagPrefix tagPrefix : TagPrefix.values()){
                if(tagPrefix.isComponent())
                    generateComponentItem(techTiers,tagPrefix);
            }
        }
        COMPONENT_ITEMS = COMPONENT_ITEMS_BUILDER.build();

    }

    private static void generateComponentItem(TechTiers techTiers, TagPrefix tagPrefix) {
        COMPONENT_ITEMS_BUILDER.put(tagPrefix,techTiers, REGISTRATE
                .item(tagPrefix.idPattern.formatted(techTiers.id()),
                        properties -> new TagPrefixComponentItem(properties,tagPrefix,techTiers))
                .color(() -> TagPrefixComponentItem::tintColor)
                .onRegister(TagPrefixComponentItem::onRegister)
                .model(NonNullBiConsumer.noop())
                .properties(p -> p.stacksTo(tagPrefix.maxStackSize()))
                .setData(ProviderType.LANG, NonNullBiConsumer.noop())
                .register());
    }
}
