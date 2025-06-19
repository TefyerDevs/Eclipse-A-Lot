package net.tefyer.eclipseallot.api.magic;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.tefyer.eclipseallot.api.materials.Material;

public class MPDatabase {
    public Object2IntArrayMap<Item> NORMAL_ITEM_DATABASE = new Object2IntArrayMap<>();
    public Object2IntArrayMap<Material> MATERIAL_DATABASE = new Object2IntArrayMap<>();

    public void addItem(Item item, int mp){
        NORMAL_ITEM_DATABASE.put(item, mp);
    }

    public void addMaterial(Material item, int mp){
        MATERIAL_DATABASE.put(item, mp);
    }

    public void init(){

        addItem(Items.GOLD_INGOT,100);

    }
}
