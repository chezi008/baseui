package com.chezi008.baseuidemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chezi008.baseui.view.tab.TabLayoutView
import com.pqtel.gysoldier.ui.adapter.ViewPagerAdapter
import com.pqtel.gysoldier.ui.fragment.PreviewFragment
import kotlinx.android.synthetic.main.activity_tab.*

class TabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        initTabLayout()
    }
    /**
     * 初始化 tablayout
     */
    private fun initTabLayout() {
        var items = arrayListOf(
            TabLayoutView.TabItem(resources.getString(R.string.tab_call), R.drawable.ic_launcher_foreground),
            TabLayoutView.TabItem("消息", R.drawable.ic_launcher_foreground),
            TabLayoutView.TabItem("相机", R.drawable.ic_launcher_foreground),
            TabLayoutView.TabItem("地图", R.drawable.ic_launcher_foreground),
            TabLayoutView.TabItem("我的", R.drawable.ic_launcher_foreground)
        )
        val fragments = arrayListOf(
            PreviewFragment(), PreviewFragment()
            , PreviewFragment(), PreviewFragment(), PreviewFragment()
        )
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager,fragments)
        tabLayout.apply {
            setupViewPager(viewPager)
            addTabItems(items)
        }
        var itemCamera = arrayListOf(
            TabLayoutView.TabItem(resources.getString(R.string.tab_call), R.drawable.ic_tabitem_camera),
            TabLayoutView.TabItem("消息", R.drawable.ic_tabitem_camera),
            TabLayoutView.TabItem("", R.drawable.ic_tabitem_camera),
            TabLayoutView.TabItem("地图", R.drawable.ic_tabitem_camera),
            TabLayoutView.TabItem("我的", R.drawable.ic_tabitem_camera)
        )
        cameraTabLayout.apply {
            setCameraListener {
                Toast.makeText(context,"点击了camera",Toast.LENGTH_SHORT).show()
            }
            addTabItems(itemCamera)
        }
    }
}
