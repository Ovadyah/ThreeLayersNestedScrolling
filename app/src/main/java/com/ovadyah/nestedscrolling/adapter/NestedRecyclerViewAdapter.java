package com.ovadyah.nestedscrolling.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
public class NestedRecyclerViewAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private final FragmentActivity mActivity;

    private NestedScrollingOuterLayout mNestedScrollingOuterLayout;
    private int mSelectedPosition;


    public NestedRecyclerViewAdapter(FragmentActivity activity) {
        mActivity = activity;
        addItemType(DataBean.NORMAL_TYPE, R.layout.item_nested_scroll_view);
        addItemType(DataBean.NEST_RECYCLER_TYPE, R.layout.fragment_nested_scroll_view);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MultiItemEntity multiItemEntity) {
        int itemViewType = baseViewHolder.getItemViewType();
        switch (itemViewType) {
            case DataBean.NORMAL_TYPE:
                convertNormal(baseViewHolder, (DataBean) multiItemEntity);
                break;
            case DataBean.NEST_RECYCLER_TYPE:
                convertNestRecyclerView(baseViewHolder, (DataBean) multiItemEntity);
                break;
            default:
                break;
        }

    }

    private void convertNestRecyclerView(BaseViewHolder baseViewHolder, DataBean viewPagerBean) {
        //只能在最后一条Item使用
        if (viewPagerBean.isLoadData) {
            return;
        }
        viewPagerBean.isLoadData = true;

        List<DataBean> dataBeans = new ArrayList<>();
        for (int i = 0; i<=20; i ++) {
            dataBeans.add(new DataBean(String.format("嵌套的数据 ListItem%s",i + 1), "",DataBean.NORMAL_TYPE));
        }
        RecyclerView recyclerView = (RecyclerView) baseViewHolder.getView(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new NestedScrollRecyclerViewAdapter(getContext(), dataBeans));
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                if (mNestedScrollingOuterLayout != null) {
                    mNestedScrollingOuterLayout.setLastItem(baseViewHolder.itemView);
                }
            }
        });
        if (mNestedScrollingOuterLayout != null) {
            mNestedScrollingOuterLayout.setChildRecyclerView(recyclerView);
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
