package com.hubusugon.sugonnetdisk.part3.service.impl;

import com.hubusugon.sugonnetdisk.part3.bean.Store;
import com.hubusugon.sugonnetdisk.part3.mapper.StoreMapper;
import com.hubusugon.sugonnetdisk.part3.service.StoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StoreImpl implements StoreService {
    @Resource
    StoreMapper storeMapper;


    @Override
    public List<Store> getStorestatus(String openid) {
        return storeMapper.selectStore(openid);
    }
}
