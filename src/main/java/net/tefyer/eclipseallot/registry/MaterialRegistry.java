package net.tefyer.eclipseallot.registry;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.materials.MaterialProperties;

public class MaterialRegistry {
    public static Object2ObjectMap<String, Material> MATERIAL = new Object2ObjectOpenHashMap<>();

    public static final Material NULL = new Material.Builder(Eclipseallot.id("null")).build();

    public static final Material ECLIPSIUM_ALLOY = new Material.Builder(Eclipseallot.id("eclpisium_alloy"))
            .setId("eclpisium_alloy")
            .ingot()
            .primaryColour(0xb734eb)
            .secondaryColoor(0x521dcf)
            .build();

    public static void init(){

    }

}
