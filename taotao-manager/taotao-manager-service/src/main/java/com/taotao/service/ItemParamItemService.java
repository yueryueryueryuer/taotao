package com.taotao.service;

/**
 * @ClassName: ItemParamItemService
 * @Description: 商品与规格参数中间表
 * @author: TaoXiaoFeng
 * @date: 2018/2/11 22:35
 */
public interface ItemParamItemService {

    /**
     * 通过商品id获取商品规格参数
     * @param itemId
     * @return
     */
    String getItemParamByItemId(Long itemId);
}
