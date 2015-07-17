package com.love.hades.hadescalendardemo.adapter;

/**
 * Author：Hades on 2015/7/16 16:48
 * E_mail：li710611@163.com
 */

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.love.hades.hadescalendardemo.listener.OnCellItemClick;
import com.love.hades.hadescalendardemo.widget.CalendarCard;

import java.util.Calendar;


public class CardPagerAdapter extends PagerAdapter {

    private static String TAG = "CardPagerAdapter";

    private Context mContext;
    private OnCellItemClick defaultOnCellItemClick;

    public CardPagerAdapter(Context ctx) {
        mContext = ctx;
    }

    @Override
    public Object instantiateItem(View collection, final int position) {
        Calendar cal = Calendar.getInstance();
        Log.d(TAG, "instantiateItem position=" + position);
        cal.add(Calendar.MONTH, position);
        Log.d(TAG, "instantiateItem month=" + cal.get(Calendar.MONTH));
        CalendarCard card = new CalendarCard(mContext);
        card.setDateDisplay(cal);
        card.notifyChanges();
        if (card.getOnCellItemClick() == null)
            card.setOnCellItemClick(defaultOnCellItemClick);

        ((ViewPager) collection).addView(card, 0);

        return card;
    }

    @Override
    public void destroyItem(View collection, int position, Object view) {
        ((ViewPager) collection).removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==((View)object);
    }

    @Override
    public void finishUpdate(View arg0) {}
    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {}
    @Override
    public Parcelable saveState() { return null; }
    @Override
    public void startUpdate(View arg0) {}

    @Override
    public int getCount() {
        // TODO almoast ifinite ;-)
        return Integer.MAX_VALUE;
    }

    public OnCellItemClick getDefaultOnCellItemClick() {
        return defaultOnCellItemClick;
    }

    public void setDefaultOnCellItemClick(OnCellItemClick defaultOnCellItemClick) {
        this.defaultOnCellItemClick = defaultOnCellItemClick;
    }

    /**
     * 当前的显示的界面
     */
    private CalendarCard curCalendarCard;

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        curCalendarCard = (CalendarCard)object;
    }

    public CalendarCard getCurrentView(){
        return curCalendarCard;
    }

}