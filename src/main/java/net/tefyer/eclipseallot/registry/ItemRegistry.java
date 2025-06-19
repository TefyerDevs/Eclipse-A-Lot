package net.tefyer.eclipseallot.registry;

public class ItemRegistry {

    public static void init(){
        MaterialItemRegistry.generateMaterialItems();
        ComponentItemRegistry.generateComponentItems();
    }

}
