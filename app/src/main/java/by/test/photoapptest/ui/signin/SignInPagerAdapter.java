package by.test.photoapptest.ui.signin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import by.test.photoapptest.R;
import by.test.photoapptest.util.LazyPagerAdapter;

/**
 * Created by SlowAR on 04.05.2017.
 */

public class SignInPagerAdapter extends LazyPagerAdapter {

    private final int PAGES_COUNT = 2;

    private static final int SIGNIN_PAGE_FRAGMENT = 0;
    private static final int SIGNUP_PAGE_FRAGMENT = 1;

    private String[] mTabTitles;
    private Context mContext;

    public SignInPagerAdapter(@NonNull Context context, ViewPager viewPager,
                              FragmentManager fragmentManager) {
        super(viewPager, fragmentManager);
        mContext = context;
        mTabTitles = new String[PAGES_COUNT];
        mTabTitles[0] = context.getString(R.string.login_text);
        mTabTitles[1] = context.getString(R.string.register_text);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case SIGNIN_PAGE_FRAGMENT:
                return SignInPageFragment.newInstance((SignInListener)mContext);
            case SIGNUP_PAGE_FRAGMENT:
                return SignUpPageFragment.newInstance((SignInListener)mContext);
            default:
                throw new UnsupportedOperationException("No such fragment type ");
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }
}
