package com.taotao.rest.service;

import com.taotao.rest.pojo.CatResult;

/**
 * @ClassName: ItemCatService
 * @Description:
 * @author: TaoXiaoFeng
 * @date: 2018/4/4 14:27
 */
public interface ItemCatService {

    /**
     * 查询itemCat表，返回data集合
     * @return
     */
    CatResult getItemCatList();
}
