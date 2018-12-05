package com.chezi008.baseui.view

import android.content.Context
import android.graphics.Color
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.chezi008.baseui.R
import com.chezi008.baseui.utils.DensityUtils


/**
 * @description ：
 * @author ：chezi008 on 2018/6/11 21:14
 * @email ：chezi008@163.com
 */
open class NormalTabLayout : RelativeLayout {

    protected var mTabLayout: TabLayout = TabLayout(context)

    constructor(ctx: Context) : this(ctx, null)

    constructor(ctx: Context, attr: AttributeSet?) : super(ctx, attr) {
        initAttrs(ctx,attr)
        initView(mTabLayout)
    }

    private var textColor:Int=0
    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.NormalTabLayout)
        textColor = ta.getResourceId(R.styleable.NormalTabLayout_textColor,  R.color.default_color_tab_text)
        ta.recycle()
    }


    open fun initView(tabLayout: TabLayout) {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(R.attr.colorAccent, typedValue, true)
        var colorAccent = typedValue.data
        tabLayout.setSelectedTabIndicatorColor(colorAccent)
        tabLayout.setBackgroundColor(Color.WHITE)
        var params = RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        addView(tabLayout, params)

    }


    open fun addTabItem(items: Array<TabItem>) {
        for (item in items) {
            var tab = mTabLayout.newTab()
            tab.customView = createItemView(item)
            mTabLayout.addTab(tab)
        }
    }

    open fun setViewPager(viewPager: ViewPager) {
        mTabLayout.setupWithViewPager(viewPager)
    }

    /**
     * 创建item视图
     * 1、目前包括图片和文字，图片在上，文字在下。
     * 2、照相机只包含图片
     */
    private fun createItemView(item: TabItem): View {
        var view = LinearLayout(context)
        view.orientation = LinearLayout.VERTICAL
        view.gravity = Gravity.CENTER
        //添加图标
        if (item.resId > 0) {
            var imageView = ImageView(context)
            if (item.tabTxt > 0) {
                imageView.adjustViewBounds = true
                imageView.maxWidth = DensityUtils.dip2px(context, 25f)
                imageView.maxHeight = DensityUtils.dip2px(context, 25f)
            }
            imageView.setImageResource(item.resId)
            imageView.setPadding(5, 15, 5, 5)
            view.addView(imageView)
        }
        //添加文字
        if (item.tabTxt > 0) {
            var textView = TextView(context)
            textView.setText(item.tabTxt)
            textView.textSize = 12.0f
            textView.setPadding(5, 5, 5, 10)
            textView.gravity = Gravity.CENTER
            var colors = resources.getColorStateList(textColor)
            textView.setTextColor(resources.getColorStateList(textColor))
            view.addView(textView)
        }
        return view
    }

    class TabItem(var tabTxt: Int, var resId: Int)


}