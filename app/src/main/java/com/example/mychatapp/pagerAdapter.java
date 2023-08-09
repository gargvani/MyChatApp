package com.example.mychatapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class pagerAdapter extends FragmentPagerAdapter {

    int tabcount;


    public pagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        tabcount = behavior;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new chatfragment();

            case 1:
                return new Statusfragment();
            case 2:
                return new callfragment();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {

        return tabcount;
    }
}
