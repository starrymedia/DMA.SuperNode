package org.elastos.record.utility.util;


import org.elastos.record.utility.denum.Action;
import org.elastos.record.utility.exception.ApiException;

public class ObjectUtils {
    /**
     * 检查传入参数是否包含空
     * @param strings
     * @return
     */
    public static boolean hasBlank(Object... strings) {
        for (Object string : strings) {
            if (isBlank(string)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否为空
     * @param string
     * @return
     */
    private static boolean isBlank(Object string) {
        return string == null || string.toString().trim().equals("");
    }



    public static void check(Object[]... objects) throws ApiException {

        for (Object[] object : objects) {
            Object key = object[0];
            Object value = object[1];

            if (key != null) {
                if (key instanceof Boolean) {
                    if ((boolean) key) {
                        checkEmptyOrBlank(value);
                    }
                }
            } else {
                checkEmptyOrBlank(value);
            }

        }
    }

    private static void checkEmptyOrBlank(Object value) throws ApiException {
        if (value instanceof Action) {
            Action action = (Action) value;
            throw new ApiException(action);
        } else {
            throw new ApiException(Action.PARAMS_ERROR, "【" + value + "】：参数值不能为空或空串");
        }
    }

    public static void checkEmpty(Object ... value) throws ApiException {
        if(hasBlank(value)){
            throw new ApiException(Action.PARAMS_ERROR, "【" + value + "】：参数值不能为空或空串");
        }
    }
}
