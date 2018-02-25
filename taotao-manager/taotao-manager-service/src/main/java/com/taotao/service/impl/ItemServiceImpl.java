package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Taoxiao on 2017/6/16 0016.
 * Describe:
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private TbItemMapper itemMapper;

    @Resource
    private TbItemDescMapper itemDescMapper;

    @Resource
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getItemById(long itemId) {
        //根据主键查询
        //TbItem item = itemMapper.selectByPrimaryKey(itemId);

        //添加查询条件查询
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example); //这样就根据itemId来查询了
        if (list!=null && list.size()>0){
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    /**
    *Author:Taoxiao
    *Describe: 商品列表查询
    */
    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        //查询商品列表
        TbItemExample example = new TbItemExample();
        //分页处理
        PageHelper.startPage(page,rows);
        List<TbItem> list = itemMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem item,String desc,String itemParams) throws Exception {
        //item补全
        //生成商品Id
        Long itemId= IDUtils.genItemId();
        item.setId(itemId);
        //'商品状态，1-正常，2-下架，3-删除'
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //把商品信息插入到数据库
        itemMapper.insert(item);
        //添加商品描述信息
        TaotaoResult result = insertItemDesc(itemId,desc);
        if (result.getStatus() != 200){
            throw new Exception();
        }
        //添加参数
        result = insertItemParamItem(itemId, itemParams);
        if (result.getStatus() != 200){
            throw new Exception();
        }
        return TaotaoResult.ok();
    }

    /**
     * 添加商品描述
     * @param desc
     */
    private TaotaoResult insertItemDesc(Long itemId , String desc){
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemParamItem(Long itemId, String itemParams) {
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(itemParams);
        Date now = new Date();
        tbItemParamItem.setCreated(now);
        tbItemParamItem.setUpdated(now);
        itemParamItemMapper.insert(tbItemParamItem);
        return TaotaoResult.ok();
    }

}
