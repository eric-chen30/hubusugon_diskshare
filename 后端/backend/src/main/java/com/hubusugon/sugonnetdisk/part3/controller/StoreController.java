package com.hubusugon.sugonnetdisk.part3.controller;

import com.hubusugon.sugonnetdisk.part3.bean.Store;
import com.hubusugon.sugonnetdisk.part3.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.hubusugon.sugonnetdisk.part3.config.Result;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class StoreController {
    @Resource
    StoreService storeService;


    @RequestMapping(value = "/warehouse", method = RequestMethod.GET)
    @ResponseBody
    public Result GetStorestatus(@RequestParam("openid") String openid) {
        List<Store> StoreList = storeService.getStorestatus(openid);
        return new  Result(0,"操作成功",StoreList);
    }
}
