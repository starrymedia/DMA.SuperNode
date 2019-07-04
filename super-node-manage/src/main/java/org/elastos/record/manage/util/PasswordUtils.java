package org.elastos.record.manage.util;


import org.elastos.record.utility.util.MD5Util;

/**
 * 用于密码混合加密
 */
public class PasswordUtils {

    public static String sign(String password, String obfuscationCode) {
        return MD5Util.MD5(password + obfuscationCode);
    }

    public static boolean verify(String password, String obfuscationCode) {
        return false;
    }
}
