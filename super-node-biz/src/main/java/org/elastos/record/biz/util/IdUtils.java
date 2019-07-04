package org.elastos.record.biz.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.elastos.record.utility.util.DateUtils;


import java.util.UUID;


public class IdUtils extends org.apache.commons.lang.StringUtils {

    public static String get() {
        return DateUtils.formatYYYYMMddHHmmssSSS() + RandomStringUtils.random(4, "123456789");
    }

    public static String get(int count) {
        return RandomStringUtils.random(count, "123456789");
    }

    public static String getUUID() {
        String string = UUID.randomUUID().toString().replaceAll("-", "");
        return string;
    }

}
