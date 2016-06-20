package com.mao.myasynctask;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by 毛麒添 on 2016/6/18 0018.
 * 异步任务
 */
public class MyAsyncTask extends AsyncTask<Void,Void,Void> {

    /**
     * 继承AsyncTask需要的三个泛型参数
     * (1)Params:启动任务时输入的参数类型
     * (2)Progress:后台任务执行中返回进度值的类型
     * (3)后台执行任务完成后返回结果的类型  
     */

    //执行后台耗时操作时调用，通常用户完成一些初始化操作
    protected void onPreExecute() {

        super.onPreExecute();
        Log.d("maoqitian", "onPreExecute");
    }

    //当doInBackground()执行后系统会自动调用该方法，并将doInBackground()方法的返回值传给该方法
    protected void onPostExecute(Void aVoid) {

        super.onPostExecute(aVoid);
        Log.d("maoqitian", "onPostExecute");
    }

    //在doInBackground()中调用publishProgress()方法更新任务执行进度后，就会触发该方法
    protected void onProgressUpdate(Void... values) {

        super.onProgressUpdate(values);
        Log.d("maoqitian", "onProgressUpdate");
    }

    //该方法必须重写，异步执行后台线程将要完成的任务
    protected Void doInBackground(Void... params) {
        Log.d("maoqitian", "doInBackground");
        publishProgress();
        return null;
    }
}
