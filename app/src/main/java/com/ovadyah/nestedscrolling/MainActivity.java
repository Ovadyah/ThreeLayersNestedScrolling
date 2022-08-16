package com.ovadyah.nestedscrolling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ovadyah.nestedscrolling.ui.NestedRecyclerViewActivity;
import com.ovadyah.nestedscrolling.ui.NestedViewPagerRecyclerViewActivity;
import com.ovadyah.nestedscrolling.ui.ThreeLayersNestedScrollActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({
            R.id.nestedRecyclerView,
            R.id.nestedViewPagerRecyclerView,
            R.id.threeLayersNestedScroll
            })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.nestedRecyclerView:
                NestedRecyclerViewActivity.launch(this);
                break;
            case R.id.nestedViewPagerRecyclerView:
                NestedViewPagerRecyclerViewActivity.launch(this);
                break;
            case R.id.threeLayersNestedScroll:
                //三层嵌套滚动解决
                ThreeLayersNestedScrollActivity.launch(this);
                break;
            default:
                break;
        }
    }
}