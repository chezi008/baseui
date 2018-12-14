package com.chezi008.baseui.view.tab

import android.content.Context
import android.graphics.Color
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.chezi008.baseui.R
import com.chezi008.baseui.utils.DensityUtils

/**
 * @description ：
 *
 * 1、用户自定义添加tabitemview
 * @author ：chezi008 on 2018/12/12 17:21
 * @email ：chezi008@163.com
 */
open class TabLayoutView : RelativeLayout {

    constructor(ctx: Context) : this(ctx, null)

    constructor(ctx: Context, attr: AttributeSet?) : super(ctx, attr) {
        initView()
        initAttrs(ctx, attr)
    }
    fun addTabItems(tabItems:List<TabItem>){
        for ((i,tabItem: TabItem) in tabItems.withIndex()){
            addTabItemView(tabItem,i)
        }
    }

    fun addTabItemView(tabItem: TabItem,position:Int) {
        addTabItemView(buildTabitemView(tabItem),position)
    }

    fun addTabItemView(view:View,position:Int){
        if (mTabLayout.tabCount>position){
            var tab = mTabLayout.getTabAt(position)
            tab?.customView = view
        }else{
            var tab = mTabLayout.newTab()
            tab?.customView = view
            mTabLayout.addTab(tab)
        }
    }

    open fun setupViewPager(viewPager: ViewPager) {
        mTabLayout.setupWithViewPager(viewPager)
    }

    protected lateinit var mTabLayout:TabLayout
    private fun initView() {
        mTabLayout = TabLayout(context)
        val params = RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        params.addRule(ALIGN_PARENT_BOTTOM)
        mTabLayout.setBackgroundColor(Color.WHITE)
        addView(mTabLayout,params)
    }

    private var textColor: Int = 0
    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.TabLayoutView)
        textColor = ta.getResourceId(R.styleable.TabLayoutView_textColor, R.color.default_color_tab_text)
        ta.recycle()
    }

    open fun buildTabitemView(item: TabItem): View {
        var view = LinearLayout(context)
        view.orientation = LinearLayout.VERTICAL
        view.gravity = Gravity.CENTER
        //添加图标
        if (item.imageRes > 0) {
            var imageView = ImageView(context)
            if (item.text.isNotEmpty()) {
                imageView.adjustViewBounds = true
                imageView.maxWidth = DensityUtils.dip2px(context, 25f)
                imageView.maxHeight = DensityUtils.dip2px(context, 25f)
            }
            imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            imageView.setImageResource(item.imageRes)
            view.addView(imageView)
        }
        //添加文字
        if (item.text.isNotEmpty()) {
            var textView = TextView(context)
            textView.setText(item.text)
            textView.textSize = 12.0f
            textView.setPadding(5, 5, 5, 10)
            textView.gravity = Gravity.CENTER
            textView.setTextColor(resources.getColorStateList(textColor))
            view.addView(textView)
        }
        return view
    }

    class TabItem(val text: String, val imageRes: Int)
}