package net.tefyer.eclipseallot.api.tag;

import lombok.Getter;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
            TagPrefixItemRenderer.create(this, tagPrefix.materialIconType, material.getMateralIconSet());
        }
    }

    public void onRegister() {}

    @OnlyIn(Dist.CLIENT)
    public static ItemColor tintColor() {
        return (itemStack, index) -> {
            if (itemStack.getItem() instanceof TagPrefixItem tagPrefixItem) {
                return tagPrefixItem.material.getLayerARGB(index);
            }
            return -1;
        };
    }
}
