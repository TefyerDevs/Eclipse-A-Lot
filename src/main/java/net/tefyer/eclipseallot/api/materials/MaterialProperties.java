package net.tefyer.eclipseallot.api.materials;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import lombok.*;
import net.minecraft.resources.ResourceLocation;
import net.tefyer.eclipseallot.api.property.PropertyKey;

import java.util.ArrayList;
import java.util.List;


public class MaterialProperties {

    /**
     * The colors of this Material.
     * if any past index 0 are -1, they aren't used.
     * <p>
     * Default: 0xFFFFFF if no Components, otherwise it will be the average of Components.
     */

    public IntList colors = IntArrayList.of(-1, -1);


    @Getter
    private List<PropertyKey> KEYS = new ArrayList<>();

    @Getter
    @Setter
    private ResourceLocation resourceLocation;

    /**
     * The IconSet of this Material.
     * <p>
     * Default: - GEM_VERTICAL if it has GemProperty.
     * - DULL if has DustProperty or IngotProperty.
     */
    @Getter
    @Setter
    private MaterialIconSet iconSet;


    public MaterialProperties(ResourceLocation location) {
        this.resourceLocation = location;
    }

    public MaterialProperties addProperty(PropertyKey key){
        KEYS.add(key);
        return this;
    }

    public boolean hasProperty(PropertyKey propertyKey) {
        return KEYS.contains(propertyKey);
    }
}
