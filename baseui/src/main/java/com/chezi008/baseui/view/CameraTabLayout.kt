package com.chezi008.baseui.view

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.chezi008.baseui.R
import com.chezi008.baseui.utils.DensityUtils

/**
 * @description ：
 * @author ：chezi008 on 2018/12/5 15:56
 * @email ：chezi008@163.com
 */
class CameraTabLayout : NormalTabLayout {

    private var mViewPager: ViewPager? = null
    //显然 lisenter 就应该是这样的
    private lateinit var listener: (View) -> Unit

    constructor(ctx: Context) : this(ctx, null)

    constructor(ctx: Context, attr: AttributeSet?) : super(ctx, attr)

    override fun initView(tabLayout: TabLayout) {
        super.initView(tabLayout)
        var ivCamera = ImageView(context)
        ivCamera.setImageResource(R.drawable.ic_tabitem_camera)
        val params = RelativeLayout.LayoutParams(200, 200)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        addView(ivCamera,params)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
//                //点击照相机，中间tab的时候不应该显示indicator
                if (tab?.position == 2) {
                    tabLayout.setSelectedTabIndicatorHeight(0)
                } else {
                    tabLayout.setSelectedTabIndicatorHeight(DensityUtils.dip2px(context, 2f))
                    var position = tab!!.position
                    if (position > 1)
                        position -= 1
                    mViewPager?.setCurrentItem(position, true)
                }

            }
        })

    }

    override fun addTabItem(items: Array<TabItem>) {
        super.addTabItem(items)
        mTabLayout.getTabAt(2)?.customView?.setOnClickListener {
            listener(it)
        }
    }
    override fun setViewPager(viewPager: ViewPager) {
        mViewPager = viewPager
        mViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                var positionOffset: Float = 0f
                if (position > 1) {
                    positionOffset = 1f
                }
                mTabLayout.setScrollPosition(
                    position, positionOffset, true)
            }

        })
    }

    fun setCameraListener(e: (View) -> Unit) {
        this.listener = e
    }
}