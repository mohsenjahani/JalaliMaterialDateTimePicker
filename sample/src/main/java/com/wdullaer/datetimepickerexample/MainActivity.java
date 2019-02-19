package com.wdullaer.datetimepickerexample;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v13.app.FragmentPagerAdapter;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.JalaliCalendar;

import java.util.Calendar;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity
{
    ViewPager viewPager;
    PickerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new PickerAdapter(getFragmentManager());
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        for(int i=0;i<adapter.getCount();i++) //noinspection ConstantConditions
            tabLayout.getTabAt(i).setText(adapter.getTitle(i));

//        ViewPump.init(ViewPump.builder()
//                .addInterceptor(new CalligraphyInterceptor(
//                        new CalligraphyConfig.Builder()
//                                .setDefaultFontPath("Vazir-FD.ttf")
//                                .setFontAttrId(R.attr.fontPath)
//                                .build()))
//                .build());
    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
//    }

    private class PickerAdapter extends FragmentPagerAdapter {
        private static final int NUM_PAGES = 2;
        Fragment timePickerFragment;
        Fragment datePickerFragment;

        PickerAdapter(FragmentManager fm) {
            super(fm);
            timePickerFragment = new TimePickerFragment();
            datePickerFragment = new DatePickerFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return datePickerFragment;
                case 1:
                default:
                    return timePickerFragment;
            }
        }

        int getTitle(int position) {
            switch(position) {
                case 0:
                    return R.string.tab_title_date;
                case 1:
                default:
                    return R.string.tab_title_time;
            }
        }
    }
}
