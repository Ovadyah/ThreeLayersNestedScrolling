package com.ovadyah.nestedscrolling.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ViewPagerBean implements MultiItemEntity {

    public static final int VIEW_PAGE_TYPE = 2;

    @Override
    public int getItemType() {
        return VIEW_PAGE_TYPE;
    }

    public boolean isLoadData = false;
}
