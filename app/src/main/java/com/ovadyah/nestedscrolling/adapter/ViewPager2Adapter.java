package com.ovadyah.nestedscrolling.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2Adapter extends FragmentStateAdapter {

    private List<Fragment> mFragmentList = new ArrayList();

    public ViewPager2Adapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList.get(position);
    }

    public void updateAdapter(List<Fragment> fragmentList) {
        if (fragmentList != null && fragmentList.size() > 0) {
            cleanAdapter();
            mFragmentList.addAll(fragmentList);
            notifyDataSetChanged();
        }
    }

    public Fragment getItemFragment(int position){
        return mFragmentList != null && mFragmentList.size() > 0 ? mFragmentList.get(position) : null;
    }

    public void cleanAdapter(){
        if (mFragmentList != null && mFragmentList.size() > 0) {
            mFragmentList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mFragmentList.size();
    }
}
