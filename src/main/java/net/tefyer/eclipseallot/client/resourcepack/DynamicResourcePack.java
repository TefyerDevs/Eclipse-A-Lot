package net.tefyer.eclipseallot.client.resourcepack;

import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class DynamicResourcePack {
    protected static final DynamicPackContents CONTENTS = new DynamicPackContents();

    public static void addItemModel(ResourceLocation loc, JsonElement obj){
        ResourceLocation location = getItemModelLocation(loc);
        if (ConfigHolder.INSTANCE.dev.dumpAssets) {
            Path parent = GTCEu.getGameDir().resolve("gtceu/dumped/assets");
            writeJson(l, null, parent, obj);
        }
        CONTENTS.addToData(location, obj.toString().getBytes(StandardCharsets.UTF_8));
    }
}
