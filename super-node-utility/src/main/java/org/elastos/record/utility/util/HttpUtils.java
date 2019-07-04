package org.elastos.record.utility.util;//


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import okhttp3.*;
import okhttp3.MultipartBody.Builder;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;


public class HttpUtils {
    private static final int TIME_OUT = 60;
    private static final OkHttpClient okHttpClient;
    private static final String contentTypeJson = "application/json;charset=utf-8";

    public HttpUtils() {
    }

    public static String uploadFile(String url, File upFile) throws IOException {
        Builder builder = new Builder();
        if (upFile.exists()) {
            String TYPE = "application/octet-stream";
            RequestBody fileBody = RequestBody.create(MediaType.parse(TYPE), upFile);
            RequestBody requestBody = builder.setType(MultipartBody.FORM).addFormDataPart("file", upFile.getName(), fileBody).build();
            Request request = (new okhttp3.Request.Builder()).url(url).put(requestBody).build();
            Response execute = okHttpClient.newCall(request).execute();
            String string = execute.body().string();
            return string;
        } else {
            throw new FileNotFoundException();
        }
    }

    public static String post(String url) {
        return post(url, (Map)null);
    }

    public static String post(String url, String jsonString) {
        return post(url, (Map)JSONObject.parseObject(jsonString));
    }

    public static String post(String url, Map<String, Object> map) {
        try {
            okhttp3.FormBody.Builder builder = new okhttp3.FormBody.Builder();
            if (map == null) {
                map = new HashMap();
            }

            if (((Map)map).isEmpty()) {
                ((Map)map).put("REQ", DateUtils.formatYYYYMMddHHmmssSSS());
            }

            JSONObject jsonObject = new JSONObject();
            Iterator var4 = ((Map)map).entrySet().iterator();

            while(true) {
                Entry entry;
                String value;
                do {
                    do {
                        do {
                            if (!var4.hasNext()) {
                                System.out.println("请求url:  " + url);
                                System.out.println("请求报文:  " + jsonObject.toJSONString());
                                RequestBody formBody = builder.build();
                                Request request = (new okhttp3.Request.Builder()).url(url).post(formBody).build();
                                Response response = okHttpClient.newCall(request).execute();
                                ResponseBody body = response.body();
                                String s = body == null ? null : body.string();
                                System.out.println("响应:  " + s);
                                return s;
                            }

                            entry = (Entry)var4.next();
                        } while(entry.getValue() == null);
                    } while(!StringUtils.isNotBlank(entry.getValue().toString()));

                    value = entry.getValue().toString();
                    if (entry.getValue() instanceof Date) {
                        value = DateUtils.formatDate((Date)entry.getValue());
                        break;
                    }
                } while(((String)entry.getKey()).endsWith("Time"));

                builder.add((String)entry.getKey(), value);
                jsonObject.put((String)entry.getKey(), value);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public static String get(String getUrl) {
        return get(getUrl, (Map)null);
    }

    public static String get(String url, Map<String, Object> map) {
        try {
            if (map != null && !map.isEmpty()) {
                url = url + toUrl(map);
            }

            System.out.println("请求地址：" + url);
            Request request = (new okhttp3.Request.Builder()).url(url).get().build();
            Response response = okHttpClient.newCall(request).execute();
            ResponseBody body = response.body();
            return body == null ? null : body.string();
        } catch (IOException var5) {
            var5.printStackTrace();
            return null;
        }
    }

    private static String toUrl(Map<String, Object> map) {
        if (map == null) {
            return "";
        } else {
            StringBuilder stringBuilder = new StringBuilder("?");
            Iterator var2 = map.entrySet().iterator();

            while(var2.hasNext()) {
                Entry<String, Object> entry = (Entry)var2.next();
                String key = (String)entry.getKey();
                Object value = entry.getValue();
                if (value != null) {
                    stringBuilder.append(key).append("=").append(value.toString()).append("&");
                }
            }

            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }
    }

    public static <U> JsonResult<U> post(String url, TypeReference<JsonResult<U>> jsonResultTypeReference) {
        String post = post(url);
        return (JsonResult)JSONObject.parseObject(post, jsonResultTypeReference, new Feature[0]);
    }

    public static <U> JsonResult<U> post(String url, JSONObject jsonObject, TypeReference<JsonResult<U>> jsonResultTypeReference) {
        String post = post(url, (Map)jsonObject);
        return (JsonResult)JSONObject.parseObject(post, jsonResultTypeReference, new Feature[0]);
    }



    public static <U> U post(String url, Map<String, Object> kvHashMap, TypeReference<U> typeReference) {
        String post = post(url, kvHashMap);
        return JSONObject.parseObject(post, typeReference, new Feature[0]);
    }

    static {
        okHttpClient = (new okhttp3.OkHttpClient.Builder()).connectTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).build();
    }
}
