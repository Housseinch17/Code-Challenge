package com.example.myapp.Fragments.AlbumFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AlbumFragment extends Fragment {
View view;
TabLayout tabLayoutAlbum;
ViewPager2 viewPagerAlbum;

public AlbumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_album2, container, false);
        tabLayoutAlbum=view.findViewById(R.id.tabLayoutAlbum);
        viewPagerAlbum=view.findViewById(R.id.viewPagerAlbum);

        AlbumViewPager albumViewPager=new AlbumViewPager(getChildFragmentManager(), getLifecycle());
        viewPagerAlbum.setAdapter(albumViewPager);

        
        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayoutAlbum, viewPagerAlbum, true, true
                , new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0:
                        tab.setText("Images and Text");
                        break;
                    case 1:
                        tab.setText("Images Only");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();


        return view;
    }

}