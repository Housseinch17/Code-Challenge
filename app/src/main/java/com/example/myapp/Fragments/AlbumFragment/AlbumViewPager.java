package com.example.myapp.Fragments.AlbumFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapp.Fragments.AlbumFragment.Fragments.ImagesAndTextFragment;
import com.example.myapp.Fragments.AlbumFragment.Fragments.ImagesOnlyFragment;


public class AlbumViewPager extends FragmentStateAdapter {

    public AlbumViewPager(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;

        switch (position) {
            case 0:
                fragment= ImagesAndTextFragment.newInstance();
                break;
            case 1:
                fragment= ImagesOnlyFragment.newInstance();
                break;
            default:
                return null;

        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
