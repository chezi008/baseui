package com.chezi008.baseui.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;


/**
 * @author ：chezi008 on 18-10-11 上午10:40
 * @description ：
 * @email ：chezi008@qq.com
 */
public class LoadingDialog {

    private ProgressDialog progressDialog;
    private Handler handler = new Handler();

    private LoadingDialog(Context ctx) {
        progressDialog = new ProgressDialog(ctx);


    }

    public void setMessage(String msg){
        if (!TextUtils.isEmpty(msg)) {
            progressDialog.setMessage(msg);
        }
    }
    private long showTime;
    public void setShowTime(long showTime){
        this.showTime = showTime;
    }

    public void show() {
        progressDialog.show();
        if (showTime > 0) {
            handler.postDelayed(cancelRunnable, showTime);
        }
    }

    public void dismiss() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        handler.removeCallbacks(cancelRunnable);
    }

    public boolean isShowing(){
        return progressDialog.isShowing();
    }

    private Runnable cancelRunnable = new Runnable() {
        @Override
        public void run() {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                if (loadingCallback != null) {
                    loadingCallback.onTimeOut();
                }
            }
        }
    };

    private LoadingCallback loadingCallback ;
    public void setLoadingCallback(LoadingCallback loadingCallback) {
        this.loadingCallback = loadingCallback;
    }

    public static class Builder {


        private Context context;

        private LoadingDialog loadingDialog;

        public Builder(Context ctx) {
            context = ctx;
            loadingDialog = new LoadingDialog(context);
        }

        public Builder setMessage(String msg) {
            loadingDialog.setMessage(msg);
            return this;
        }

        public Builder setShowTime(long time) {
            if (time < 0) {
                return this;
            }
            loadingDialog.setShowTime(time);
            return this;
        }

        public Builder setLoadingCallback(LoadingCallback loadingCallback) {
            loadingDialog.setLoadingCallback(loadingCallback);
            return this;
        }

        public LoadingDialog build(){
            return loadingDialog;
        }

        public void show() {
            loadingDialog.show();
        }
    }

    public interface LoadingCallback {
        void onTimeOut();
    }
}
