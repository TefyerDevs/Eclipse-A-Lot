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

    String id;

    public MaterialItem(String id, MaterialProperties properties, Material material) {
        super(new Item.Properties());
        this.properties = properties;
        this.material = material;
        this.id = id;
    }

}

