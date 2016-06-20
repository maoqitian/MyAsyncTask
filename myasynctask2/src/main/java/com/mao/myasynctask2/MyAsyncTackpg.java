package com.mao.myasynctask2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

/**
 * Created by 毛麒添 on 2016/6/19 0019.
 */
public class MyAsyncTackpg extends AppCompatActivity {
    private ProgressBar mprogressBar;
    private Masynctack2 masynctack2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        mprogressBar= (ProgressBar) findViewById(R.id.progressBar2);
        masynctack2=new Masynctack2();
        masynctack2.execute();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(masynctack2!=null&&masynctack2.getStatus()== AsyncTask.Status.RUNNING){
            //cancel方法只是将对应的AsyncTask标记为cancel状态，并不是真正的取消线程的执行
            masynctack2.cancel(true);
        }
    }

    class Masynctack2 extends AsyncTask<Void,Integer,Void>{


        protected Void doInBackground(Void... params) {
            //模拟进度更新
            for (int i=0;i<100;i++){
                if(isCancelled()){
                    break;//如果收到状态为cancel 跳出循环
                }
                publishProgress(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(isCancelled()){
                return;
            }
            //获取进度更新值
            mprogressBar.setProgress(values[0]);
        }
    }
}
