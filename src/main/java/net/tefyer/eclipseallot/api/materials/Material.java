package net.tefyer.eclipseallot.api.materials;

import com.tterrag.registrate.util.entry.ItemEntry;
import lombok.Getter;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.api.chemical.Element;
import net.tefyer.eclipseallot.api.chemical.ElementStack;
import net.tefyer.eclipseallot.api.property.PropertyKey;
import net.tefyer.eclipseallot.registry.MaterialRegistry;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Material {

    @Getter
    private String id;

    @Getter
    private String chemicalString;

    private Double mass;
    @Getter
    private int magicalPower = -1;


    public List<ElementStack> elements;
    @Getter
    private MaterialProperties properties;

    protected Material(String id,int magical_power, List<ElementStack> elements,MaterialProperties properties) {
        this.properties = properties;
        this.elements = elements;
        this.id = id;
        this.magicalPower = magical_power;
    }

    public int getMagicalPower() {
        if(magicalPower == -1)
            calculateMagicalPower();
        return magicalPower;
    }

    private void calculateMagicalPower() {
        if(elements.size() == 1){
            magicalPower = elements.get(0).element().maginalPower;
            return;
        }

        magicalPower = 0;
        for(ElementStack entry : elements){
            magicalPower += entry.element().maginalPower* entry.size();
        }
    }

    public String getName(){
        return properties.getResourceLocation().getPath();
    }

    /**
     * Gets a specific color layer in ARGB.
     *
     * @param index the index of the layer [0,10). will crash if you pass values > 10.
     * @return Gets a specific color layer.
     */
    public int getMaterialARGB(int index) {
        return properties.colors.getInt(index) | 0xff000000;
    }

    public int getLayerARGB(int layerIndex) {
        // get 2nd digit as positive if emissive layer
        if (layerIndex < -100) {
            layerIndex = (Math.abs(layerIndex) % 100) / 10;
        }
        if (layerIndex > properties.colors.size() - 1 || layerIndex < 0) return -1;
        int layerColor = getMaterialARGB(layerIndex);
        if (layerColor != -1 || layerIndex == 0) return layerColor;
        else return getMaterialARGB(0);
    }

    public boolean isNull() {
        return this == MaterialRegistry.NULL;
    }


    public String getModid() {
        return properties.getResourceLocation().getNamespace();
    }


    public boolean hasProperty(PropertyKey propertyKey) {
        return properties.hasProperty(propertyKey);
    }
    public boolean hasProperty(PropertyKey... keys) {
        return properties.hasProperty(keys);
    }


    public MaterialIconSet getMateralIconSet() {
        return properties.getIconSet();
    }

    public String getUnlocalizedName() {
        return properties.getResourceLocation().toLanguageKey("material");
    }
    public MutableComponent getLocalizedName() {
        return Component.translatable(getUnlocalizedName());
    }

    public void calculateChemicalString() {
        if(isChemicalStringNull())
            chemicalString = "";
        for(ElementStack entry : elements){
            if(entry.size() > 1){
                chemicalString += entry.element().symbol+APIUtils.Number.toSmallDownNumbers(String.valueOf(entry.size()));
            }else{
                chemicalString += entry.element().symbol;
            }
        }
    }

    public boolean isChemicalStringNull() {
        return chemicalString == null;
    }

    public double mass() {
        if(mass == null){
            calculateMass();
        }
        return mass;
    }

    private void calculateMass() {
        mass = (double) 0;
        for(ElementStack element: elements){
            double mass = element.mass();
            this.mass += mass;
        }
    }

    public String getPENString() {
        int protons = 0,neutrons = 0,electrons = 0;
        for(ElementStack element: elements){
            protons += element.element().protons*element.size();
            neutrons += element.element().neutrons*element.size();
            electrons += element.element().electrons*element.size();
        }

        String penString = "";
        penString += "protons: "+protons+" ";
        penString += "electrons: "+electrons+" ";
        penString += "neutrons: "+neutrons;
        return penString;
    }


    public static class Builder{

        /*
         * Temporary value to use to determine how to calculate default RGB
         */
        private boolean averageRGB = false;

        public int magical_power = -1;
        public String name;
        public List<ElementStack> elements;
        public MaterialProperties properties;

        public Builder(ResourceLocation resourceLocation) {
            setProperties(new MaterialProperties(resourceLocation));
        }


        public Builder setProperties(MaterialProperties properties){
            this.properties = properties;
            return this;
        }

        public Builder iconSet(MaterialIconSet iconSet) {
            properties.setIconSet(iconSet);
            return this;
        }
        public Builder setMagicalPower(int magical_power) {
            this.magical_power = magical_power;
            return this;
        }
        public Builder setElement(ElementStack... elements){
            this.elements = List.of(elements);
            return this;
        }
        public Builder setElement(ElementStack element){
            if(elements == null){
                this.elements = new ArrayList<>();
            }
            elements.add(element);
            return this;
        }
        public Builder addProperty(PropertyKey... key){
            properties.addProperty(key);
            return this;
        }
        public Builder ingot(){
            properties.addProperty(PropertyKey.INGOT);
            iconSet(MaterialIconSet.METALLIC);
            return this;
        }


        public Builder dust(){
            properties.addProperty(PropertyKey.DUST);
            return this;
        }
        /**
         * Set the Color of this Material.<br>
         * Defaults to 0xFFFFFF unless {@link Builder#colorAverage()} was called, where
         * it will be a weighted average of the components of the Material.
         *
         * @param color         The RGB-formatted Color.
         */
        public Builder primaryColour(int color) {
            this.properties.colors.set(0, color);
            return this;
        }

        public Builder colorAverage() {
            this.averageRGB = true;
            return this;
        }

        /**
         * Set the secondary color of this Material.<br>
         * Defaults to 0xFFFFFF unless {@link Builder#colorAverage()} was called, where
         * it will be a weighted average of the components of the Material.
         *
         * @param colour The RGB-formatted Color.
         */
        public Builder secondaryColoor(int colour) {
            this.properties.colors.set(1, colour);
            return this;
        }


        public Material build(){
            return new Material(properties.getResourceLocation().getPath(),magical_power,elements,properties);
        }

    }
}
