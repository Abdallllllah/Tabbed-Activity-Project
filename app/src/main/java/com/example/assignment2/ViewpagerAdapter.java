package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.assignment2.fragments.FeesFragment;
import com.example.assignment2.fragments.RegisterFragment;
import com.example.assignment2.fragments.SummaryFragment;


public class ViewpagerAdapter  extends FragmentStateAdapter {
    public ViewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new RegisterFragment();
            case 1:
                return new FeesFragment();
            case 2:
                return new SummaryFragment();
            default:
                return new RegisterFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}