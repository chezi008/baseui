package com.chezi008.baseui

/**
 * @description ：
 * @author ：chezi008 on 2018/6/11 23:07
 * @email ：chezi008@163.com
 */
interface IBaseUI {
    fun initVariable()
    /**
     * 初始化视图，一般做一些视图初始化，事件设定
     */
    fun initView()

    fun layoutResId(): Int
}