package org.elastos.record.manage.util;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.elastos.dma.utility.dmaenum.Currency;
import org.elastos.record.utility.denum.Action;
import org.elastos.record.utility.exception.ApiException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: YangChuanTong
 * @Date: 2019/4/29 19:41
 * @Description:
 */
public class MarketUtils {

    private static Map<String, BigDecimal> cacheMap = new HashMap<String, BigDecimal>();


    public static BigDecimal getRate(Currency from, Currency to) {
        final String url = "http://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";
        TreeMap<String, Object> map = new TreeMap<>();
        map.put("convert", to.name());
        map.put("symbol", from.name());
        String result = httpGet(url, map);
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject ststus = jsonObject.getJSONObject("status");
        Integer error_code = ststus.getInteger("error_code");
        String cacheKey = from.name() + "_" + to.name();
        if (error_code == 0) {
            JSONObject data = jsonObject.getJSONObject("data");
            JSONObject ELA = data.getJSONObject(from.name());
            JSONObject quote = ELA.getJSONObject("quote");
            JSONObject USD = quote.getJSONObject(to.name());
            BigDecimal price = USD.getBigDecimal("price").setScale(4, 1);
            cacheMap.put(cacheKey, price);
            return price;
        } else {
            return cacheMap.get(cacheKey);
        }

    }


    //获取ELA行情
    public static String httpGet(String url, Map<String, Object> map) {
        try {
            final int TIME_OUT = 60;
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .build();
            FormBody.Builder builder = new FormBody.Builder();

            if (map == null) {
                map = new HashMap<>();
            }
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() == null) {
                    continue;
                }
                builder.add(entry.getKey(), entry.getValue().toString());
            }
            Request request = new Request.Builder().url(url + maptoUrl(map)).get().header("Accept", "application/json").addHeader("X-CMC_PRO_API_KEY", "334f81f0-facf-495d-abe1-adfef9f311f8").build();
            Response response = okHttpClient.newCall(request).execute();
            ResponseBody body = response.body();
            return body == null ? null : body.string();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    private static String maptoUrl(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder("?");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }
            stringBuilder.append(key).append("=").append(value.toString()).append("&");

        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }


    interface ELA {
        int SCALE = 4;
    }

    private static int ela_dma_rate = 1000;

    public static BigDecimal convertDmaToUsd(BigDecimal dma) throws ApiException {
        BigDecimal elaAmount = dma.divide(new BigDecimal(ela_dma_rate), ELA.SCALE, BigDecimal.ROUND_UP);
        BigDecimal elaToUsdtRate = getRate(Currency.ELA, Currency.USD);// = 2.xxxx
        if (elaToUsdtRate != null) {
            BigDecimal multiply = elaAmount.multiply(elaToUsdtRate);
            return multiply;
        }
        throw new ApiException(Action.RPC_ERROR);
    }


    /**
     * 转换ela到usdt
     *
     * @param ela
     * @return
     * @throws ApiException
     */
    public static BigDecimal convertElaToUsd(BigDecimal ela) throws ApiException {
        BigDecimal elaToUsdtRate = getRate(Currency.ELA, Currency.USD);
        if (elaToUsdtRate != null) {
            return ela.multiply(elaToUsdtRate);
        }
        throw new ApiException(Action.RPC_ERROR);
    }

    public static BigDecimal convertUsdToEla(BigDecimal usd) throws ApiException {
        BigDecimal elaToUsdtRate = convertElaToUsd(new BigDecimal(1)); // 3
        if (elaToUsdtRate != null) {
            return usd.divide(elaToUsdtRate, 10, RoundingMode.HALF_UP);
        }
        throw new ApiException(Action.RPC_ERROR);
    }

    public static void main(String[] args) throws ApiException {
        BigDecimal bigDecimal = convertUsdToEla(new BigDecimal(20));
        System.out.println("bigDecimal = " + bigDecimal);
    }


}
