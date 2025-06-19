package net.tefyer.eclipseallot.datagen.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.tag.TagPrefix;
import net.tefyer.eclipseallot.registry.MaterialRegistry;

import java.util.Collection;

import static net.tefyer.eclipseallot.api.APIUtils.Formatting.toEnglishName;

public class ItemLang {
    public static void init(RegistrateLangProvider provider){
        initGeneratedNames(provider);
        generateMaterials(provider, MaterialRegistry.MATERIAL);
    }

    private static void initGeneratedNames(RegistrateLangProvider provider) {
        // TagPrefix
        for (TagPrefix tagPrefix : TagPrefix.values()) {
            provider.add(tagPrefix.getUnlocalizedName(), tagPrefix.langValue);
        }

    }


    public static void generateMaterials(RegistrateLangProvider provider, Object2ObjectMap<String, Material> materials) {
        for (Material material : materials.values())
            provider.add(material.getUnlocalizedName(), toEnglishName(material.getName()));
    }
}
