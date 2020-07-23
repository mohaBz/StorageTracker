package com.mddev.storagetracker.mainview;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.mddev.storagetracker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    MainFragment.ViewPagerAdapter viewPagerAdapter;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.pager);
        tabLayout=view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        viewPagerAdapter=new MainFragment.ViewPagerAdapter(getParentFragmentManager(),0);
        viewPagerAdapter.addFragment(new TruckFragment(),getString(R.string.truck));
        viewPagerAdapter.addFragment(new StockFragment(),getString(R.string.stock));
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_local_shipping_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_store_black_24dp);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments;
        List<String> titles;


        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
            fragments=new ArrayList<>();
            titles=new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position) ;
        }


        public void addFragment(Fragment fragment,String title){
            fragments.add(fragment);
            titles.add(title);
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
