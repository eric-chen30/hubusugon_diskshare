package com.hubusugon.sugonnetdisk.part3.mapper;

import com.hubusugon.sugonnetdisk.part3.bean.Store;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper {
    List<Store> selectStore(@Param(value = "openid") String openid);
}
