package net.tefyer.eclipseallot.api.registrate;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.world.item.Item;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.api.materials.MaterialItem;
import net.tefyer.eclipseallot.api.materials.MaterialProperties;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ERegistrate extends Registrate {
    public ERegistrate(String modid) {
        super(modid);
    }


    @Override
    public <T extends Item> @NotNull ItemBuilder<T, Registrate> item(String name,
                                                                     NonNullFunction<Item.Properties, T> factory) {
        return super.item(name, factory).lang(APIUtils.Formatting.toEnglishName(name.replaceAll("\\.", "_")));
    }
}
