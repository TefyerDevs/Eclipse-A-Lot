package net.tefyer.eclipseallot.api.chemical;

import lombok.Getter;
import net.tefyer.eclipseallot.api.materials.MaterialIconSet;
import net.tefyer.eclipseallot.api.property.PropertyKey;

public enum Element {
    CHAOSIUM("Chaosium","Ch",130,153,130,
            0x4f0a06,0x0d0b4d, MaterialIconSet.METALLIC,PropertyKey.INGOT),
    FURRISIUM("Furrisium","Fr",131,143,131,
            0xb34757,0x701c29, MaterialIconSet.METALLIC, PropertyKey.INGOT),
    NORMALIUM("Normalium","No",132,151,132,
            0x5148d9,0x151057, MaterialIconSet.METALLIC, PropertyKey.INGOT);

    @Getter
    public String name;
    @Getter
    public String symbol;
    @Getter
    public int protons;
    @Getter
    public int electrons;
    @Getter
    public int neutrons;
    @Getter
    public int primaryColour;
    @Getter
    public int secondaryColour;
    @Getter
    public MaterialIconSet materialIconSet;
    @Getter
    public PropertyKey[] propertyKeys;

    Element(String name, String symbol,
            int protons, int electrons, int neutrons,
            int primaryColour, int secondaryColour, MaterialIconSet materialIconSet, PropertyKey... keys) {
        this.name = name;
        this.symbol = symbol;
        this.protons = protons;
        this.electrons = electrons;
        this.neutrons = neutrons;
        this.primaryColour = primaryColour;
        this.materialIconSet = materialIconSet;
        this.secondaryColour = secondaryColour;
        this.propertyKeys = keys;
    }

    public void init(){}
}
