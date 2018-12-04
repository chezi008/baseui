package com.pqtel.cloud.ui

/**
 * @description ：
 * @author ：chezi008 on 2018/6/8 17:35
 * @email ：chezi008@163.com
 */
interface IPresenter<T> {
    fun takeView(view: T)

    fun dropView()
}