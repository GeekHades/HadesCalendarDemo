package com.love.hades.hadescalendardemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.love.hades.hadescalendardemo.Utils.LogUtils;
import com.love.hades.hadescalendardemo.fragment.CardPagerViewFragment;
import com.love.hades.hadescalendardemo.listener.OnCellItemClick;
import com.love.hades.hadescalendardemo.widget.CalendarCard;

/**
 * Author：Hades on 2015/7/17 13:38
 * E_mail：li710611@163.com
 */
public class CardPagerFragmentAdapter extends FragmentStatePagerAdapter {

    private static String TAG = "CardPagerFragmentAdapter";
    private OnCellItemClick mOnCellItemClick;
    private CardPagerViewFragment cardPagerViewFragment;

    public CardPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LogUtils.d(TAG, "CardPagerFragmentAdapter instantiateItem    position=" + position);
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        LogUtils.d(TAG, "CardPagerFragmentAdapter getItem    position=" + position);
        cardPagerViewFragment = CardPagerViewFragment.newInstance(position);
        return cardPagerViewFragment;
    }

    public CalendarCard getCurrentView(){
        return cardPagerViewFragment.getCurrentView();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

//    public void setDefaultOnCellItemClick(OnCellItemClick defaultOnCellItemClick) {
//        cardPagerViewFragment.setDefaultOnCellItemClick(defaultOnCellItemClick);
//    }

//    public void setOnCellItemClick(OnCellItemClick mOnCellItemClick) {
//        cardPagerViewFragment.setOnCellItemClick(mOnCellItemClick);
//    }



}
