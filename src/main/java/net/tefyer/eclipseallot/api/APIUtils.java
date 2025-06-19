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
import org.jetbrains.annotations.NotNull;

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
    public static class Number{
        /**
         * <p/>
         * This is worth exactly one normal Item.
         * This Constant can be divided by many commonly used Numbers such as
         * 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 24, ... 64 or 81
         * without loosing precision and is for that reason used as Unit of Amount.
         * But it is also small enough to be multiplied with larger Numbers.
         * <p/>
         * This is used to determine the amount of Material contained inside a prefixed Ore.
         * For example Nugget = M / 9 as it contains out of 1/9 of an Ingot.
         */
        public static final long M = 3628800;

        private static final int SMALL_DOWN_NUMBER_BASE = '\u2080';
        private static final int SMALL_UP_NUMBER_BASE = '\u2070';
        private static final int SMALL_UP_NUMBER_ONE = '\u00B9';
        private static final int SMALL_UP_NUMBER_TWO = '\u00B2';
        private static final int SMALL_UP_NUMBER_THREE = '\u00B3';
        private static final int NUMBER_BASE = '0';

        public static String toSmallUpNumbers(String string) {
            return checkNumbers(string, SMALL_UP_NUMBER_BASE, true);
        }
        public static String toSmallDownNumbers(String string) {
            return checkNumbers(string, SMALL_DOWN_NUMBER_BASE, false);
        }
        @NotNull
        private static String checkNumbers(String string, int smallUpNumberBase, boolean isUp) {
            char[] charArray = string.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                int relativeIndex = charArray[i] - NUMBER_BASE;
                if (relativeIndex >= 0 && relativeIndex <= 9) {
                    if (isUp) {
                        if (relativeIndex == 1) {
                            charArray[i] = SMALL_UP_NUMBER_ONE;
                            continue;
                        } else if (relativeIndex == 2) {
                            charArray[i] = SMALL_UP_NUMBER_TWO;
                            continue;
                        } else if (relativeIndex == 3) {
                            charArray[i] = SMALL_UP_NUMBER_THREE;
                            continue;
                        }
                    }
                    int newChar = smallUpNumberBase + relativeIndex;
                    charArray[i] = (char) newChar;
                }
            }
            return new String(charArray);
        }
    }
}
