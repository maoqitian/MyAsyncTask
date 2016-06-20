package com.mao.myasynctask2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by 毛麒添 on 2016/6/18 0018.
 */
public class ImageTest extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;
    private static String URL = "image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%20%E6%8C%89%E9%92%AE&step_word=&pn=5&spn=0&di=47992088980&pi=&rn=1&tn=baiduimagedetail&is=&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=860041642%2C1965219463&os=1092350559%2C1648351914&simid=3127184842%2C3615838281&adpicid=0&ln=1968&fr=&fmq=1466258095241_R&fm=result&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fstatic.freepik.com%2Ffree-photo%2Fbutton_2900555.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fvg_z%26e3Bu6jjrth_z%26e3Bv54AzdH3Fu6jj-ri5p5AzdH3Fk7pp5g_d89nn_z%26e3Bip4&gsm=0&rpstart=0&rpnum=0";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        imageView = (ImageView) findViewById(R.id.image1);
        progressBar = (ProgressBar) findViewById(R.id.mpb);
        //设置传递进去的参数
        new MyAsyncTask().execute(URL);


    }

    //定义一个内部方法初始化AsyncTask
    class MyAsyncTask extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressBar.setVisibility(View.GONE);
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url=params[0];//获取params数组第一位的数据，也就只是第一个数据，我所定义的图片地址URL
            Bitmap bitmap=null;
            URLConnection connection;//网络连接对象
            InputStream is;//获取数据输入流对象
            try {
                connection=new URL(url).openConnection();//获取网络连接对象
                is=connection.getInputStream();//获取输入流
                BufferedInputStream bis=new BufferedInputStream(is);
                bitmap= new BitmapFactory().decodeStream(bis);//通过decodeStream将输入流解析成图片
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
}
