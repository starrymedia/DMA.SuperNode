package org.elastos.record.manage.constant;

public class Constant {

    public static class ShiroHttpSession {
        public static final String CORE_USER_KEY = "CORE_USER";
        public static final String SIMPLE_AUTHORIZATION_INFO = "SIMPLE_AUTHORIZATION_INFO";
    }


    public static class Chain {
        public static final String CHAIN = "CHAIN";
    }


    public static class PageQuery {
        public static final Integer MAX_PAGE_SIZE = 100000;
    }

    public static class Revoke {
        public static final int BATCH_SIZE = 10000;
    }

    public class SearchTime {
        //时间区间分隔符号
        //2019-02-22 00:00:00 ~ 2019-03-20 00:00:00

        public static final String separator = " - ";
    }
}
