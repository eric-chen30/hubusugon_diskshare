package com.hubusugon.sugonnetdisk.part3.service;

import com.hubusugon.sugonnetdisk.part3.bean.SharedFiles;


import java.util.List;

public interface SharedFilesService {
    List<SharedFiles> getAllFiles();

    List<SharedFiles> findSharedFiles(String file_name);

    String addNumber(String openid, int number);

    String reduceNumber(String openid, int number);
}
