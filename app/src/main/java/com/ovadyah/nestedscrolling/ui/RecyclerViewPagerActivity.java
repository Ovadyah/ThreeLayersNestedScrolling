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
import com.ovadyah.nestedscrolling.adapter.RecyclerNestAdapter;
import com.ovadyah.nestedscrolling.bean.DataBean;
import com.ovadyah.nestedscrolling.bean.ViewPagerBean;
import com.ovadyah.nestedwidget.nestscrollview.NestedScrollingOuterLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 最外层recyclerView 在最后一个Item里面嵌套（tab + viewPager +recyclerView）
 *
 * @author Ovadyah
 */
public class RecyclerViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView_parent)
    RecyclerView recyclerViewParent;

    NestedScrollingOuterLayout nestedScrollingOuterLayout;

    public static void launch(FragmentActivity activity) {
        Intent intent = new Intent(activity, RecyclerViewPagerActivity.class);
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
            list.add(new DataBean(String.format("外层ListItem%s",i + 1), ""));
        }
        //最后一条添加嵌套类型
        list.add(new ViewPagerBean());

        recyclerViewParent.setLayoutManager(new LinearLayoutManager(this));
        RecyclerNestAdapter adapter = new RecyclerNestAdapter(this);
        if (nestedScrollingOuterLayout != null) {
            adapter.setNestedParentLayout(nestedScrollingOuterLayout);
        }
        recyclerViewParent.setAdapter(adapter);

        adapter.setNewInstance(list);
    }
}
