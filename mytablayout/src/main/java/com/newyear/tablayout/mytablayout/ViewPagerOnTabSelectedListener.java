package com.newyear.tablayout.mytablayout;

import android.support.v4.view.ViewPager;

/**
 * A {@link TabLayout.OnTabSelectedListener} class which contains the necessary calls back
 * to the provided {@link ViewPager} so that the tab position is kept in sync.
 */
public class ViewPagerOnTabSelectedListener  implements TabLayout.OnTabSelectedListener {
    private final ViewPager mViewPager;

    public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
        mViewPager = viewPager;
    }

    @Override
    public void onTabSelected(Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab) {
        // No-op
    }

    @Override
    public void onTabReselected(Tab tab) {
        // No-op
    }
}
