package net.tefyer.eclipseallot.api.tag;

import lombok.Getter;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.api.materials.MaterialIconSet;
import net.tefyer.eclipseallot.api.tiers.TechTiers;
import net.tefyer.eclipseallot.client.tag.TagPrefixItemRenderer;

public class TagPrefixComponentItem extends Item {
    @Getter
    TagPrefix tagPrefix;
    @Getter
    TechTiers techTiers;
    public TagPrefixComponentItem(Properties properties, TagPrefix tagPrefix, TechTiers techTiers) {
        super(properties);
        this.tagPrefix = tagPrefix;
        this.techTiers = techTiers;
        if(Eclipseallot.isClientSide()){
            TagPrefixItemRenderer.create(this, tagPrefix.materialIconType, MaterialIconSet.COMPONENT);
        }
    }

    public void onRegister() {}

    @OnlyIn(Dist.CLIENT)
    public static ItemColor tintColor() {
        return (itemStack, index) -> {
            if (itemStack.getItem() instanceof TagPrefixComponentItem tagPrefixItem) {
                if(index == 1){
                    return tagPrefixItem.techTiers.primaryColour;
                }
            }
            return -1;
        };
    }


    @Override
    public String getDescriptionId() {
        return tagPrefix.getUnlocalizedName(techTiers);
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return tagPrefix.getUnlocalizedName(techTiers);
    }

    @Override
    public Component getDescription() {
        return tagPrefix.getLocalizedName(techTiers);
    }

    @Override
    public Component getName(ItemStack stack) {
        return getDescription();
    }
}
