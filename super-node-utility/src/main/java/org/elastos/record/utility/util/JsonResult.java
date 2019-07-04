package org.elastos.record.utility.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import org.elastos.record.utility.denum.Action;
import org.elastos.record.utility.exception.ApiException;
import org.elastos.record.utility.exception.InsertNewInstanceException;

import java.io.Serializable;
import java.util.Map;


/**
 * 结果集工具类
 */
@Data
public class JsonResult<T> implements Serializable {

    private String code;
    private Boolean success;
    private String msg;
    private T data;
    private JSONObject map = new JSONObject();

    public JsonResult() {

    }

    public JsonResult(boolean success, String code, String message) {
        this(success, code, message, null);
    }

    public JsonResult(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.msg = message;
        this.data = data;
        System.out.println(JSONObject.toJSONString(data));
    }


    public static <T> JsonResult<T> success() {
        return new JsonResult<>(true, Action.SUCCESS.getCode(),Action. SUCCESS.getMsg());
    }

    public static <U> JsonResult<U> success(U data) {
        return new JsonResult<>(true,Action. SUCCESS.getCode(),Action. SUCCESS.getMsg(), data);
    }


    public static <T> JsonResult<T> success(Action action) {
        return new JsonResult<>(true, action.getCode(), action.getMsg());
    }


    public static <U> JsonResult<U> error(U data) {
        return new JsonResult<>(false,Action. ERROR.getCode(),Action. ERROR.getMsg(), data);

    }

    public static <U> JsonResult<U> error(ApiException exception) {
        return new JsonResult<>(false, exception.getCode(), exception.getMessage());

    }

    public static <U> JsonResult<U> error(InsertNewInstanceException exception) {
        return new JsonResult<>(false, exception.getCode(), exception.getMessage());

    }

    public static <T> JsonResult<T> error(String code, String message) {
        return new JsonResult<>(false, code, message);
    }

    public static <T> JsonResult<T> error(Action action) {
        return new JsonResult<>(false, action.getCode(), action.getMsg());
    }




    public JsonResult<T> setCode(String code) {
        this.code = code;
        return this;
    }


    public JsonResult<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }


    public JsonResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }


    public JsonResult<T> setData(T data) {
        this.data = data;
        return this;
    }
    public JsonResult<T> put(String key , Object value){
          this.map.put(key,value);
          return this;
    }
    public JsonResult<T> putAll(Map<? extends String, ?> param){
        this.map.putAll(param);
        return this;
    }
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
