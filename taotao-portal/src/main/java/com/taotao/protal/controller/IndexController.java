package com.taotao.protal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: IndexController
 * @Description:
 * @author: TaoXiaoFeng
 * @date: 2018/4/4 09:37
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }
}
