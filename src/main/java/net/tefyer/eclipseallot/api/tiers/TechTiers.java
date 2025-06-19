package net.tefyer.eclipseallot.api.tiers;

import lombok.Getter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.registry.MaterialRegistry;

public enum TechTiers {
    LOW("Low", MaterialRegistry.MATERIAL.get("Furrite"),8,500,25,25,0x20c20c),
    BASIC("Basic", MaterialRegistry.ECLIPSIUM_ALLOY,32,2000,100,100,0x1a6dc7),
    ADVANCED("Advanced",MaterialRegistry.ESTATIUM_ALLOY,128,8000,400,400,0xcc5b23),
    NUCLEAR("Nuclear", MaterialRegistry.MATERIAL.get("Asylite"),512,32000,1600,1600,0xd12e98),
    FISSION("Fission", MaterialRegistry.MATERIAL.get("Ideote"),2048,128000 ,64000,64000,0xd4d4d4),
    HYPER("Hyper", MaterialRegistry.MATERIAL.get("Communite"),8192,512000 ,256000,256000,0xff0000)
    ;

    @Getter
    public String name;
    @Getter
    public Material material;
    @Getter
    public int usage;
    @Getter
    public int capacity;
    @Getter
    public int inputValue;
    @Getter
    public int outputValue;
    @Getter
    public int primaryColour;

    TechTiers(String name,Material material, int usage, int capacity, int input_value, int output_value, int primaryColour) {
        this.name = name;
        this.material = material;
        this.usage = usage;
        this.capacity = capacity;
        this.inputValue = input_value;
        this.outputValue = output_value;
        this.primaryColour = primaryColour;
    }

    public MutableComponent getLocalizedName() {
        return Component.translatable(getUnlocalizedName());
    }
    public String getUnlocalizedName() {
        return new ResourceLocation(Eclipseallot.MODID, id()).toLanguageKey("component");
    }

    public String id(){
        return name.toLowerCase();
    }
}
