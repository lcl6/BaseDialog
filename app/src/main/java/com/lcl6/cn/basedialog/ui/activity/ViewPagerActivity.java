package com.lcl6.cn.basedialog.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.ui.frament.TextFrament;
import com.lcl6.cn.basedialog.ui.frament.TextSecondFrament;
import com.lcl6.cn.component.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by liancl on 2017/9/6.
 */

public class ViewPagerActivity extends BaseActivity {

    @BindView(R.id.viewpage)
    ViewPager mViewpage;
    @BindView(R.id.tab)
    TabLayout mTab;
    private List<String> mTitle;

    PublishSubject<String> mPublishSubject;

    public static void start(Context context) {
        Intent starter = new Intent(context, ViewPagerActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_viewpager;
    }

    @Override
    protected void initView() {
        onLoadSuccessStatus();
        mTitle=new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            mTitle.add("第"+i+"页");
        }
        mViewpage.setAdapter(new TabAdapter(getSupportFragmentManager()));
        mTab.setupWithViewPager(mViewpage);
    }

    private class TabAdapter extends FragmentPagerAdapter {

        private TabAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TextFrament.Companion.newInstance(mPublishSubject);//
                case 1:
                    return TextSecondFrament.newInstance();//
                default:
                    return TextFrament.Companion.newInstance(mPublishSubject);
            }
        }

        @Override
        public int getCount() {
            return mTitle.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }
    }
    @Override
    protected void initData() {
        mPublishSubject= PublishSubject.create();
    }

}
