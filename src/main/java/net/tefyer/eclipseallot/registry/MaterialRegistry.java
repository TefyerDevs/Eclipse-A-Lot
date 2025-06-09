package net.tefyer.eclipseallot.registry;

import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.materials.MaterialProperties;
import net.tefyer.eclipseallot.api.registrate.ERegistrate;

public class MaterialRegistry {
    public static final ERegistrate REGISTRATE = new ERegistrate(Eclipseallot.MODID);

    public static final Material ECLIPSIUM_ALLOY = new Material.Builder()
            .setId("eclpisium_alloy")
            .setProperties(
                    new MaterialProperties())
            .ingot()
            .primaryColour(0xb734eb)
            .secondaryColoor(0x521dcf)
            .build();

    public static void init(){}

}
