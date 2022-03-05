package com.hubusugon.sugonnetdisk.part3.mapper;

import com.hubusugon.sugonnetdisk.part3.bean.SharedFiles;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SharedFilesMapper {
    List<SharedFiles> selectAllFiles();

    List<SharedFiles> selectFilesByFile_name(@Param("file_name") String file_name);

    void addUserNumber(@Param(value = "openid") String openid, @Param(value = "number") int number);

    void reduceUserNumber(@Param(value = "openid") String openid, @Param(value = "number") int number);
}
