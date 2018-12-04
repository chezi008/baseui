package com.pqtel.cloud.ui

/**
 * @description ：
 * @author ：chezi008 on 2018/6/11 19:24
 * @email ：chezi008@163.com
 */
interface IBaseView {
    fun showToast(msg: String)
    fun showLoadingDialog()
    fun hideLoadingDialog()
}