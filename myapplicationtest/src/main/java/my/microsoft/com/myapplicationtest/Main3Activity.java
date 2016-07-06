package my.microsoft.com.myapplicationtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import my.microsoft.com.myapplicationtest.utils.HttpUtils;

public class Main3Activity extends AppCompatActivity {
    private TextView tv;
    private TextView tv1;
    private TextView tv2;
    private ImageView iv;
    Bitmap bitmap=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tv= (TextView) this.findViewById(R.id.Main3Activity_tv);
        tv1= (TextView) this.findViewById(R.id.Main3Activity_tv1);
        tv2= (TextView) this.findViewById(R.id.Main3Activity_tv2);
        iv= (ImageView) this.findViewById(R.id.Main3Activity_iv);

        Intent intent=getIntent();
        if (intent!=null){
            String id=intent.getStringExtra("id");
            String title=intent.getStringExtra("title");
            String shorttitle=intent.getStringExtra("shorttitle");
            String description=intent.getStringExtra("description");
            String imgpath=intent.getStringExtra("imgpath");
            Log.i("aaa","页面3得到图片地址"+imgpath);
            bitmap = BitmapFactory.decodeFile(imgpath);
//            byte [] b= HttpUtils.request(imgpath);
//            if (b != null) {
//                Log.i("aaa","页面3bitmap不为空");
//                bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
//
//            }else {
//                Log.i("aaa","页面3的b 为空");
//            }
            tv.setText("ID:"+id+"\r\n标题为："+title);
            iv.setImageBitmap(bitmap);
            tv1.setText("副标题："+shorttitle);
            tv2.setText("正文："+description);
        }
    }
}
