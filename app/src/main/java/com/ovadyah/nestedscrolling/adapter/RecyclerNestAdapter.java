package com.ovadyah.nestedscrolling.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.tabs.TabLayout;

import com.ovadyah.nestedscrolling.R;
import com.ovadyah.nestedscrolling.bean.DataBean;
import com.ovadyah.nestedscrolling.bean.ViewPagerBean;
import com.ovadyah.nestedscrolling.ui.fragment.NestedScrollFragment;
import com.ovadyah.nestedwidget.nestscrollview.NestedScrollingOuterLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hufeiyang
 */
public class RecyclerNestAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private final FragmentActivity mActivity;

    private String[] titles = {"Tab1", "Tab2", "Tab3"};

    private NestedScrollingOuterLayout mNestedScrollingOuterLayout;
    private int mSelectedPosition;


    public RecyclerNestAdapter(FragmentActivity activity) {
        mActivity = activity;
        addItemType(DataBean.NORMAL_TYPE, R.layout.item_nested_scroll_view);
        addItemType(ViewPagerBean.VIEW_PAGE_TYPE, R.layout.item_view_page);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MultiItemEntity multiItemEntity) {
        int itemViewType = baseViewHolder.getItemViewType();
        switch (itemViewType) {
            case DataBean.NORMAL_TYPE:
                convertNormal(baseViewHolder, (DataBean) multiItemEntity);
                break;
            case ViewPagerBean.VIEW_PAGE_TYPE:
                convertViewPager(baseViewHolder, (ViewPagerBean) multiItemEntity);
                break;
            default:
                break;
        }

    }

    private void convertViewPager(BaseViewHolder baseViewHolder, ViewPagerBean viewPagerBean) {
        if (viewPagerBean.isLoadData) {
            return;
        }
        viewPagerBean.isLoadData = true;
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            NestedScrollFragment fragment = new NestedScrollFragment();
            fragment.setIndex(i);
            fragment.setNestedParentLayout(mNestedScrollingOuterLayout);
            fragments.add(fragment);
        }


        ViewPagerAdapter adapter = new ViewPagerAdapter(mActivity.getSupportFragmentManager(), titles, fragments);
        ViewPager viewPager = (ViewPager) baseViewHolder.getView(R.id.view_pager);
        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(mSelectedPosition);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                mSelectedPosition = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        TabLayout tab = (TabLayout) baseViewHolder.getView(R.id.tab_layout);
        tab.setupWithViewPager(viewPager);
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                if (mNestedScrollingOuterLayout != null) {
                    mNestedScrollingOuterLayout.setLastItem(baseViewHolder.itemView);

                }
            }
        });


    }

    private void convertNormal(BaseViewHolder baseViewHolder, DataBean dataBean) {
        baseViewHolder.setText(R.id.textView, dataBean.text);
//        ImageLoader.with(mActivity).loadBitmapAsync(dataBean.url, baseViewHolder.getView(R.id.imageView));
    }

    public void setNestedParentLayout(NestedScrollingOuterLayout nestedScrollingOuterLayout) {
        mNestedScrollingOuterLayout = nestedScrollingOuterLayout;
    }
}
