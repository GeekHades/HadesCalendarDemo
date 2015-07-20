package com.love.hades.hadescalendardemo.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.love.hades.hadescalendardemo.Utils.LogUtils;
import com.love.hades.hadescalendardemo.fragment.CardPagerViewFragment;
import com.love.hades.hadescalendardemo.listener.OnCellItemClick;

/**
 * Author：Hades on 2015/7/17 13:38
 * E_mail：li710611@163.com
 */
public class CardPagerFragmentAdapter extends FragmentStatePagerAdapter {

    private static String TAG = "CardPagerFragmentAdapter";
    private OnCellItemClick mOnCellItemClick;
    private CardPagerViewFragment cardPagerViewFragment;

    private int index;

    public CardPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LogUtils.d(TAG, " instantiateItem    position=" + position);
        cardPagerViewFragment = CardPagerViewFragment.newInstance(position);
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        LogUtils.d(TAG, " getItem    position=" + position);
        LogUtils.d(TAG, " getItem    position= cardPagerViewFragment.getUserVisibleHint()" + cardPagerViewFragment.getUserVisibleHint());
        if(null == cardPagerViewFragment){
            cardPagerViewFragment = CardPagerViewFragment.newInstance(position);
        }

        return cardPagerViewFragment;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        LogUtils.d(TAG, " destroyItem    position=" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        LogUtils.d(TAG, "    isViewFromObject()");
        return super.isViewFromObject(view, object);
    }

    @Override
    public void startUpdate(ViewGroup container) {
        LogUtils.d(TAG, "    startUpdate()" );
        super.startUpdate(container);
    }

//    setPrimaryItem

    /**
     * 尽调用一次  但是加载进来的好像不是正在显示的！
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        LogUtils.d(TAG, "    setPrimaryItem()" );
        if(null != cardPagerViewFragment){
            LogUtils.d(TAG, "    Fragment is onResume()?"+ cardPagerViewFragment.getUserVisibleHint() );
        }
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public Parcelable saveState() {
        LogUtils.d(TAG, "    saveState()" );
        return super.saveState();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        LogUtils.d(TAG, "    restoreState()" );
        super.restoreState(state, loader);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        LogUtils.d(TAG, "    finishUpdate()" );
        super.finishUpdate(container);
    }

//    public CalendarCard getCurrentView(){
//        return cardPagerViewFragment.getCurrentView();
//    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public void setDefaultOnCellItemClick(OnCellItemClick defaultOnCellItemClick) {
//        cardPagerViewFragment.setDefaultOnCellItemClick(defaultOnCellItemClick);
    }

    public void setOnCellItemClick(OnCellItemClick mOnCellItemClick) {
//        cardPagerViewFragment.setOnCellItemClick(mOnCellItemClick);
    }

    /**
     * 判断是否为空
     *
     * @param position
     */
    private void cardPagerViewFragmentIsEmpty(int position) {
        if(null == cardPagerViewFragment){
            cardPagerViewFragment = CardPagerViewFragment.newInstance(position);
        }
    }



}
