package com.chezi008.baseui.view

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.chezi008.baseui.R
import kotlinx.android.synthetic.main.widget_titlebar.view.*

/**
 *  -description: 顶部标题栏
 *  -author: created by tang on 2019/7/24 15:02
 */
open class TitleBar : LinearLayout {

    private var leftText: String? = null
    private var leftImg: Int? = null
    private var centerText: String? = null
    private var rightText: String? = null
    private var rightImg: Int? = null
    private var bgColor: Int? = null
    private var textColor: Int? = null
    private var leftGone = false

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttr(context, attrs)
        LayoutInflater.from(context).inflate(R.layout.widget_titlebar, this)
        initView()
    }

    private fun initAttr(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar)
        leftText = typedArray.getString(R.styleable.TitleBar_titleLeftText)
        leftImg = typedArray.getResourceId(
            R.styleable.TitleBar_titleLeftImage,
            R.drawable.common_title_back
        )
        centerText = typedArray.getString(R.styleable.TitleBar_titleCenterText)
        rightText = typedArray.getString(R.styleable.TitleBar_titleRightText)
        rightImg = typedArray.getResourceId(R.styleable.TitleBar_titleRightImage, 0)
        bgColor = typedArray.getColor(R.styleable.TitleBar_titleBgColor, Color.WHITE)
        textColor = typedArray.getColor(R.styleable.TitleBar_titleTextColor, Color.BLACK)
        leftGone = typedArray.getBoolean(R.styleable.TitleBar_titleLeftGone, false)
        typedArray.recycle()
    }

    private fun initView() {
        setBackgroundColor(bgColor!!)
        tv_tb_left.setTextColor(textColor!!)
        tv_tb_center.setTextColor(textColor!!)
        tv_tb_right.setTextColor(textColor!!)



        if (!TextUtils.isEmpty(leftText)) {
            tv_tb_left.text = leftText
            ll_tb_left.visibility = View.VISIBLE
        }

        if (!TextUtils.isEmpty(rightText)) {
            tv_tb_right.text = rightText
            ll_tb_right.visibility = View.VISIBLE
        }

        if (!TextUtils.isEmpty(centerText)) {
            tv_tb_center.text = centerText
        }

        if (leftImg != 0) {
            iv_tb_left.setImageResource(leftImg!!)
            ll_tb_left.visibility = View.VISIBLE
        }

        if (rightImg != 0) {
            iv_tb_right.setImageResource(rightImg!!)
            ll_tb_right.visibility = View.VISIBLE
        }

        ll_tb_left.setOnClickListener {
            if (mTitleBarClickListener?.leftClick() == null) {
                back()
            } else {
                mTitleBarClickListener?.leftClick()
            }
        }

        ll_tb_right.setOnClickListener {
            mTitleBarClickListener?.rightClick()
        }

        ll_tb_left.visibility = if (leftGone){View.GONE}else{View.VISIBLE}
    }

    fun setLeftGone(isGone: Boolean) {
        if (isGone) {
            ll_tb_left.visibility = View.GONE
        } else {
            ll_tb_left.visibility = View.VISIBLE
        }
    }

    fun setLeftIcon(resId: Int) {
        if (resId > 0) {
            iv_tb_left.setImageResource(resId)
            ll_tb_left.visibility = View.VISIBLE
        } else {
            ll_tb_left.visibility = View.GONE
        }
    }

    fun setLeftText(text: String) {
        if (!TextUtils.isEmpty(text)) {
            tv_tb_left.text = text
            ll_tb_left.visibility = View.VISIBLE
        } else {
            ll_tb_left.visibility = View.GONE
        }
    }

    fun setRightIcon(resId: Int) {
        if (resId > 0) {
            iv_tb_right.setImageResource(resId)
            ll_tb_right.visibility = View.VISIBLE
        } else {
            ll_tb_right.visibility = View.GONE
        }
    }

    fun setRightText(text: String) {
        if (!TextUtils.isEmpty(text)) {
            tv_tb_right.text = text
            ll_tb_right.visibility = View.VISIBLE
        } else {
            ll_tb_right.visibility = View.GONE
        }
    }

    fun setCenterText(text: String) {
        tv_tb_center.text = text
    }

    private var mTitleBarClickListener: TitleBarClickListener? = null

    open fun setTitleBarClickListener(titleBarClickListener: TitleBarClickListener) {
        mTitleBarClickListener = titleBarClickListener
    }

    interface TitleBarClickListener {
        fun leftClick()
        fun rightClick()
    }

    private fun back() {
        findActivity(context)?.finish()
    }

    private fun findActivity(context: Context): Activity? {
        if (context is Activity) {
            return context
        }
        return if (context is ContextWrapper) {
            var wrapper: ContextWrapper = context
            findActivity(wrapper.baseContext)
        } else {
            null
        }
    }
}