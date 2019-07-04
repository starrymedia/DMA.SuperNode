package org.elastos.record.manage.util;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 接受jqueryDataTable 参数
 */
public class DataTableDto implements Serializable {

    private long start = 0;
    private long length = 10;


    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getPageSize() {

        return length;
    }


    public long getPageNumber() {
        long l = start / length + 1;
        return l < 0 ? 10 : l;
    }


    public DataTableDto() {
    }

    public DataTableDto(long start, long length) {
        this.start = start;
        this.length = length;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public static void main(String[] args) {
        DataTableDto dataTableDto = new DataTableDto(21, 10);
        System.out.println("dataTableDto = " + dataTableDto);
    }

}
