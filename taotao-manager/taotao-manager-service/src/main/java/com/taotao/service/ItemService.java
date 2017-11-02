package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * Created by Taoxiao on 2017/6/16 0016.
 * Describe:商品查询和添加
 */
public interface ItemService {
    TbItem getItemById(long itemId);

    //common中的pojo
    EUDataGridResult getItemList(int page,int rows);
    //添加的商品加入数据库
    TaotaoResult createItem(TbItem item,String desc) throws Exception;
}
