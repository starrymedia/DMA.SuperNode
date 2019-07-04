package org.elastos.record.utility.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


import org.elastos.record.utility.denum.Action;
import org.elastos.record.utility.exception.InsertNewInstanceException;
import org.elastos.record.utility.util.JsonResult;


@EqualsAndHashCode(callSuper = true)
@Data
public class ResultMsg extends JsonResult<Object> {

    private boolean success = true;
    private String msg = "操作成功";

    private String code = "0";

    private Object data;


    public String getCode() {
        return this.code;
    }

    public JsonResult<Object> setCode(String code) {
        this.code = code;
        return this;
    }

    public ResultMsg() {
    }

    public ResultMsg(Object data) {
        this.data = data;
    }

    public ResultMsg(String msg, boolean status) {
        this.msg = msg;
        this.success = status;
        this.code = "1";
    }

    public static ResultMsg ok() {
        return new ResultMsg();
    }

    public static ResultMsg ok(Object data) {
        return new ResultMsg(data);
    }

    public static ResultMsg fail() {
        return new ResultMsg("操作失败", false);
    }

    public static ResultMsg fail(Action action) {
        return new ResultMsg(action.getMsg(), false);
    }

    public static ResultMsg fail(String msg) {
        return new ResultMsg(msg, false);
    }

    public static ResultMsg fail(InsertNewInstanceException e) {
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(e.getCode());
        resultMsg.setMsg(e.getMessage());
        return resultMsg;
    }


    public ResultMsg setSuccess(boolean success) {
        this.success = success;
        return this;
    }


    public ResultMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public void setInfo(String msg) {
        this.msg = msg;
    }


    public ResultMsg setData(Object data) {
        this.data = data;
        return this;
    }


}
