package com.chezi008.baseuidemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chezi008.baseui.view.NormalTabLayout
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
        var items: Array<NormalTabLayout.TabItem> = arrayOf(
            NormalTabLayout.TabItem(R.string.tab_call, R.drawable.ic_launcher_foreground),
            NormalTabLayout.TabItem(R.string.tab_msg, R.drawable.ic_launcher_foreground),
            NormalTabLayout.TabItem(R.string.tab_camera, R.drawable.ic_launcher_foreground),
            NormalTabLayout.TabItem(R.string.tab_map, R.drawable.ic_launcher_foreground),
            NormalTabLayout.TabItem(R.string.tab_me, R.drawable.ic_launcher_foreground)
        )
        tabLayout.apply {
            addTabItem(items)
            setViewPager(viewPager)
        }
        var itemCamera: Array<NormalTabLayout.TabItem> = arrayOf(
            NormalTabLayout.TabItem(R.string.tab_call, R.drawable.ic_launcher_foreground),
            NormalTabLayout.TabItem(R.string.tab_msg, R.drawable.ic_launcher_foreground),
            NormalTabLayout.TabItem(0, R.drawable.ic_launcher_foreground),
            NormalTabLayout.TabItem(R.string.tab_map, R.drawable.ic_launcher_foreground),
            NormalTabLayout.TabItem(R.string.tab_me, R.drawable.ic_launcher_foreground)
        )
        cameraTabLayout.apply {
            setCameraListener {
                Toast.makeText(context,"点击了camera",Toast.LENGTH_SHORT).show()
            }
            addTabItem(itemCamera)
        }
    }
}
