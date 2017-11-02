package com.taotao.common.pojo;

import java.util.List;

/**
 * Created by Taoxiao on 2017/6/18 0018.
 * Describe:被多个项目使用的pojo,返回一个EasyUIDateGrid支持的数据格式
 */
public class EUDataGridResult {
    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
