package net.tefyer.eclipseallot.api.materials;

import lombok.Getter;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MaterialItem extends Item {
    @Getter
    MaterialProperties properties;
    @Getter
    Material material;
    public MaterialItem(MaterialProperties properties, Material material) {
        super(new Item.Properties());
        this.properties = properties;
        this.material = material;
    }

    public static ItemColor tintColor() {
        return (itemStack, index) ->{
            if(itemStack.getItem() instanceof MaterialItem materialItem){
                return materialItem.material.getLayerARGB(index);
            }
            return -1;
        };
    }
}
