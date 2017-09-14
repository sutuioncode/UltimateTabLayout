package com.athbk.ultimatetablayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by athbk on 8/17/17.
 */

public class UltimateTabLayout extends FrameLayout {

    private final int DEFAULT_COLOR_UNDER_LINE = Color.parseColor("#000000");
    private final int DEFAULT_TAB_STYLE = 2; // style fixed.
    private final int DEFAULT_PADDING = 16;
    private final int DEFAULT_SIZE_ICON = 50;
    private final int DEFAULT_WIDTH_UNDER_LINE = 5;

    public final static int VERTICAL = 0;
    public final static int HORIZONTAL = 1;

    /**
     *  = 1: style sliding.
     *  = 2: style fixed.
     */
    private int tabStyle;

    private float tabTextSize;
    private int tabTextColor;

    private boolean tabUnderLineShow;
    private int tabUnderLineColor;

    private float tabWidth;
    private float tabHeight;

    private float tabPaddingTop;
    private float tabPaddingBottom;
    private float tabPaddingLeft;
    private float tabPaddingRight;

    private int tabPositionIcon;
    private float tabPaddingIcon;

    private float tabHeightIcon;
    private float tabWidthIcon;

    private int tabOrientation;

    private Paint mPaintUnderLine;

    private Context context;

    public UltimateTabLayout(Context context) {
        super(context);
        this.context = context;
        init(context, null, 0);
    }

    public UltimateTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context, attrs, 0);
    }

    public UltimateTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int def){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.UltimateTabLayout, def, 0);
        tabStyle = ta.getInt(R.styleable.UltimateTabLayout_tab_style, DEFAULT_TAB_STYLE);
        tabTextSize = ta.getFloat(R.styleable.UltimateTabLayout_tab_text_size, 14f);
        tabTextColor = ta.getResourceId(R.styleable.UltimateTabLayout_tab_text_color, R.color.tab_color_selected_default);
        tabUnderLineShow = ta.getBoolean(R.styleable.UltimateTabLayout_tab_under_line_show, true);
        tabUnderLineColor = ta.getColor(R.styleable.UltimateTabLayout_tab_under_line_color, DEFAULT_COLOR_UNDER_LINE);

        tabHeight = ta.getDimension(R.styleable.UltimateTabLayout_tab_height, -1);
        tabWidth = ta.getDimension(R.styleable.UltimateTabLayout_tab_width, -1);

        tabPaddingLeft = ta.getFloat(R.styleable.UltimateTabLayout_tab_padding_left, DEFAULT_PADDING);
        tabPaddingRight = ta.getFloat(R.styleable.UltimateTabLayout_tab_padding_right, DEFAULT_PADDING);
        tabPaddingTop = ta.getFloat(R.styleable.UltimateTabLayout_tab_padding_top, DEFAULT_PADDING);
        tabPaddingBottom = ta.getFloat(R.styleable.UltimateTabLayout_tab_padding_bottom, DEFAULT_PADDING);

        tabPositionIcon = ta.getInt(R.styleable.UltimateTabLayout_tab_position_icon, 0);
        tabPaddingIcon = ta.getFloat(R.styleable.UltimateTabLayout_tab_padding_icon, DEFAULT_PADDING);

        tabWidthIcon = ta.getFloat(R.styleable.UltimateTabLayout_tab_width_icon, DEFAULT_SIZE_ICON);
        tabHeightIcon = ta.getFloat(R.styleable.UltimateTabLayout_tab_height_icon, DEFAULT_SIZE_ICON);

        tabOrientation = ta.getInt(R.styleable.UltimateTabLayout_tab_orientation, HORIZONTAL);

        mPaintUnderLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintUnderLine.setColor(tabUnderLineColor);

        ta.recycle();

//        setWillNotDraw(false);
    }

    public void setViewPager(final ViewPager viewPager, IFTabAdapter tabAdapterIF){
        TabModel tabModel = new TabModel.Builder(tabUnderLineShow, DEFAULT_WIDTH_UNDER_LINE, tabOrientation)
                .setTabHeight((int)tabHeight)
                .setTabHeightIcon(tabHeightIcon)
                .setTabPaddingBottom(tabPaddingBottom)
                .setTabPaddingLeft(tabPaddingLeft)
                .setTabPaddingRight(tabPaddingRight)
                .setTabPaddingTop(tabPaddingTop)
                .setTabPaddingIcon(tabPaddingIcon)
                .setTabWidth((int)tabWidth)
                .setTabWidthIcon(tabWidthIcon)
                .setTabPositionIcon(tabPositionIcon)
                .setTabTextColor(tabTextColor)
                .setTabTextSize(tabTextSize)
                .build();


        if (tabStyle == 1){
            if (tabOrientation == VERTICAL){
                VerticalSlingTabView verticalSlingTabView = new VerticalSlingTabView(context);
                verticalSlingTabView.setPaintUnderLine(mPaintUnderLine);
                verticalSlingTabView.setTabModel(tabModel);
                verticalSlingTabView.setViewPager(viewPager, tabAdapterIF);
                this.addView(verticalSlingTabView);
            }
            else {
                HorizontalSlingTabView horizontalSlingTabView = new HorizontalSlingTabView(context);
                horizontalSlingTabView.setPaintUnderLine(mPaintUnderLine);
                horizontalSlingTabView.setTabModel(tabModel);
                horizontalSlingTabView.setViewPager(viewPager, tabAdapterIF);
                this.addView(horizontalSlingTabView);
            }
        }
        else {
            FixTabView fixTabView = new FixTabView(context);
            fixTabView.setPaintUnderLine(mPaintUnderLine);
            fixTabView.setTabModel(tabModel);
            fixTabView.setViewPager(viewPager, tabAdapterIF);
            this.addView(fixTabView);
        }

        requestLayout();
    }


    public void setTabStyle(int tabStyle) {
        this.tabStyle = tabStyle;
    }

    public void setTabTextSize(int tabTextSize) {
        this.tabTextSize = tabTextSize;
    }

    public void setTabTextColor(int tabTextColor) {
        this.tabTextColor = tabTextColor;
    }

    public void setTabUnderLineShow(boolean tabUnderLineShow) {
        this.tabUnderLineShow = tabUnderLineShow;
    }

    public void setTabUnderLineColor(int tabUnderLineColor) {
        this.tabUnderLineColor = tabUnderLineColor;
    }

    public void setTabWidth(int tabWidth) {
        this.tabWidth = tabWidth;
    }

    public void setTabHeight(int tabHeight) {
        this.tabHeight = tabHeight;
    }

    public void setTabPaddingTop(float tabPaddingTop) {
        this.tabPaddingTop = tabPaddingTop;
    }

    public void setTabPaddingBottom(float tabPaddingBottom) {
        this.tabPaddingBottom = tabPaddingBottom;
    }

    public void setTabPaddingLeft(float tabPaddingLeft) {
        this.tabPaddingLeft = tabPaddingLeft;
    }

    public void setTabPaddingRight(float tabPaddingRight) {
        this.tabPaddingRight = tabPaddingRight;
    }

    public void setTabPositionIcon(int tabPositionIcon) {
        this.tabPositionIcon = tabPositionIcon;
    }

    public void setTabPaddingIcon(float tabPaddingIcon) {
        this.tabPaddingIcon = tabPaddingIcon;
    }

    public void setTabHeightIcon(float tabHeightIcon) {
        this.tabHeightIcon = tabHeightIcon;
    }

    public void setTabWidthIcon(float tabWidthIcon) {
        this.tabWidthIcon = tabWidthIcon;
    }

    public void setTabOrientation(int tabOrientation) {
        this.tabOrientation = tabOrientation;
    }
}
