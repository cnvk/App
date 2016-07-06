package my.microsoft.com.imgcache;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import my.microsoft.com.day0627.R;

public class CacheActivity extends AppCompatActivity {
    private Button btn1,btn2;
    private ImageView iv1,iv2;
    String urlStr ="http://img11.360buyimg.com/da/jfs/t2884/148/2599938753/163002/9eefb92a/576ca2eaNa6c4f816.jpg";
    MomeryCache momeryCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        btn1= (Button) this.findViewById(R.id.CacheActivity_btn1);
        btn2= (Button) this.findViewById(R.id.CacheActivity_btn2);
        iv1= (ImageView) this.findViewById(R.id.CacheActivity_iv1);
        iv2= (ImageView) this.findViewById(R.id.CacheActivity_iv2);
        momeryCache=new MomeryCache();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            byte[] data =DownLoadImage.downloadFile(urlStr);
                            Bitmap bitmap=ImageCompression.getCompressionImage(data,100,100);//目标图片的长宽
                            //把下载好的图片存到缓存中,以便btn2使用
                            momeryCache.addBitmapFromLruCache(urlStr,bitmap);
                            //把bitmap传给主线程,更新UI
                            Message msg=Message.obtain();
                            msg.obj=bitmap;
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
            public void onClick(View view) {
                //直接从缓存中加载图片
                Bitmap bitmap=momeryCache.getBitmapFromLruCache(urlStr);
                if (bitmap!=null) {
                    iv2.setImageBitmap(bitmap);
                }
            }
        });
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap= (Bitmap) msg.obj;
            iv1.setImageBitmap(bitmap);
        }
    };



}
