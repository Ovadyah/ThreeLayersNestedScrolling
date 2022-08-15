package com.ovadyah.nestedscrolling.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ovadyah.nestedscrolling.R;
import com.ovadyah.nestedscrolling.bean.DataBean;
import com.ovadyah.nestedscrolling.bean.ViewPagerBean;
import com.ovadyah.nestedscrolling.ui.fragment.ViewPager2ItemFragment;
import com.ovadyah.nestedwidget.nestscrollview.NestedScrollingOuterLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ovadyah
 */
public class ThreeNestedScrollItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private final Fragment mFragment;

    private String[] titles = {"VP2嵌套Tab1", "VP2嵌套Tab2", "VP2嵌套Tab3"};

    private NestedScrollingOuterLayout mNestedScrollingOuterLayout;
    private int mSelectedPosition;


    public ThreeNestedScrollItemAdapter(Fragment fragment) {
        mFragment = fragment;
        addItemType(DataBean.NORMAL_TYPE, R.layout.item_nested_scroll_view);
        addItemType(ViewPagerBean.VIEW_PAGE_TYPE, R.layout.item_view_page2);
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
        /*
        //第三层嵌套不能设置LastItem，只能在第二层嵌套中设置
        if (mNestedScrollingOuterLayout != null) {
            mNestedScrollingOuterLayout.setLastItem(baseViewHolder.itemView);
        }*/
        viewPagerBean.isLoadData = true;
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ViewPager2ItemFragment fragment = ViewPager2ItemFragment.newInstance(i);
            fragment.setIndex(i);
            fragment.setNestedParentLayout(mNestedScrollingOuterLayout);
            fragments.add(fragment);
        }
        final TabLayout tab = (TabLayout) baseViewHolder.getView(R.id.tab_layout);
        final ViewPager2Adapter adapter = new ViewPager2Adapter(mFragment);
        final ViewPager2 viewPager2 = (ViewPager2) baseViewHolder.getView(R.id.view_pager2);
        viewPager2.setAdapter(adapter);
        adapter.updateAdapter(fragments);
        viewPager2.setCurrentItem(mSelectedPosition);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mSelectedPosition = position;
                updateCurrentFragment(position, adapter);
            }
        });
        TabLayoutMediator tabMediator = new TabLayoutMediator(tab, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                tab.setIcon(titleItem[position]);
                tab.setText(titles[position]);
                viewPager2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateCurrentFragment(position, adapter);
                    }
                }, 120);
            }
        });
        tabMediator.attach();
    }

    private void updateCurrentFragment(int position, ViewPager2Adapter adapter) {
        Fragment fragment = adapter != null && adapter.getItemCount() > 0 ? adapter.getItemFragment(position) : null;
        RecyclerView recyclerView = fragment != null && fragment instanceof ViewPager2ItemFragment ? ((ViewPager2ItemFragment)fragment).getRecyclerView() : null;
        if (mNestedScrollingOuterLayout != null && recyclerView != null) {
            mNestedScrollingOuterLayout.setChildTouchView(recyclerView);
        }
    }

    private void convertNormal(BaseViewHolder baseViewHolder, DataBean dataBean) {
        baseViewHolder.setText(R.id.textView, dataBean.text);
//        ImageLoader.with(mActivity).loadBitmapAsync(dataBean.url, baseViewHolder.getView(R.id.imageView));
    }

    public void setNestedParentLayout(NestedScrollingOuterLayout nestedScrollingOuterLayout) {
        mNestedScrollingOuterLayout = nestedScrollingOuterLayout;
    }
}
