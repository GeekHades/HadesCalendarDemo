package com.love.hades.hadescalendardemo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.love.hades.hadescalendardemo.Utils.LogUtils;
import com.love.hades.hadescalendardemo.listener.OnCellItemClick;
import com.love.hades.hadescalendardemo.widget.CalendarCard;

import java.util.Calendar;

/**
 * Author：Hades on 2015/7/17 13:41
 * E_mail：li710611@163.com
 */
public class CardPagerViewFragment extends Fragment {

    private static String TAG = "CardPagerViewFragment";

    int mNum;

    private OnCellItemClick defaultOnCellItemClick;

    private FragmentActivity mContext;
    private CalendarCard card;

    /**
     * Create a new instance of CountingFragment, providing "num"
     * as an argument.
     */
    public static CardPagerViewFragment newInstance(int num) {
        CardPagerViewFragment f = new CardPagerViewFragment();
        LogUtils.d(TAG,"CardPagerViewFragment "+"  "+num);
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }

    /**
     * @return 返回当前日历的View
     */
    public CalendarCard getCurrentView(){
        if(null == this.card){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, mNum);
            card = new CalendarCard(this.getActivity());
            card.setDateDisplay(cal);
        }else {
            return this.card;
        }
        return this.card;
    }

    @Override
    public void onAttach(Activity activity) {
        LogUtils.d(TAG,"onAttach "+getActivity().toString()+"  "+mNum);
        super.onAttach(activity);
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, "onCreate "+getActivity().toString()+"  "+mNum);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    /**
     * The Fragment's UI is just a simple text view showing its
     * instance number.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtils.d(TAG, "onCreateView " + getActivity().toString() + "  " + mNum);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, mNum);

        card = new CalendarCard(this.getActivity());
        card.setDateDisplay(cal);
        card.notifyChanges();

        if (card.getOnCellItemClick() == null)
            card.setOnCellItemClick(defaultOnCellItemClick);

        return card;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.d(TAG, "onActivityCreated "+getActivity().toString()+"  "+mNum);
//        setListAdapter(new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings));
    }

    @Override
    public void onStart() {
        LogUtils.d(TAG, "onStart "+getActivity().toString()+"  "+mNum);
        super.onStart();
    }

    @Override
    public void onResume() {
        LogUtils.d(TAG, "onResume "+getActivity().toString()+"  "+mNum);
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtils.d(TAG, "onPause"+getActivity().toString()+"  "+mNum);
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtils.d(TAG, "onStop "+getActivity().toString()+"  "+mNum);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        LogUtils.d(TAG, "onDestroyView "+getActivity().toString()+"  "+mNum);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LogUtils.d(TAG, "onDestroy "+getActivity().toString()+"  "+mNum);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        LogUtils.d(TAG, "onActivityCreated "+getActivity().toString()+"  "+mNum);
        super.onDetach();
    }

    @Override
    public boolean getUserVisibleHint() {
        LogUtils.d(TAG, "--getUserVisibleHint() "+getActivity().toString()+"  "+mNum);
        return super.getUserVisibleHint();
    }

    public void setDefaultOnCellItemClick(OnCellItemClick defaultOnCellItemClick) {
        this.defaultOnCellItemClick = defaultOnCellItemClick;
        }
    }
