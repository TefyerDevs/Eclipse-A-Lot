package net.tefyer.eclipseallot.api.registrate;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.Builder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.materials.MaterialItem;
import net.tefyer.eclipseallot.api.materials.MaterialProperties;
import net.tefyer.eclipseallot.registry.MaterialRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ERegistrate extends Registrate {
    public List<Material> materials = new ArrayList<>();

    private final AtomicBoolean registered = new AtomicBoolean(false);

    public Object2ObjectOpenHashMap<String, ItemEntry<MaterialItem>> materialItems = new Object2ObjectOpenHashMap<>();

    public ERegistrate(String modid) {
        super(modid);
    }



    @Override
    public <T extends Item> @NotNull ItemBuilder<T, Registrate> item(String name,
                                                                     NonNullFunction<Item.Properties, T> factory) {
        return super.item(name, factory).lang(APIUtils.Formatting.toEnglishName(name.replaceAll("\\.", "_")));
    }

    private RegistryEntry<CreativeModeTab> currentTab;
    private static final Map<RegistryEntry<?>, RegistryEntry<CreativeModeTab>> TAB_LOOKUP = new IdentityHashMap<>();

    public void creativeModeTab(Supplier<RegistryEntry<CreativeModeTab>> currentTab) {
        this.currentTab = currentTab.get();
    }

    public boolean isInCreativeTab(RegistryEntry<?> entry, RegistryEntry<CreativeModeTab> tab) {
        return TAB_LOOKUP.get(entry) == tab;
    }

    public void setCreativeTab(RegistryEntry<?> entry, @Nullable RegistryEntry<CreativeModeTab> tab) {
        TAB_LOOKUP.put(entry, tab);
    }

    protected <R,
            T extends R> RegistryEntry<T> accept(String name, ResourceKey<? extends Registry<R>> type,
                                                 Builder<R, T, ?, ?> builder, NonNullSupplier<? extends T> creator,
                                                 NonNullFunction<RegistryObject<T>, ? extends RegistryEntry<T>> entryFactory) {
        RegistryEntry<T> entry = super.accept(name, type, builder, creator, entryFactory);

        if (this.currentTab != null) {
            TAB_LOOKUP.put(entry, this.currentTab);
        }

        return entry;
    }

    public Material addMaterials(Material mat){
        materials.add(mat);
        return mat;
    }
    public void addItem(String id,ItemEntry<MaterialItem> materialItemItemEntry) {
        materialItems.put(id, materialItemItemEntry);
    }

    public List<Material> getAllMaterials() {
        return materials;
    }

    public void registerRegistrate() {
        registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @Override
    public Registrate registerEventListeners(IEventBus bus) {
        if (!registered.getAndSet(true)) {
            return super.registerEventListeners(bus);
        }
        return this;
    }

}
