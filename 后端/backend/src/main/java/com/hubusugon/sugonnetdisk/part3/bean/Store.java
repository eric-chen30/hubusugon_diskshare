package com.hubusugon.sugonnetdisk.part3.bean;

import lombok.Data;

@Data
public class Store {
    private String open_id;
    private int folder;
    private int file;
    private long use_capacity;
    private long current_num;
    private long document_num;
    private int photo_num;
    private int movie_num;
    private int void_num;
    private int other_num;
}
