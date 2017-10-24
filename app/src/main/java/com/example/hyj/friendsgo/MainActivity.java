package com.example.hyj.friendsgo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.hyj.friendsgo.feature.RecommendFragment;
import com.example.hyj.friendsgo.setting.SettingFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.hyj.friendsgo.R.id.tablayout;

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new Fragment());
        fragments.add(new Fragment());
        fragments.add(new SettingFragment());

        viewPager = (ViewPager) findViewById(R.id.vp_main);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(new viewpagerAdapter(getSupportFragmentManager(), fragments));

        tabLayout = (TabLayout) findViewById(tablayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    public class viewpagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments;

        public viewpagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }

    private void copyFile(final String fileName) {

        final File file = new File(getExternalFilesDir(null).getPath() + "/" + fileName);
        if (!file.exists()) {
            //复制文件
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream inputStream = getAssets().open(fileName);
                        OutputStream outputStream = new FileOutputStream(file);
                        byte[] buffer = new byte[1444];
                        int readSize = 0;
                        while ((readSize = inputStream.read(buffer)) != 0) {
                            outputStream.write(buffer, 0, readSize);
                        }
                        inputStream.close();
                        outputStream.close();
                    } catch (Exception e) {
                    }

                }
            });
            thread.start();
        }
    }


}
