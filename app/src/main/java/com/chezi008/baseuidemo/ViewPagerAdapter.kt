package com.pqtel.gysoldier.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @description ：
 * @author ：chezi008 on 2018/12/12 15:36
 * @email ：chezi008@163.com
 */
class ViewPagerAdapter(fm: FragmentManager, private val mData:List<Fragment>): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return mData[position]
    }

    override fun getCount(): Int {
        return mData.size
    }

}