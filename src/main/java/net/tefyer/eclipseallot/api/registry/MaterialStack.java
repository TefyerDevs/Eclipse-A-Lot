package net.tefyer.eclipseallot.api.registry;

import net.tefyer.eclipseallot.api.materials.Material;
import net.tefyer.eclipseallot.registry.MaterialRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.WeakHashMap;

public record MaterialStack(@NotNull Material material, long amount) {

    public static final MaterialStack EMPTY = new MaterialStack(MaterialRegistry.NULL, 0);

    private static final Map<String, MaterialStack> PARSE_CACHE = new WeakHashMap<>();

    public MaterialStack copy() {
        if (isEmpty()) return EMPTY;
        return new MaterialStack(material, amount);
    }

    public static MaterialStack fromString(CharSequence str) {
        String trimmed = str.toString().trim();
        String copy = trimmed;

        var cached = PARSE_CACHE.get(trimmed);

        if (cached != null) {
            return cached;
        }

        var count = 1;
        var spaceIndex = copy.indexOf(' ');

        if (spaceIndex >= 2 && copy.indexOf('x') == spaceIndex - 1) {
            count = Integer.parseInt(copy.substring(0, spaceIndex - 1));
            copy = copy.substring(spaceIndex + 1);
        }

        cached = new MaterialStack(MaterialRegistry.get(copy), count);
        PARSE_CACHE.put(trimmed, cached);
        return cached;
    }

    public boolean isEmpty() {
        return this.material == MaterialRegistry.NULL || this.amount < 1;
    }

}
