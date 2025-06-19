package net.tefyer.eclipseallot.registry;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.registry.ERegistrate;
import net.tefyer.eclipseallot.api.chemical.ChemicalHelper;
import net.tefyer.eclipseallot.api.tag.TagPrefix;
import org.jetbrains.annotations.NotNull;

import static net.tefyer.eclipseallot.proxy.CommonProxy.REGISTRATE;

public class CreativeModeTabs {
    public static RegistryEntry<CreativeModeTab> MATERIAL_ITEM = REGISTRATE.defaultCreativeTab("material_item",
                    builder -> builder.displayItems(new RegistrateDisplayItemsGenerator("material_item", REGISTRATE))
                            .icon(() -> ChemicalHelper.get(TagPrefix.ingot, MaterialRegistry.ECLIPSIUM_ALLOY))
                            .title(REGISTRATE.addLang("itemGroup", Eclipseallot.id("material_item"),
                                    Eclipseallot.NAME + " Material Items"))
                            .build())
            .register();

    public static void init() {
    }


    public static class RegistrateDisplayItemsGenerator implements CreativeModeTab.DisplayItemsGenerator {

        public final String name;
        public final ERegistrate registrate;

        public RegistrateDisplayItemsGenerator(String name, ERegistrate registrate) {
            this.name = name;
            this.registrate = registrate;
        }

        @Override
        public void accept(@NotNull CreativeModeTab.ItemDisplayParameters itemDisplayParameters,
                           @NotNull CreativeModeTab.Output output) {
            var tab = registrate.get(name, Registries.CREATIVE_MODE_TAB);
            for (var entry : registrate.getAll(Registries.BLOCK)) {
                if (!registrate.isInCreativeTab(entry, tab))
                    continue;
                Item item = entry.get().asItem();
                if (item == Items.AIR)
                    continue;
                output.accept(item);
            }
            for (var entry : registrate.getAll(Registries.ITEM)) {
                if (!registrate.isInCreativeTab(entry, tab))
                    continue;
                Item item = entry.get();
                output.accept(item);

            }
        }
    }
}
