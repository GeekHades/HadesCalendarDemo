package com.love.hades.hadescalendardemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.love.hades.hadescalendardemo.Utils.LogUtils;
import com.love.hades.hadescalendardemo.fragment.CardPagerViewFragment;
import com.love.hades.hadescalendardemo.widget.CalendarCard;
import com.love.hades.hadescalendardemo.widget.CalendarCardPager;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class AppointmentActivity extends FragmentActivity {

    private static String TAG = "AppointmentActivity";

    @InjectView(R.id.calendarCard1)
    CalendarCardPager mCalendarCard;

    public static int chosenYear;
    public static int chosenMoth;
    public static int chosenDay;

    public static int mTargetYear;
    public static int mTargetMonth;

    private int index;
    private String uids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mCalendarCard.setOffscreenPageLimit(0);
        initListener();
    }

    /**
     *
     */
    private void initListener() {

//        mCalendarCard.setOnCellItemClick(new OnCellItemClick() {
//            @Override
//            public void onCellClick(View v, CardGridItem item) {
//                initChosenViewInfo(item);
//            }
//
//            private void initChosenViewInfo(CardGridItem item) {
//                AppointmentActivity.chosenYear = item.getDate().get(Calendar.YEAR);
//                AppointmentActivity.chosenDay = item.getDate().get(Calendar.DAY_OF_MONTH);
//                int mouthNum = item.getDate().get(Calendar.MONTH);
//                if (AppointmentActivity.chosenDay == 1) {
//                    //TODO 如果选中的是1的话，月份不需要加一，因为在日历里面，有一个加1的算法，导致了这里每个月的一号，月份比当前月份大一！
//                    AppointmentActivity.chosenMoth = mouthNum;
//                } else {
//                    AppointmentActivity.chosenMoth = mouthNum + 1;
//                }
//            }
//        });



        //下面解决预加载的问题
        mCalendarCard.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                LogUtils.d(TAG,"onPageScrolled  position="+position+"  positionOffset="+positionOffset+"  positionOffsetPixels="+positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.d(TAG, "Adapter  position="+position);
                int cutIndex = mCalendarCard.getCurrentItem();
                LogUtils.d(TAG, "Adapter  currentItem position=" + cutIndex);
                Bundle mBundle = mCalendarCard.getCardPagerAdapter().getItem(cutIndex).getArguments();
                int num =  mBundle.getInt("num");
                LogUtils.d(TAG, "Adapter  currentItem getArguments num=" + num);

                //TODO 这里的处理方式预加载是有问题的！
//                CalendarCard currentView = mCalendarCard.getCardPagerAdapter().getCurrentView();
                CardPagerViewFragment cardPagerViewFragment = (CardPagerViewFragment)mCalendarCard.getCardPagerAdapter().getItem(cutIndex);

                if(null != cardPagerViewFragment){
                    CalendarCard currentView = cardPagerViewFragment.getCurrentView();
                    if(null != currentView){
                        Calendar cal = currentView.getDateDisplay();
                        mTargetYear = cal.get(Calendar.YEAR);
                        caculateYearMonth(position, cal);
                        Log.d(TAG, "mTargetYear=" + mTargetYear);
                        Log.d(TAG, "mTargetMonth=" + mTargetMonth);
                        Log.d(TAG, "mTarge   chouseYear=" + chosenYear +"   chosenMonth="+chosenMoth+"  chouseDay="+chosenDay);
                        currentView.updateView();
                    }
                }

            }

            private void caculateYearMonth(int position, Calendar cal) {
                if (index < position) {
                    mTargetMonth = cal.get(Calendar.MONTH) + 1;
                    mTargetMonth++;
                    if (mTargetMonth == 13) {
                        mTargetYear++;
                        mTargetMonth = 1;
                    }
                    index = position;
                } else {
                    mTargetMonth = cal.get(Calendar.MONTH) + 1;
                    mTargetMonth--;
                    if (mTargetMonth == 0) {
                        mTargetYear--;
                        mTargetMonth = 12;
                    }
                    index = position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                LogUtils.d(TAG,"onPageScrollStateChanged  position="+state);
            }
        });

    }

    /**
     * @hades 清除选中天数的数据
     */
    public void clearChosenDayData() {
        AppointmentActivity.chosenDay = 0;
        AppointmentActivity.chosenMoth = 0;
        AppointmentActivity.chosenYear = 0;
    }


    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
