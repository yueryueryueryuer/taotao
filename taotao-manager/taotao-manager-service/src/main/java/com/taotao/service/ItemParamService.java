package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/**
 * Created by Taoxiao on 2017/7/20 0020.
 * Describe:商品规格模板接口
 */
public interface ItemParamService {
    TaotaoResult getItemParamByCid(long cid);

    /**
     * 插入一条规格参数
     * @param itemParam
     * @return
     */
    TaotaoResult insertItemParam(TbItemParam itemParam);
}
