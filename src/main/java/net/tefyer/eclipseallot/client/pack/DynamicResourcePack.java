package net.tefyer.eclipseallot.client.pack;

import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.resources.IoSupplier;
import net.tefyer.eclipseallot.Eclipseallot;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class DynamicResourcePack  implements PackResources {

    protected static final ObjectSet<String> CLIENT_DOMAINS = new ObjectOpenHashSet<>();
    protected static final DynamicPackContents CONTENTS = new DynamicPackContents();

    private final String name;

    public DynamicResourcePack(String name) {
        this(name, List.of());
    }

    public DynamicResourcePack(String name, Collection<String> domains) {
        this.name = name;
        CLIENT_DOMAINS.addAll(domains);
    }
    static {
        CLIENT_DOMAINS.addAll(Sets.newHashSet(Eclipseallot.MODID, "minecraft", "forge", "c"));
    }

    public static void addItemModel(ResourceLocation loc, JsonElement obj){
        ResourceLocation location = getItemModelLocation(loc);
        CONTENTS.addToData(location, obj.toString().getBytes(StandardCharsets.UTF_8));
    }
    public static void addItemModel(ResourceLocation loc, Supplier<JsonElement> obj) {
        addItemModel(loc, obj.get());
    }

    public static void clearClient() {
        CONTENTS.clearData();
    }

    @Nullable
    @Override
    public IoSupplier<InputStream> getRootResource(String... elements) {
        return null;
    }

    @Override
    public IoSupplier<InputStream> getResource(PackType type, ResourceLocation location) {
        if (type == PackType.CLIENT_RESOURCES) {
            return CONTENTS.getResource(location);
        }
        return null;
    }

    @Override
    public void listResources(PackType packType, String namespace, String path, ResourceOutput resourceOutput) {
        if (packType == PackType.CLIENT_RESOURCES) {
            CONTENTS.listResources(namespace, path, resourceOutput);
        }
    }

    @Override
    public Set<String> getNamespaces(PackType type) {
        return type == PackType.CLIENT_RESOURCES ? CLIENT_DOMAINS : Set.of();
    }

    @Nullable
    @Override
    public <T> T getMetadataSection(MetadataSectionSerializer<T> metaReader) {
        if (metaReader == PackMetadataSection.TYPE) {
            return (T) new PackMetadataSection(Component.literal(Eclipseallot.NAME +" dynamic assets"),
                    SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES));
        }
        return null;
    }

    @Override
    public String packId() {
        return this.name;
    }

    @Override
    public void close() {
        // NOOP
    }

    public static ResourceLocation getBlockStateLocation(ResourceLocation blockId) {
        return new ResourceLocation(blockId.getNamespace(),
                String.join("", "blockstates/", blockId.getPath(), ".json"));
    }

    public static ResourceLocation getModelLocation(ResourceLocation blockId) {
        return new ResourceLocation(blockId.getNamespace(), String.join("", "models/", blockId.getPath(), ".json"));
    }

    public static ResourceLocation getItemModelLocation(ResourceLocation itemId) {
        return new ResourceLocation(itemId.getNamespace(), String.join("", "models/item/", itemId.getPath(), ".json"));
    }

    public static ResourceLocation getTextureLocation(@Nullable String path, ResourceLocation tagId) {
        if (path == null) {
            return new ResourceLocation(tagId.getNamespace(), String.join("", "textures/", tagId.getPath(), ".png"));
        }
        return new ResourceLocation(tagId.getNamespace(),
                String.join("", "textures/", path, "/", tagId.getPath(), ".png"));
    }
}
