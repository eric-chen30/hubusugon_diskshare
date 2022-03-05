package com.hubusugon.sugonnetdisk.part3.bean;

import java.util.Date;

import lombok.Data;

@Data
public class SharedFiles {
    private String user_name;
    private int my_file_id;
    private String my_file_name;
    private int file_store_id;
    private String my_file_path;
    private int download_time;
    private Date upload_time;
    private int parent_folder_id;
    private long size;
    private String type;
    private String postfix;


}
