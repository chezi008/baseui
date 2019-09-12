package com.chezi008.baseui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.chezi008.baseui.view.LoadingDialog

/**
 * @description ：
 * @author ：chezi008 on 2018/6/11 19:51
 * @email ：chezi008@163.com
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView, IBaseUI {
    open val TAG = javaClass.simpleName
    open var mLoadingDialog: LoadingDialog? = null
    /**
     * hanlder
     */
    protected var mHandler = Handler(Looper.getMainLooper())
    /**
     * 主线程id
     */
    var UiThreadId = Thread.currentThread().id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId())
        initBaseView()
        initVariable()
        initView()
    }

    protected fun initBaseView() {
        mLoadingDialog = LoadingDialog.Builder(this)
                .setMessage("加载中...")
                .setLoadingCallback { showToast( "请求超时") }
                .setShowTime(8000).build()
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
            Toast.makeText(BaseActivity@this,msg,Toast.LENGTH_SHORT).show()
            return
        }
        mHandler.post { Toast.makeText(BaseActivity@this,msg,Toast.LENGTH_SHORT).show() }

    }

}