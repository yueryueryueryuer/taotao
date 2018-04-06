package com.taotao.rest.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;

/**
 * @ClassName: itemCatController
 * @Description:
 * @author: TaoXiaoFeng
 * @date: 2018/4/4 15:14
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 返回json数据，jsonp callback      第一种方法
     * @param callback
     * @return
     */
//    @RequestMapping(value = "/itemCat/list",
//        produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
//    @ResponseBody
//    public String getItemCatList(String callback) {
//        CatResult itemCatList = itemCatService.getItemCatList();
//        String json = JsonUtils.objectToJson(itemCatList);
//        return callback + "(" + json +");";
//    }

    /**
     * 使用MappingJacksonValue对象包装返回结果，并设置jsonp的回调方法    第二种方法
     * @param callback
     * @return
     */
    @RequestMapping(value = "/itemCat/list")
    @ResponseBody
    public Object getItemCatList(String callback) {
        CatResult itemCatList = itemCatService.getItemCatList();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(itemCatList);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;

    }
}
