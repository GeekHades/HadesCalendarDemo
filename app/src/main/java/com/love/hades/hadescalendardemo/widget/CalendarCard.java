package com.love.hades.hadescalendardemo.widget;

/**
 * Author：Hades on 2015/7/16 16:50
 * E_mail：li710611@163.com
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.love.hades.hadescalendardemo.AppointmentActivity;
import com.love.hades.hadescalendardemo.R;
import com.love.hades.hadescalendardemo.listener.OnCellItemClick;
import com.love.hades.hadescalendardemo.listener.OnItemRender;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 */
public class CalendarCard extends RelativeLayout {

    private static String TAG = "CalendarCard";

    private TextView cardTitle;
    private int itemLayout = R.layout.card_item_simple;
    private OnItemRender mOnItemRender;
    private OnItemRender mOnItemRenderDefault;
    private OnCellItemClick mOnCellItemClick;
    private Calendar dateDisplay;
    /**
     * 可点击的日历
     */
    private ArrayList<CheckableLayout> cells = new ArrayList<CheckableLayout>();
    private LinearLayout cardGrid;
    private int currentYear, currentMonth, currentDay;
    /**
     * UpdateView invoke Flag
     */
    private boolean mRefreshFlag;

    public CalendarCard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public CalendarCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalendarCard(Context context) {
        super(context);
        init(context);
    }

    public void setCurrentChosen(int currentYear,int currentMonth,int currentDay){
        this.currentDay = currentDay;
        this.currentYear = currentYear;
        this.currentMonth = currentMonth;
    }

    private void init(Context ctx) {
        if (isInEditMode()) return;
        View layout = LayoutInflater.from(ctx).inflate(R.layout.card_view, null, false);

        if (dateDisplay == null)
            dateDisplay = Calendar.getInstance();

        cardTitle = (TextView) layout.findViewById(R.id.cardTitle);
        cardGrid = (LinearLayout) layout.findViewById(R.id.cardGrid);

        cardTitle.setText(new SimpleDateFormat("yyyy年MM月", Locale.getDefault()).format(dateDisplay.getTime()));

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        ((TextView) layout.findViewById(R.id.cardDay1)).setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        ((TextView) layout.findViewById(R.id.cardDay2)).setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        ((TextView) layout.findViewById(R.id.cardDay3)).setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        ((TextView) layout.findViewById(R.id.cardDay4)).setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        ((TextView) layout.findViewById(R.id.cardDay5)).setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        ((TextView) layout.findViewById(R.id.cardDay6)).setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        ((TextView) layout.findViewById(R.id.cardDay7)).setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));

        LayoutInflater la = LayoutInflater.from(ctx);
        for (int y = 0; y < cardGrid.getChildCount(); y++) {
            LinearLayout row = (LinearLayout) cardGrid.getChildAt(y);
            for (int x = 0; x < row.getChildCount(); x++) {
                CheckableLayout cell = (CheckableLayout) row.getChildAt(x);
                cell.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (CheckableLayout c : cells) {
                            CardGridItem item = (CardGridItem) c.getTag();
                            c.setChecked(false);
                            if(item!=null) {
                                if (item.getStatus() == 0) {
                                    ((TextView) c.getChildAt(0)).setTextColor(getResources().getColor(R.color.font_red));
                                } else if (item.getStatus() == 1) {
                                    ((TextView) c.getChildAt(0)).setTextColor(getResources().getColor(R.color.font_black_2));
                                } else {
                                    ((TextView) c.getChildAt(0)).setTextColor(getResources().getColor(R.color.font_black_4));
                                }
                            }
                        }

                        //TODO 选中
                        ((CheckableLayout) v).setChecked(true);
                        ((TextView) ((CheckableLayout) v).getChildAt(0)).setTextColor(getResources().getColor(R.color.white));

                        if (getOnCellItemClick() != null){
                            getOnCellItemClick().onCellClick(v, (CardGridItem) v.getTag()); // TODO create item
                            CardGridItem item = (CardGridItem) v.getTag();
                        }

                    }
                });

                View cellContent = la.inflate(itemLayout, cell, false);
                cell.addView(cellContent);
                cells.add(cell);
            }
        }

        addView(layout);

        mOnItemRenderDefault = new OnItemRender() {
            @Override
            public void onRender(CheckableLayout v, CardGridItem item) {
                if (item.getStatus()==1) {
                    ((TextView) v.getChildAt(0)).setText(item.getDayOfMonth().toString());
                    ((TextView) v.getChildAt(0)).setTextColor(getResources().getColor(R.color.font_black_2));
                } else if(item.getStatus()==-1){
                    ((TextView) v.getChildAt(0)).setText(item.getDayOfMonth().toString());
                    ((TextView) v.getChildAt(0)).setTextColor(getResources().getColor(R.color.font_black_4));
                }else{
                    ((TextView) v.getChildAt(0)).setText("今日");
                    ((TextView) v.getChildAt(0)).setTextColor(getResources().getColor(R.color.font_red));
                }
            }
        };

        updateCells();
    }

    private int getDaySpacing(int dayOfWeek) {
        if (Calendar.SUNDAY == dayOfWeek)
            return 6;
        else
            return dayOfWeek - 2;
    }

    private int getDaySpacingEnd(int dayOfWeek) {
        return 8 - dayOfWeek;
    }


    /**
     * 更新界面
     *
     * @param chooseY 选择的年
     * @param chooseM 选择的月
     * @param chooseDay 选择的日
     */
    public void updateView(){
        //refresh view
        updateCells();
    }

    private void updateCells() {
        Calendar today = Calendar.getInstance();
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        int thisMonth = today.get(Calendar.MONTH);
        int thisYear = today.get(Calendar.YEAR);

        Calendar cal;
        Integer counter = 1;
        if (dateDisplay != null){
            cal = (Calendar) dateDisplay.clone();
        }else{
            cal = Calendar.getInstance();
        }

        cal.set(Calendar.DAY_OF_MONTH, 1);

        int daySpacing = getDaySpacing(cal.get(Calendar.DAY_OF_WEEK));
        // INFO : wrong calculations of first line - fixed
        if (daySpacing > 0 && daySpacing < 6) {
            Calendar prevMonth = (Calendar) cal.clone();
            prevMonth.add(Calendar.MONTH, -1);
            prevMonth.set(Calendar.DAY_OF_MONTH, prevMonth.getActualMaximum(Calendar.DAY_OF_MONTH) - daySpacing + 1);
            for (int i = 0; i < daySpacing; i++) {
                CheckableLayout cell = cells.get(counter);
                cell.setTag(new CardGridItem(Integer.valueOf(prevMonth.get(Calendar.DAY_OF_MONTH))).setEnabled(false));
                cell.setEnabled(false);
                cell.setVisibility(View.INVISIBLE);
                (mOnItemRender == null ? mOnItemRenderDefault : mOnItemRender).onRender(cell, (CardGridItem) cell.getTag());
                counter++;
                prevMonth.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        int firstDay = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        int lastDay = cal.get(Calendar.DAY_OF_MONTH) + 1;

        int mon = cal.get(Calendar.MONTH);
        cal.set(Calendar.MONTH,++mon);

        mRefreshFlag = checkedFlag();
        Log.d(TAG, "mRefreshFlag=" + mRefreshFlag);
        for (int i = firstDay; i < lastDay; i++) {
            cal.set(Calendar.DAY_OF_MONTH, i - 1);
            Calendar date = (Calendar) cal.clone();
            date.add(Calendar.DAY_OF_MONTH, 1);
            CheckableLayout cell = cells.get(counter);
            int status = isTodayView(todayDay, thisMonth, thisYear, cal, i, cell);
            //setDate Date 是错误的数据！
            cell.setTag(new CardGridItem(i).setEnabled(true).setDate(date).setStatus(status));
            cell.setVisibility(View.VISIBLE);
            (mOnItemRender == null ? mOnItemRenderDefault : mOnItemRender).onRender(cell, (CardGridItem) cell.getTag());
            setCellCheckedHadChosed(i, cell);
            counter++;
        }

        if (dateDisplay != null)
            cal = (Calendar) dateDisplay.clone();
        else
            cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        daySpacing = getDaySpacingEnd(cal.get(Calendar.DAY_OF_WEEK));

        if (daySpacing > 0) {
            for (int i = 0; i < daySpacing; i++) {
                CheckableLayout cell = cells.get(counter);
                cell.setTag(new CardGridItem(i + 1).setEnabled(false)); // .setDate((Calendar)cal.clone())
                cell.setEnabled(false);
                cell.setVisibility(View.INVISIBLE);
                (mOnItemRender == null ? mOnItemRenderDefault : mOnItemRender).onRender(cell, (CardGridItem) cell.getTag());
                counter++;
            }
        }

        if (counter < cells.size()) {
            for (int i = counter; i < cells.size(); i++) {
                cells.get(i).setVisibility(View.GONE);
            }
        }

    }

    /**
     * 设置选中的状态
     *
     * @param i
     * @param cell
     */
    private void setCellCheckedHadChosed(int i, CheckableLayout cell) {
        //设置选中的状态： 条件是 年月匹配的是 mRefreshFlag = true. 最后是对应的天匹配！
        cell.setChecked(false);
        if(mRefreshFlag && i== AppointmentActivity.chosenDay){
            Log.d(TAG, "counter=" + i);
            cell.setChecked(true);
            ((TextView) cell.getChildAt(0)).setTextColor(getResources().getColor(R.color.white));
        }
    }

    private int isTodayView(int todayDay, int thisMonth, int thisYear, Calendar cal, int i, CheckableLayout cell) {
        int status;
        if(cal.get(Calendar.MONTH)==thisMonth&&cal.get(Calendar.YEAR)==thisYear) {
            if (i < todayDay) {
                status = -1;
                cell.setEnabled(false);
            } else if (i == todayDay) {
                status = 0;
                cell.setEnabled(true);
            } else {
                status = 1;
                cell.setEnabled(true);
            }
        }else{
            status = 1;
            cell.setEnabled(true);
        }
        return status;
    }


    public boolean checkedFlag(){

        //updateView flag
        if(AppointmentActivity.chosenYear==0 || AppointmentActivity.chosenMoth ==0){
            return false;
        }else{
            Log.d(TAG,"chonseYear="+AppointmentActivity.chosenYear);
            Log.d(TAG,"chonseMonth="+AppointmentActivity.chosenMoth);
            Log.d(TAG,"chonseDay="+AppointmentActivity.chosenDay);

            Log.d(TAG,"targetYear="+AppointmentActivity.mTargetYear);
            Log.d(TAG,"targetMonth="+AppointmentActivity.mTargetMonth);
            if(AppointmentActivity.chosenYear==AppointmentActivity.mTargetYear){
                if(AppointmentActivity.chosenMoth==AppointmentActivity.mTargetMonth){
                    mRefreshFlag = true;
                }else{
                    mRefreshFlag = false;
                }
            }else{
                mRefreshFlag = false;
            }
        }
        return   mRefreshFlag;
    };

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//		if (changed && cells.size() > 0) {
//			int size = (r - l) / 7;
//			for(CheckableLayout cell : cells) {
//				cell.getLayoutParams().height = size;
//			}
//		}
    }

    public int getItemLayout() {
        return itemLayout;
    }

    public void setItemLayout(int itemLayout) {
        this.itemLayout = itemLayout;
        //mCardGridAdapter.setItemLayout(itemLayout);
    }

    public OnItemRender getOnItemRender() {
        return mOnItemRender;
    }

    public void setOnItemRender(OnItemRender mOnItemRender) {
        this.mOnItemRender = mOnItemRender;
        //mCardGridAdapter.setOnItemRender(mOnItemRender);
    }

    public Calendar getDateDisplay() {
        return dateDisplay;
    }

    public void setDateDisplay(Calendar dateDisplay) {
        this.dateDisplay = dateDisplay;
        cardTitle.setText(new SimpleDateFormat("yyyy年MM月", Locale.getDefault()).format(dateDisplay.getTime()));
    }

    public OnCellItemClick getOnCellItemClick() {
        return mOnCellItemClick;
    }

    public void setOnCellItemClick(OnCellItemClick mOnCellItemClick) {
        this.mOnCellItemClick = mOnCellItemClick;
    }

    /**
     * call after change any input data - to refresh view
     */
    public void notifyChanges() {
        //mCardGridAdapter.init();
        updateCells();
    }

}

