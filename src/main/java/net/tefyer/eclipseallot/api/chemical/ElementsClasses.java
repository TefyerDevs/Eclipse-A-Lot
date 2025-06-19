package net.tefyer.eclipseallot.api.chemical;

import lombok.Getter;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.api.materials.MaterialIconSet;
import net.tefyer.eclipseallot.api.materials.MaterialIconType;

public enum ElementsClasses {
    NATURALS(MaterialIconSet.ROUGH),
    ELETRICTS(MaterialIconSet.METALLIC),
    NORMALS(MaterialIconSet.WOOD),
    UNSTABLES(MaterialIconSet.BRIGHT),
    UNREACTIVES(MaterialIconSet.DULL);

    @Getter
    MaterialIconSet materialIconSet;

    ElementsClasses(MaterialIconSet materialIconSet) {
        this.materialIconSet = materialIconSet;
    }

    @Override
    public String toString() {
        return APIUtils.Formatting.capitalizeFirstLetter(super.toString().toLowerCase());
    }
}
