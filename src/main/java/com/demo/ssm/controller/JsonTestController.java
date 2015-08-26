package com.demo.ssm.controller;

import com.demo.ssm.po.ItemsCustom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gmy on 15/8/10.
 * json交互的测试
 */
@Controller
public class JsonTestController {

    /**
     *请求json,输出json
     * @param itemsCustom
     * @return
     * @RequestBody 将json转成java对象
     * @ResponseBody 将java对象转换成json对象输出
     */
    @RequestMapping("/requestJson")
    public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom) {
        return itemsCustom;
    }
    /**
     *请求key/value,输出json
     * @param itemsCustom
     * @return
     * @ResponseBody 将java对象转换成json对象输出
     */
    @RequestMapping("/responseJson")
    public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom) {
        return itemsCustom;
    }
}
