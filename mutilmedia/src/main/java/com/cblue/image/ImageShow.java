package com.cblue.image;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cblue.mutilmedia.R;

/**
 * 实现图片的二次采集（压缩效果）
 */
public class ImageShow extends AppCompatActivity {



    Button btn;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        btn = (Button)findViewById(R.id.imageshow_btn);
        iv = (ImageView)findViewById(R.id.imageshow_iv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                          byte[] data=   DownLoadImage.downloadFile("http://img11.360buyimg.com/da/jfs/t2884/148/2599938753/163002/9eefb92a/576ca2eaNa6c4f816.jpg");
                            Bitmap bitmap = ImageCompression.getCompressionImage(data,100,100);
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
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap  bitmap = (Bitmap)msg.obj;
            iv.setImageBitmap(bitmap);
        }
    };


}
