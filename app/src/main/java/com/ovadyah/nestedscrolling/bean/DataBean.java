package com.ovadyah.nestedscrolling.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class DataBean implements MultiItemEntity {

    public static final int NORMAL_TYPE = 1;

    public  String text;
    public  String url;

    public DataBean(String text, String url) {
        this.text = text;
        this.url = url;
    }

    @Override
    public int getItemType() {
        return NORMAL_TYPE;
    }
}
