package com.rishabh.roposo.ui.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.rishabh.roposo.R;
import com.rishabh.roposo.ui.fragments.StoriesFragment;
import com.rishabh.roposo.ui.fragments.TempFragmentA;
import com.rishabh.roposo.ui.fragments.TempFragmentB;
import com.rishabh.roposo.ui.fragments.TempFragmentC;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rishabh bhatia on 21-03-2016.
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewpager) ViewPager mPager;
    @Bind(R.id.tabs) TabLayout tabs;
    private StoriesFragment storiesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupViewPager();

        tabs.setupWithViewPager(mPager);

        tabs.getTabAt(0).setIcon(android.R.drawable.ic_menu_directions);
        tabs.getTabAt(1).setIcon(android.R.drawable.ic_lock_lock);
        tabs.getTabAt(2).setIcon(android.R.drawable.ic_btn_speak_now);
        tabs.getTabAt(3).setIcon(android.R.drawable.ic_menu_edit);
    }

    private void setupViewPager() {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        storiesFragment = StoriesFragment.newInstance();
        adapter.addFragment(storiesFragment,null);
        adapter.addFragment(TempFragmentA.newInstance(),null);
        adapter.addFragment(TempFragmentB.newInstance(),null);
        adapter.addFragment(TempFragmentC.newInstance(),null);

        mPager.setAdapter(adapter);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }

    }
}
