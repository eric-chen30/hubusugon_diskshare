package com.hubusugon.sugonnetdisk.part3.service.impl;

import com.hubusugon.sugonnetdisk.part3.bean.SharedFiles;
import com.hubusugon.sugonnetdisk.part3.mapper.SharedFilesMapper;
import com.hubusugon.sugonnetdisk.part3.service.SharedFilesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SharedFilesImpl implements SharedFilesService {
    @Resource
    SharedFilesMapper sharedFilesMapper;


    public List<SharedFiles> getAllFiles() {
        return sharedFilesMapper.selectAllFiles();
    }


    public List<SharedFiles> findSharedFiles(String file_name) {
        return sharedFilesMapper.selectFilesByFile_name(file_name);
    }


    public String addNumber(String openid, int number) {
        try {
            sharedFilesMapper.addUserNumber(openid, number);
            return "增加成功";
        } catch (Exception e) {
            return ("增加失败:\n" + e);
        }
    }


    public String reduceNumber(String openid, int number) {
        try {
            sharedFilesMapper.reduceUserNumber(openid, number);
            return "修改成功";
        } catch (Exception e) {
            return ("修改失败:\n" + e);
        }
    }
}


