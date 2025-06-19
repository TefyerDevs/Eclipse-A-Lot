package net.tefyer.eclipseallot.registry;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.chemical.Element;
import net.tefyer.eclipseallot.api.chemical.ElementStack;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.materials.MaterialIconSet;
import net.tefyer.eclipseallot.api.materials.MaterialProperties;

public class MaterialRegistry {
    public static Object2ObjectMap<String, Material> MATERIAL = new Object2ObjectOpenHashMap<>();

    public static final Material NULL = new Material.Builder(Eclipseallot.id("null")).build();

    public static final Material ECLIPSIUM_ALLOY = new Material.Builder(Eclipseallot.id("eclpisium_alloy"))
            .ingot()
            .dust()
            .setElement(new ElementStack(Element.NORMALIUM,1),
                    new ElementStack(Element.CHAOSIUM,3),
                    new ElementStack(Element.FURRISIUM,5))
            .primaryColour(0xb734eb)
            .secondaryColoor(0x521dcf)
            .iconSet(MaterialIconSet.SHINY)
            .build();

    public static final Material ESTATIUM_ALLOY = new Material.Builder(Eclipseallot.id("estatium_alloy"))
            .ingot()
            .setElement(new ElementStack(Element.CHAOSIUM,5), new ElementStack(Element.NORMALIUM,2))
            .primaryColour(0xf07c22)
            .secondaryColoor(0x7d3d0b)
            .iconSet(MaterialIconSet.SHINY)
            .build();

    public static void init(){
        MATERIAL.put(ECLIPSIUM_ALLOY.getId(), ECLIPSIUM_ALLOY);
        MATERIAL.put(ESTATIUM_ALLOY.getId(), ESTATIUM_ALLOY);

        for (Element element: Element.values()){
            Material elemental_material = new Material.Builder(Eclipseallot.id(element.name))
                    .addProperty(element.getPropertyKeys())
                    .setElement(new ElementStack(element,1))
                    .primaryColour(element.primaryColour)
                    .secondaryColoor(element.secondaryColour)
                    .iconSet(element.materialIconSet)
                    .build();

            MATERIAL.put(elemental_material.getId(), elemental_material);
        }
    }

}
