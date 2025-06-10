package net.tefyer.eclipseallot.api.tag;

import lombok.Getter;
import net.minecraft.world.item.Item;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.client.tag.TagPrefixItemRenderer;

public class TagPrefixItem extends Item {
    @Getter
    TagPrefix tagPrefix;
    @Getter
    Material material;
    public TagPrefixItem(Properties properties, TagPrefix tagPrefix, Material material) {
        super(properties);
        this.tagPrefix = tagPrefix;
        this.material = material;
        if(Eclipseallot.isClientSide()){
            TagPrefixItemRenderer.create(this, tagPrefix.getMaterialIconType(), material.getMateralIconSet());
        }
    }
}
