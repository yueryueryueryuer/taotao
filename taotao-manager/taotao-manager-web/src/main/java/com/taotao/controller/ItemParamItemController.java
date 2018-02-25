package com.taotao.controller;

import com.taotao.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: ItemParamItemController
 * @Description: 商品参数展示
 * @author: TaoXiaoFeng
 * @date: 2018/2/11 23:29
 */
@Controller
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/showitem/{itemId}")
    private String showItemParam(@PathVariable Long itemId, Model model) {
        String s = itemParamItemService.getItemParamByItemId(itemId);
        model.addAttribute("itemParam",s);
        return "item";
    }
}
