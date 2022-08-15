package com.ovadyah.nestedscrolling.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ovadyah.nestedscrolling.R;
import com.ovadyah.nestedscrolling.adapter.ThreeLayersNestedScrollAdapter;
import com.ovadyah.nestedscrolling.bean.DataBean;
import com.ovadyah.nestedscrolling.bean.ViewPagerBean;
import com.ovadyah.nestedwidget.nestscrollview.NestedScrollingOuterLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 嵌套recyclerView
 * @author Ovadyah
 */
public class ThreeLayersNestedScrollActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView_parent)
    RecyclerView recyclerViewParent;

    NestedScrollingOuterLayout nestedScrollingOuterLayout;

    public static void launch(FragmentActivity activity) {
        Intent intent = new Intent(activity, ThreeLayersNestedScrollActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_nested_scroll);
        nestedScrollingOuterLayout = findViewById(R.id.nested_scrolling_parent_layout);

        ButterKnife.bind(this);

        List<MultiItemEntity> list = new ArrayList<>();
        for (int i = 0; i<= 15; i ++) {
            list.add(new DataBean(String.format("最外层ListItem%s",i + 1), "", DataBean.NORMAL_TYPE));
        }
        //最后一条添加嵌套类型
        list.add(new ViewPagerBean());

        recyclerViewParent.setLayoutManager(new LinearLayoutManager(this));
        ThreeLayersNestedScrollAdapter adapter = new ThreeLayersNestedScrollAdapter(this);
        if (nestedScrollingOuterLayout != null) {
            adapter.setNestedParentLayout(nestedScrollingOuterLayout);
        }
        recyclerViewParent.setAdapter(adapter);
        recyclerViewParent.setItemAnimator(null);
        recyclerViewParent.setHasFixedSize(true);
        recyclerViewParent.setDrawingCacheEnabled(true);
        recyclerViewParent.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerViewParent.setItemViewCacheSize(30);
        adapter.setNewInstance(list);
    }
}
