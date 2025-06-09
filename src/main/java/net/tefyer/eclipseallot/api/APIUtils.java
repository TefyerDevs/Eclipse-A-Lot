package net.tefyer.eclipseallot.api;

import com.google.common.base.CaseFormat;
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
    }
}
