package net.tefyer.eclipseallot.api;

import com.google.common.base.CaseFormat;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.tefyer.eclipseallot.Eclipseallot;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class APIUtils {
    public static Eclipseallot instance;

    public static class Formatting{

        /**
         * apple_orange.juice => Apple Orange (Juice)
         */
        public static String toEnglishName(Object internalName) {
            return Arrays.stream(internalName.toString().toLowerCase(Locale.ROOT).split("_"))
                    .map(StringUtils::capitalize)
                    .collect(Collectors.joining(" "));
        }

        /**
         * Does almost the same thing as .to(LOWER_UNDERSCORE, string), but it also inserts underscores between words and
         * numbers.
         *
         * @param string Any string with ASCII characters.
         * @return A string that is all lowercase, with underscores inserted before word/number boundaries:
         *         "maragingSteel300" -> "maraging_steel_300"
         */
        public static String toLowerCaseUnder(String string) {
            return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, string);
        }

        public static String toLowerCaseUnderscore(String string) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < string.length(); i++) {
                if (i != 0 && (Character.isUpperCase(string.charAt(i)) ||
                        (Character.isDigit(string.charAt(i - 1)) ^ Character.isDigit(string.charAt(i)))))
                    result.append("_");
                result.append(Character.toLowerCase(string.charAt(i)));
            }
            return result.toString();
        }
    }
    public static class Tag{

        public static <T> TagKey<T> optionalTag(Registry<T> registry, ResourceLocation id) {
            return TagKey.create(registry.key(), id);
        }

        public static <T> TagKey<T> optionalTag(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation id) {
            return TagKey.create(registryKey, id);
        }
        /**
         * Generates tag under Forge namespace
         *
         * @return Tag #forge:path
         */
        public static TagKey<Item> createItemTag(String path) {
            return createTag(BuiltInRegistries.ITEM, path, false);
        }

        /**
         * Generates tag under Forge namespace
         *
         * @param vanilla Whether to use vanilla namespace instead of Forge
         * @return optional tag #forge:path or #minecraft:path
         */
        public static <T> TagKey<T> createTag(Registry<T> registry, String path, boolean vanilla) {
            if (vanilla) return optionalTag(registry, new ResourceLocation("minecraft", path));
            return optionalTag(registry, new ResourceLocation("forge", path));
        }
        /**
         * Generates tag under Forge namespace
         *
         * @param vanilla Whether to use vanilla namespace instead of Forge
         * @return Tag #forge:path or #minecraft:path
         */
        public static TagKey<Item> createItemTag(String path, boolean vanilla) {
            return createTag(BuiltInRegistries.ITEM, path, vanilla);
        }
    }
}
