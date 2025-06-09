package net.tefyer.eclipseallot.api.materials;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import lombok.*;
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

    public MaterialProperties() {
    }

    public MaterialProperties addProperty(PropertyKey key){
        KEYS.add(key);
        return this;
    }
}
