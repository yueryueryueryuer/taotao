package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ItemCatServiceImpl
 * @Description:
 * @author: TaoXiaoFeng
 * @date: 2018/4/4 14:29
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public CatResult getItemCatList() {
        CatResult catResult = new CatResult();
        //从根节点开始查询
        catResult.setData(getItemList(0L));
        return catResult;
    }

    /**
     * 查询商品分类列表
     *
     * @param parentId
     * @return
     */
    private List<?> getItemList(Long parentId) {
        //创造查询条件
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //开始查询
        List<TbItemCat> tbItemCatList = tbItemCatMapper.selectByExample(tbItemCatExample);
        //把查询出来的值 放入list中返回
        ArrayList<Object> list = new ArrayList<>();
        //只要14层，多了会超出左侧列表框
        int count =0;
        for (TbItemCat t : tbItemCatList) {
            //判断是否为父节点
            if (t.getIsParent()) {
                CatNode catNode = new CatNode();
                if (parentId == 0) {
                    catNode.setName("<a href='/products/" + t.getId() + ".html'>" + t.getName() + "</a>");
                } else {
                    catNode.setName(t.getName());
                }
                catNode.setUrl("/products/" + t.getId() + ".html");
                catNode.setItem(getItemList(t.getId()));
                list.add(catNode);
                //第一层只取14层
                count++;
                if (count >=14 && parentId ==0 ){
                    break;
                }
            } else {
                //如果是叶子节点
                list.add("/products/" + t.getId() + ".html|" + t.getName());
            }
        }
        return list;
    }
}
