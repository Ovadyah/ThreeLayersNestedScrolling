package com.ovadyah.nestedscrolling.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ovadyah.nestedscrolling.R;
import com.ovadyah.nestedscrolling.adapter.NestedScrollRecyclerViewAdapter;
import com.ovadyah.nestedscrolling.bean.DataBean;
import com.ovadyah.nestedwidget.nestscrollview.NestedScrollingOuterLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NestedScrollFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private NestedScrollingOuterLayout mNestedScrollingOuterLayout;


    private int mFragmentIndex;

    public NestedScrollFragment() {
        // Required empty public constructor
    }

    public static NestedScrollFragment newInstance() {
        NestedScrollFragment fragment = new NestedScrollFragment();
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NestedScrollTestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NestedScrollFragment newInstance(String param1, String param2) {
        NestedScrollFragment fragment = new NestedScrollFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nested_scroll_view, container, false);
        unbinder = ButterKnife.bind(this, view);

        initView();

        return view;
    }

    private void initView() {

        List<DataBean> dataBeans = new ArrayList<>();
        for (int i = 0; i<=20; i ++) {
            dataBeans.add(new DataBean(String.format("嵌套的数据 ListItem%s",i + 1), "",DataBean.NORMAL_TYPE));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new NestedScrollRecyclerViewAdapter(getContext(), dataBeans));

    }

    /**
     * 这个方法仅仅工作在FragmentPagerAdapter的场景中。（普通的activity中的一个fragment 不会调用。）
     *
     * @param visibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean visibleToUser) {
        super.setUserVisibleHint(visibleToUser);

        if (visibleToUser && isCurrentDisplayedFragment()) {
            if (mNestedScrollingOuterLayout != null) {
                mNestedScrollingOuterLayout.setChildRecyclerView(recyclerView);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isCurrentDisplayedFragment()) {
            if (mNestedScrollingOuterLayout != null) {
                mNestedScrollingOuterLayout.setChildRecyclerView(recyclerView);
            }
        }
    }

    /**
     * 是当前展示的fragment
     * @return
     */
    private boolean isCurrentDisplayedFragment() {
        if (getView() == null || !(getView().getParent() instanceof View)) {
            return false;
        }
        View parent = (View) getView().getParent();
        if (parent instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) parent;
            int currentItem = viewPager.getCurrentItem();
            return currentItem == mFragmentIndex;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setNestedParentLayout(NestedScrollingOuterLayout nestedParentLayout) {
        mNestedScrollingOuterLayout = nestedParentLayout;
    }

    public void setIndex(int i) {
        mFragmentIndex = i;
    }
}
