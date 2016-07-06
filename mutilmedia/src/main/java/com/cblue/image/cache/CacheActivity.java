package com.cblue.image.cache;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cblue.image.DownLoadImage;
import com.cblue.image.ImageCompression;
import com.cblue.mutilmedia.R;

public class CacheActivity extends AppCompatActivity {


    Button btn1,btn2;
    ImageView iv1,iv2;
    MomeryCache momeryCache;
    String urlStr ="http://img11.360buyimg.com/da/jfs/t2884/148/2599938753/163002/9eefb92a/576ca2eaNa6c4f816.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        btn1 = (Button)findViewById(R.id.cacheactivity_btn1);
        btn2 = (Button)findViewById(R.id.cacheactivity_btn2);
        iv1 = (ImageView)findViewById(R.id.cacheactivity_iv1);
        iv2 = (ImageView)findViewById(R.id.cacheactivity_iv2);
        momeryCache = new MomeryCache();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            byte[] data=   DownLoadImage.downloadFile(urlStr);
                            Bitmap bitmap = ImageCompression.getCompressionImage(data,100,100);
                            momeryCache.addBitmapToLruCache(urlStr,bitmap);
                            Message msg = Message.obtain();
                            msg.obj = bitmap;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap =   momeryCache.getBitmapFromLruCache(urlStr);
                if(bitmap!=null){
                    iv2.setImageBitmap(bitmap);
                }
            }
        });


    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap  bitmap = (Bitmap)msg.obj;
            iv1.setImageBitmap(bitmap);
        }
    };
}
