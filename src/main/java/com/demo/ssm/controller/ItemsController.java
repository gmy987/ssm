package com.demo.ssm.controller;

import com.demo.ssm.po.ItemsCustom;
import com.demo.ssm.po.ItemsQueryVo;
import com.demo.ssm.service.ItemsService;
import com.demo.ssm.validation.ValidGroup1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by gmy on 15/8/4.
 */
@Controller
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    ItemsService itemsService;

    /**
     * @ModelAttribute 此注解将本方法的返回值填充到request域中
     * @return
     */
    @ModelAttribute("itemsType")
    public Map<Integer, String> getItemsType() {
        Map<Integer, String> itemsTypeMap = new HashMap<>();
        itemsTypeMap.put(101, "数码产品");
        itemsTypeMap.put(102, "服装");
        return itemsTypeMap;
    }

    /**
     *
     * @param model
     * @return
     * @throws Exception
     * @Description: 商品查询
     * controller方法的形参默认支持的类型: HttpServletRequest,HttpServletResponse,HttpSession,
     * Model/ModelMap(Model是一个接口,ModelMap是一个接口实现) 作用: 将model数据填充到request域
     *
     */
    //商品查询
    @RequestMapping("/queryItems")
    public String queryItems(ModelMap model,ItemsQueryVo itemsQueryVo) throws Exception {
        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
//        System.out.println(itemsList);
        model.addAttribute("itemsList", itemsList);
        return "items";
    }

    /**
     *
     * @param model
     * @param itemsId
     * @return
     * @throws Exception
     * @Description: 商品信息修改页面展示
     * @RequestParam 可以对简单类型进行绑定
     *               如果不使用这个注解,要求传入参数名称和形参名称一致
     *               required属性指定此参数是否必须
     *               defaultValue属性可以设置默认值
     *               @RequestParam(value = "id",required = true) Integer itemsId
     * @PathVariable 另一种传参方式,使用RESTful风格
     *               @RequestMapping(value = "/editItems/{id}",method = {RequestMethod.POST,RequestMethod.GET})
     *               @PathVariable("id") Integer itemsId
     *
     */
    @RequestMapping(value = "/editItems/{id}",method = {RequestMethod.POST,RequestMethod.GET})
    public String editItems(ModelMap model,@PathVariable("id") Integer itemsId) throws Exception {
        ItemsCustom itemsCustom = itemsService.findById(itemsId);
//        判断商品是否为空,根据id没有查询到商品则抛出异常,提示用户商品信息不存在
//        与业务相关,在service中抛出
//        if (itemsCustom == null) {
//            throw new CustomException("修改的商品信息不存在");
//        }
        model.addAttribute("itemsCustom", itemsCustom);
        return "editItems";
    }


    /**
     * @param id            对于简单类型,若想实现数据回显,需要使用model.addAttribute()方法,手动将其添加到request域
     * @param itemsCustom   使用@validated注解进行校验,validated注解中的value可以指定分组
     *                      @ModelAttribute 注解将该pojo对象放入request域,springmvc默认使用数据回显(页面出错后,仍可以显示之前的数据),
     *                                      即将pojo放入request域，默认名称为类名首字母小写,使用@ModelAttribute注解则可以指定request的key的名称
     * @param itemsPic      接收商品的图片
     * @param bindingResult 接收校验出错的信息,和@validated配对出现,且必须紧跟在@validated后面,顺序不能变
     * @return
     * @throws Exception
     * @Description: 商品修改的提交
     * 对于POJO对象的绑定,要求表单中元素的name属性和POJO对象的属性名一致
     */
    @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit(ModelMap modelMap, Integer id,@ModelAttribute("itemsCustom") @Validated(value = {ValidGroup1.class}) ItemsCustom itemsCustom, BindingResult bindingResult, MultipartFile itemsPic) throws Exception {
        //获取校验错误信息
        if (bindingResult.hasErrors()) {
            //输出错误信息
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                System.out.println(objectError.getDefaultMessage());
            }
            //将错误信息传到页面
            itemsCustom.setPic(itemsService.findById(id).getPic());
            modelMap.addAttribute("allErrors", allErrors);
            return "editItems";
        }
//        System.out.println(itemsCustom);
//        上传图片
        if (itemsPic != null) {
            //存储图片的物理路径
            String path = "/Users/imac/javastudy/ssm/src/main/webapp/upload/";
            String originalFilename = itemsPic.getOriginalFilename();
            if (originalFilename != null && originalFilename.length() > 0) {
                String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
                File file = new File(path + newFileName);
                //将内存中的数据写入磁盘
                itemsPic.transferTo(file);
                //将新的文件名称写入itemsCustom
                itemsCustom.setPic(newFileName);
            } else {
                itemsCustom.setPic(itemsService.findById(id).getPic());
            }
        }
        itemsService.updateItems(id, itemsCustom);
        //重定向
        //return "redirect:queryItems";
        //forward转发和redirct区别
        //redirect的url地址栏变化，request不共享
        //forward的url地址栏不变,request共享
        return "forward:queryItems.action";
    }

    /**
     *
     * @return
     * @throws Exception
     * @Description: 批量删除商品
     *               数组的传递和普通参数相同,只要参数名一致就可以
     */
    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] itemsIds) throws Exception {
        for (int id : itemsIds) {
            itemsService.deleteById(id);
        }
        return "redirect:queryItems";
    }

    /**
     *
     * @param
     * @return
     * @throws Exception
     * @Description: 批量修改商品的页面,在页面中可以修改商品信息
     *
     */
    @RequestMapping("/editItemsBatch")
    public String editItemsBatch(ModelMap modelMap) throws Exception {
        List<ItemsCustom> itemsList = itemsService.findItemsList(null);
        modelMap.addAttribute("itemsList", itemsList);
        return "editItemsBatch";
    }

    /**
     *
     * @param itemsQueryVo
     * @return
     * @throws Exception
     * @Description: 使用list接收页面提交的批量数据,要通过包装pojo来接收
     *               通过itemsQueryVo批量接收提交的商品信息,将商品信息存储到itemsQueryVo中的itemsCustomList中
     *
     */
    @RequestMapping("/editItemsBatchSubmit")
    public String editItemsBatchSubmit(ItemsQueryVo itemsQueryVo) throws Exception {
        for (ItemsCustom itemsCustom : itemsQueryVo.getItemsCustomList()) {
            itemsService.updateItems(itemsCustom.getId(),itemsCustom);
        }
        return "redirect:queryItems";
    }
}
