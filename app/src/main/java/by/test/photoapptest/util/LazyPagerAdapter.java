package by.test.photoapptest.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

/**
 * Created by SlowAR on 04.05.2017.
 */

public abstract class LazyPagerAdapter extends FragmentStatePagerAdapter
        implements ViewPager.OnPageChangeListener {

    private final SparseArrayCompat<Fragment> mFragments;

    public LazyPagerAdapter(@NonNull final ViewPager viewPager, FragmentManager fragmentManager) {
        super(fragmentManager);
        mFragments = new SparseArrayCompat<>();
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}
