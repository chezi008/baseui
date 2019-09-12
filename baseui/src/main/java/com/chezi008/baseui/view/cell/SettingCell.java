package com.chezi008.baseui.view.cell;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chezi008.baseui.R;
import com.chezi008.baseui.utils.DensityUtils;


/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2019/8/20 16:38
 */
public class SettingCell extends BaseCell {

    public SettingCell(Context context) {
        super(context);
    }

    public SettingCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView() {
        setBackgroundResource(R.drawable.common_bg_item_click);
        super.initView();
    }


    private ImageView ivLeftIcon;
    private TextView tvRightContent, tvCenter;

    @Override
    void initLeft(LinearLayout llLeft) {
        ivLeftIcon = new ImageView(getContext());
        //加载图标
        ivLeftIcon.setVisibility(leftIcon > 0 ? VISIBLE : GONE);
        if (leftIcon > 0) {
            ivLeftIcon.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            ivLeftIcon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            ivLeftIcon.setImageResource(leftIcon);
        }
        llLeft.addView(ivLeftIcon);
    }

    @Override
    void initCenter(LinearLayout llCenter) {
        llCenter.setOrientation(LinearLayout.HORIZONTAL);
        if (!TextUtils.isEmpty(title)) {
            tvCenter = new TextView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;
            tvCenter.setLayoutParams(layoutParams);
            tvCenter.setTextAppearance(getContext(), R.style.CommonBlackText_14sp);
            tvCenter.setLines(1);
            tvCenter.setText(title);
            tvCenter.setGravity(Gravity.CENTER_VERTICAL);
            llCenter.addView(tvCenter);
        }
        if (centerIcon > 0) {
//            llCenter.setPadding(dipNormal,dipNormal,dipNormal,dipSmall);
            setCenterImage(centerIcon);
        }
    }

    @Override
    void initRight(LinearLayout llRight) {
        if (rightIcon > 0) {//rightIcon
            ImageView ivRight = new ImageView(getContext());
            ivRight.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            ivRight.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            ivRight.setImageResource(rightIcon);
            llRight.addView(ivRight);
        }
        setRightContent(rightContent);
    }


    public void setRightContent(String content) {
        rightContent = content;
        if (!TextUtils.isEmpty(content)) {
            if (tvRightContent != null) {
                llRight.removeView(tvRightContent);
            }
            tvRightContent = new TextView(getContext());
            tvRightContent.setTextAppearance(getContext(), R.style.CommonBlackText_14sp);
            tvRightContent.setLines(1);
            tvRightContent.setText(content);
            tvRightContent.setPadding(dipNormal, 0, dipNormal, 0);
            llRight.addView(tvRightContent, 0);
        }
    }

    private ImageView ivRight;

    public void setCenterImage(@DrawableRes int resId) {
        if (ivRight != null) {
            llCenter.removeView(ivRight);
        }
        int dimen = DensityUtils.dip2px(getContext(), 55);
        ivRight = new ImageView(getContext());
        ivRight.setLayoutParams(new LinearLayout.LayoutParams(dimen, dimen));
        ivRight.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ivRight.setImageResource(resId);
        llCenter.addView(ivRight);
    }

    public ImageView getCenterImage() {
        return ivRight;
    }
}
