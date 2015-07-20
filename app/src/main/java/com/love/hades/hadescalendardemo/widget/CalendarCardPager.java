package com.love.hades.hadescalendardemo.widget;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.love.hades.hadescalendardemo.adapter.CardPagerFragmentAdapter;
import com.love.hades.hadescalendardemo.listener.OnCellItemClick;

public class CalendarCardPager extends ViewPager {

//    private CardPagerAdapter mCardPagerAdapter;
    private OnCellItemClick mOnCellItemClick;
    private CardPagerFragmentAdapter mCardPagerFragmentAdapter;

    public CalendarCardPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        init(context);
    }

    public CalendarCardPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalendarCardPager(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        FragmentActivity mContext = (FragmentActivity)context;
        mCardPagerFragmentAdapter = new CardPagerFragmentAdapter(mContext.getSupportFragmentManager());
        setAdapter(mCardPagerFragmentAdapter);
    }

    public CardPagerFragmentAdapter getCardPagerAdapter() {
        return mCardPagerFragmentAdapter;
    }

    public OnCellItemClick getOnCellItemClick() {
        return mOnCellItemClick;
    }

    public void setOnCellItemClick(OnCellItemClick mOnCellItemClick) {
        this.mOnCellItemClick = mOnCellItemClick;
        mCardPagerFragmentAdapter.setDefaultOnCellItemClick(this.mOnCellItemClick);
        mCardPagerFragmentAdapter.setOnCellItemClick(this.mOnCellItemClick);

        if (getChildCount() > 0) {
            for(int i=0; i<getChildCount(); i++) {
                View v = getChildAt(i);
                if (v instanceof CalendarCard) {
                    ((CalendarCard) v).setOnCellItemClick(this.mOnCellItemClick);
                }
            }
        }
    }



}
