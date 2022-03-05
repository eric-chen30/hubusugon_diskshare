package com.hubusugon.sugonnetdisk.part3.service;

import com.hubusugon.sugonnetdisk.part3.bean.Store;

import java.util.List;

public interface StoreService {
    List<Store> getStorestatus(String openid);
}
