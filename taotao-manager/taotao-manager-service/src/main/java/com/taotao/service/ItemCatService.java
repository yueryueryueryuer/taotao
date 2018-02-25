package com.taotao.service;



import com.taotao.common.pojo.EUTreeNode;

import java.util.List;

/**
 * Created by Taoxiao on 2017/6/19 0019.
 * Describe:商品分类
 */
public interface ItemCatService {
    List<EUTreeNode> getItemCatList(long parentId);
}
