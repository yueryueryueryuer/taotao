package com.taotao.service.impl;

import com.sun.tools.javac.resources.compiler;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taoxiao on 2017/6/19 0019.
 * Describe:
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EUTreeNode> getItemCatList(long parentId) {
        //根据parentId查询分类列表
        TbItemCatExample example = new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        //分类列表转换成TreeNode的列表
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbItemCat tbItemCat : list) {
            //创建一个TreeNode对象
            EUTreeNode node = new EUTreeNode(tbItemCat.getId(), tbItemCat.getName(),
                    tbItemCat.getIsParent() ? "closed" : "open");
            resultList.add(node);
        }

        return resultList;

    }
}
