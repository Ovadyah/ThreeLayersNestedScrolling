package com.ovadyah.nestedscrolling.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class DataBean implements MultiItemEntity {

    public static final int NORMAL_TYPE = 1;
    public static final int NEST_RECYCLER_TYPE = 2;

    public  String text;
    public  String url;
    public  int type;

    public boolean isLoadData = false;

    public DataBean(String text, String url, int type) {
        this.text = text;
        this.url = url;
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type == NEST_RECYCLER_TYPE ? NEST_RECYCLER_TYPE : NORMAL_TYPE;
    }
}
