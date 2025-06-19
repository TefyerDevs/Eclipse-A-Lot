package net.tefyer.eclipseallot.api.tag;

import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.client.tag.TagPrefixItemRenderer;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

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
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents,
                                TooltipFlag isAdvanced) {
        if(material.isChemicalStringNull()){
            material.calculateChemicalString();
        }
        tooltipComponents.add(Component.literal(material.getChemicalString()).withStyle(ChatFormatting.YELLOW));
        tooltipComponents.add(Component.literal(material.getPENString())
                .withStyle(ChatFormatting.YELLOW));
        tooltipComponents.add(Component.literal("mass: "+ APIUtils.Formatting.roundDecimalPlaces(material.mass(),2))
                .withStyle(ChatFormatting.YELLOW));
        if(material.elements.size() == 1){
            tooltipComponents.add(Component.literal("Atomic Number: "+  material.elements.get(0).element().getProtons())
                    .withStyle(ChatFormatting.YELLOW));
            tooltipComponents.add(Component.literal("Material Class: " +
                    material.elements.get(0).element().getElementsClasses().toString()).withStyle(ChatFormatting.YELLOW));
        }
        if(material.getMagicalPower() != 0){
            tooltipComponents.add(Component.literal("Magical Power: " +
                    material.getMagicalPower()).withStyle(ChatFormatting.YELLOW));
        }
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }

    @Override
    public String getDescriptionId() {
        return tagPrefix.getUnlocalizedName(material);
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return tagPrefix.getUnlocalizedName(material);
    }

    @Override
    public Component getDescription() {
        return tagPrefix.getLocalizedName(material);
    }

    @Override
    public Component getName(ItemStack stack) {
        return getDescription();
    }

}
