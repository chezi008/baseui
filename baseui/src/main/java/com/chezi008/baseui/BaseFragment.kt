package com.pqtel.cloud.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chezi008.baseui.view.LoadingDialog

/**
 * @description ：基类的fragment
 *                 1、提供标题栏功能，目前暂时不需要公共的标题栏
 *                 2、kotlin类和方法默认是final属性，如需要被子类重写和继承，请使用open关键字
 * @author ：chezi008 on 2018/6/8 10:51
 * @email ：chezi008@163.com
 */
abstract class BaseFragment : Fragment(), IBaseView, IBaseUI {
    open val TAG = javaClass.simpleName
    /**
     * 是否已经加载视图
     */
    private var isViewPrepared = false
    /**
     * 是否已经加载数据
     */
    private var isDataLoaded: Boolean = false

    open var mLoadingDialog: LoadingDialog? = null
    /**
     * hanlder
     */
    private var mHandler = Handler(Looper.getMainLooper())
    /**
     * 主线程id
     */
    private var UiThreadId = Thread.currentThread().id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVariable()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(layoutResId(), container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepared = true
        initBaseView()
        initView()
    }

    private fun initBaseView(){
        mLoadingDialog = LoadingDialog.Builder(context)
                .setMessage("加载中...")
                .setLoadingCallback { showToast("请求超时") }
                .setShowTime(8000).build()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isViewPrepared) {
            return
        }
        if (isVisibleToUser) {
            if (!isDataLoaded) {
                onLazyLoad()
                isDataLoaded = true
                return
            }
        }
    }

    /**
     * 显示加载dialog
     */
    override fun showLoadingDialog() {
        if (Thread.currentThread().id == UiThreadId) {
            if (mLoadingDialog!!.isShowing) {
                return
            }
            mLoadingDialog!!.show()
            return
        }
        mHandler.post(Runnable {
            if (mLoadingDialog!!.isShowing) {
                return@Runnable
            }
            mLoadingDialog!!.show()
        })
    }

    /**
     * 隐藏加载dialog
     */
    override fun hideLoadingDialog() {
        if (Thread.currentThread().id == UiThreadId) {
            mLoadingDialog!!.dismiss()
            return
        }
        mHandler.post { mLoadingDialog!!.dismiss() }
    }

    override fun showToast(msg: String) {
        if (Thread.currentThread().id == UiThreadId) {
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
            return
        }
        mHandler.post { Toast.makeText(context,msg,Toast.LENGTH_SHORT).show() }

    }



    open fun onLazyLoad(){

    }
}