package net.tefyer.eclipseallot.api.chemical;

import lombok.Getter;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.api.materials.MaterialIconSet;
import net.tefyer.eclipseallot.api.property.PropertyKey;

public enum Element {
    // NATURALS
    FURRITE("Fr",1,1,1,
            0xb34757,0x701c29, ElementsClasses.NATURALS, PropertyKey.INGOT),
    CHAOSIUM("Ch",2,2,2,
            0xc43027,0x0d0b4d, ElementsClasses.NATURALS,PropertyKey.INGOT),
    MODGIN("Mo",3,3,3,
            0x3fcc44,0x197d1d,ElementsClasses.NATURALS, PropertyKey.INGOT),

    // UNREACTIVE
    NORMALIUM("No",7,7,7,
            0x5148d9,0x151057,ElementsClasses.UNREACTIVES, PropertyKey.INGOT),

    // ELETRICTS
    CODIUM("Cd",8,8,8,
            0x3fcc44,0x197d1d,ElementsClasses.ELETRICTS, PropertyKey.INGOT),
    COPPIN("Co",9,9,9,
            0xd6612b,0xb31f17,ElementsClasses.ELETRICTS, PropertyKey.INGOT),
    POLK("Po",10,10,10,
            0xe66963,0xa33e39,ElementsClasses.ELETRICTS, PropertyKey.INGOT),
    ENERGIST("En",48,50,48,
            0x38cf5b,0x188732,ElementsClasses.ELETRICTS, PropertyKey.INGOT),

    // UNSTABLES
    ASYLITE("Ay",49,53,49,
                   0xff0015,0x7a0b14,ElementsClasses.UNSTABLES, PropertyKey.DUST),
    ITEMDIUM("It",50,57,50,
                   0xb3b3b3,0x4f4f4f,ElementsClasses.UNSTABLES, PropertyKey.DUST),
    IDEOTE("Id",51,62,51,
                 0xe0ac1d,0x9e5715,ElementsClasses.UNSTABLES, PropertyKey.INGOT),
    GRINDITE("Gr",52,73,52,
                     0x949494,0x363636,ElementsClasses.UNSTABLES, PropertyKey.INGOT),
    COMMUNITE("Cm",53,136,53,
            0xff4d4d,0xb52828,ElementsClasses.UNSTABLES, PropertyKey.INGOT);
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
    public ElementsClasses elementsClasses;
    @Getter
    public PropertyKey[] propertyKeys;

    Element(String symbol,
            int protons, int electrons, int neutrons,
            int primaryColour, int secondaryColour,
            ElementsClasses elementsClasses,PropertyKey... keys) {
        this.name = APIUtils.Formatting.capitalizeFirstLetter(super.toString().toLowerCase());
        this.symbol = symbol;
        this.protons = protons;
        this.electrons = electrons;
        this.neutrons = neutrons;
        this.primaryColour = primaryColour;
        this.materialIconSet = elementsClasses.getMaterialIconSet();
        this.elementsClasses = elementsClasses;
        this.secondaryColour = secondaryColour;
        this.propertyKeys = keys;
    }

    public void init(){}
}
