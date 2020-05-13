package com.example.chesminnguyen.app_edm_music.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chesminnguyen.app_edm_music.Fragment.AlbumFragment;
import com.example.chesminnguyen.app_edm_music.Fragment.BaiHat_Fragment;
import com.example.chesminnguyen.app_edm_music.Fragment.ChuDe_Fragment;
import com.example.chesminnguyen.app_edm_music.Fragment.PlaylistFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private  int tabNumber;
    public PagerAdapter(@NonNull FragmentManager fm, int behavior,int tabNumber) {
        super(fm, behavior);
        this.tabNumber=tabNumber;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
            {
                return  new BaiHat_Fragment();

            }
            case 1:
            {
                return  new AlbumFragment();

            }
            case 2:
            {
                return  new PlaylistFragment();
            }
            case 3:
            {


                return  new ChuDe_Fragment();

            }
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabNumber;
    }
}
