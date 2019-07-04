package org.elastos.record.utility.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DecimalFormatUtil {

    public static String format(BigDecimal value, int scale) {
        scale = scale <= 0 ? 2 : scale;
        StringBuilder pattern = new StringBuilder("0.");
        for (int i = 0; i < scale; i++) {
            pattern.append("0");
        }
        return new DecimalFormat(pattern.toString()).format(value);
    }
}
