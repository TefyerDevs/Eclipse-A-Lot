package net.tefyer.eclipseallot.api.materials;

import com.tterrag.registrate.util.entry.ItemEntry;
import lombok.Getter;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.api.property.PropertyKey;
import net.tefyer.eclipseallot.registry.MaterialRegistry;

import java.util.function.Supplier;

public class Material {

    @Getter
    private String id;

    @Getter
    private MaterialProperties properties;

    protected Material(String id, MaterialProperties properties) {
        this.id = id;
        this.properties = properties;
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


    public String getName() {
        return properties.getResourceLocation().getPath();
    }

    public String getModid() {
        return properties.getResourceLocation().getNamespace();
    }


    public boolean hasProperty(PropertyKey propertyKey) {
        return properties.hasProperty(propertyKey);
    }

    public MaterialIconSet getMateralIconSet() {
        return properties.getIconSet();
    }


    public static class Builder{

        /*
         * Temporary value to use to determine how to calculate default RGB
         */
        private boolean averageRGB = false;

        public String id;
        public MaterialProperties properties;

        public Builder(ResourceLocation resourceLocation) {
            setProperties(new MaterialProperties(resourceLocation));
        }

        public Builder setId(String id){
            this.id = id;
            return this;
        }

        public Builder setProperties(MaterialProperties properties){
            this.properties = properties;
            return this;
        }

        public Builder iconSet(MaterialIconSet iconSet) {
            properties.setIconSet(iconSet);
            return this;
        }
        public Builder ingot(){
            properties.addProperty(PropertyKey.INGOT);
            iconSet(MaterialIconSet.METALLIC);
            return this;
        }

        public Builder rawIngot(){
            properties.addProperty(PropertyKey.RAW_INGOT);
            iconSet(MaterialIconSet.DULL);
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
            return new Material(id,properties);
        }
    }
}
