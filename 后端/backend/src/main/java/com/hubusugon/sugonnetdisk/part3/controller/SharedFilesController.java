package com.hubusugon.sugonnetdisk.part3.controller;

import com.hubusugon.sugonnetdisk.part3.bean.SharedFiles;
import com.hubusugon.sugonnetdisk.part3.config.Result;
import com.hubusugon.sugonnetdisk.part3.service.SharedFilesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class SharedFilesController {
    @Resource
    SharedFilesService sharedFilesService;


    @RequestMapping(value = "/Shared", method = RequestMethod.GET)
    @ResponseBody
    public Result GetSharedFiles() {
        List<SharedFiles> sharedFilesList = sharedFilesService.getAllFiles();
        return new  Result(0,"操作成功",sharedFilesList);
    }


    @RequestMapping(value = "/SharedFind", method = RequestMethod.GET)
    @ResponseBody
    public Result FindSharedFiles(@RequestParam("file_name") String file_name) {
        List<SharedFiles> FindFilesList = sharedFilesService.findSharedFiles(file_name);
        return new  Result(0,"操作成功",FindFilesList);
    }


    @RequestMapping(value = "/increaseMaxFile", method = RequestMethod.GET)
    @ResponseBody
    public Result AddNumber(@RequestParam(value = "openid") String openid, @RequestParam(value = "number") int number) {
        String result = sharedFilesService.addNumber(openid, number);
        return new  Result(0,"操作成功");
    }


    @RequestMapping(value = "/reduceMaxFile", method = RequestMethod.GET)
    @ResponseBody
    public Result ReduceNumber(@RequestParam(value = "openid") String openid, @RequestParam(value = "number") int number) {
        String result = sharedFilesService.reduceNumber(openid, number);
        return new  Result(0,"操作成功");
    }

}
