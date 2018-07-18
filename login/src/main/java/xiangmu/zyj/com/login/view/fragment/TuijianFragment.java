package xiangmu.zyj.com.login.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xiangmu.zyj.com.login.R;

public class TuijianFragment extends Fragment {
    private TabLayout tab;
    private ViewPager pager;
    private List<String> aa;
    private ArrayList<Fragment> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.tuijian_layout, container, false);
        tab = inflate.findViewById(R.id.tableyout);
        pager = inflate.findViewById(R.id.pager);
        //把tabloout 和 pager组合起来
        tab.setupWithViewPager(pager);
        //写数据源
        aa = new ArrayList<>();
        aa.add("热门");
        aa.add("关注");
        list = new ArrayList<>();
        list.add(new Tuijian_guanzhu());
        list.add(new Tuijian_remen());
        pager.setAdapter(new MypagerAdaper(getFragmentManager()));
        return inflate;
    }
    class MypagerAdaper extends FragmentPagerAdapter {
        public MypagerAdaper(FragmentManager fm) {
            super(fm);
        }
        @Override
        public int getCount() {
            return 2;
        }
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }
        //横向菜单的方法
        @Override
        public CharSequence getPageTitle(int position) {
            return aa.get(position);
        }
    }
}