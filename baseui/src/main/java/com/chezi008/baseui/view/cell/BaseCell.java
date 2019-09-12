package com.chezi008.baseui.view.cell;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.chezi008.baseui.R;
import com.chezi008.baseui.utils.DensityUtils;


/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2019/8/20 16:19
 */
public abstract class BaseCell extends ConstraintLayout {

    public BaseCell(Context context) {
        this(context, null);
    }

    public BaseCell(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_base_cell, this);
        initAttars(context, attrs);
        initView();
    }

    protected boolean isShowUnderLine;
    protected int leftIcon, rightIcon, centerIcon;
    protected int titleStyle, leftContentStyle, rightContentStyle;
    protected String title, leftContent, rightContent;

    protected float leftIconDimen, rightIconDimen;
    protected int dipLargest = DensityUtils.dip2px(getContext(), 25);

    private void initAttars(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseCell);
        isShowUnderLine = typedArray.getBoolean(R.styleable.BaseCell_cell_show_underline, false);

        leftIcon = typedArray.getResourceId(R.styleable.BaseCell_cell_left_icon, -1);
        leftIconDimen = typedArray.getDimension(R.styleable.BaseCell_cell_left_icon_dimen, dipLargest);
        title = typedArray.getString(R.styleable.BaseCell_cell_title);
        titleStyle = typedArray.getResourceId(R.styleable.BaseCell_cell_title_style, -1);
        centerIcon = typedArray.getResourceId(R.styleable.BaseCell_cell_center_icon, -1);
        leftContent = typedArray.getString(R.styleable.BaseCell_cell_left_content);
        leftContentStyle = typedArray.getResourceId(R.styleable.BaseCell_cell_left_content_style, -1);
        rightContent = typedArray.getString(R.styleable.BaseCell_cell_right_content);
        rightContentStyle = typedArray.getResourceId(R.styleable.BaseCell_cell_right_content_style, -1);
        rightIcon = typedArray.getResourceId(R.styleable.BaseCell_cell_right_icon, -1);
        rightIconDimen = typedArray.getDimension(R.styleable.BaseCell_cell_right_icon_dimen, dipLargest);

        typedArray.recycle();
    }

    protected LinearLayout llLeft, llCenter, llRight;
    protected int dipSmall = DensityUtils.dip2px(getContext(), 5);
    protected int dipNormal = DensityUtils.dip2px(getContext(), 10);
    protected int dipMinHeight = DensityUtils.dip2px(getContext(), 50);
    private View line;

    protected void initView() {
//        setMinHeight(dipMinHeight);

        line = findViewById(R.id.line);
        llLeft = findViewById(R.id.llLeft);
        llLeft.setGravity(Gravity.CENTER_VERTICAL);
        llCenter = findViewById(R.id.llCenter);
        llCenter.setGravity(Gravity.CENTER_VERTICAL);
        llRight = findViewById(R.id.llRight);
        llRight.setGravity(Gravity.CENTER_VERTICAL);
        initLeft(llLeft);
        initCenter(llCenter);
        initRight(llRight);

        line.setVisibility(isShowUnderLine ? VISIBLE : GONE);
    }

    abstract void initRight(LinearLayout llRight);

    abstract void initCenter(LinearLayout llCenter);

    abstract void initLeft(LinearLayout llLeft);

    public String getTitle() {
        return title;
    }

    public String getRightContent() {
        return rightContent;
    }
}
