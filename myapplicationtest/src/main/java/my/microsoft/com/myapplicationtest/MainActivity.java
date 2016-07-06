package my.microsoft.com.myapplicationtest;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import my.microsoft.com.myapplicationtest.utils.HttpUtils;
import my.microsoft.com.myapplicationtest.utils.JsonUtils;
import my.microsoft.com.myapplicationtest.utils.News;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String path="http://www.3dmgame.com/sitemap/api.php?row=10&typeid=1&paging=1&page=1";
        intent=new Intent(this,MyService.class);
        intent.putExtra("path",path);
        startService(intent);

    }

    //按钮跳转到下一页面
    public void btn_tonext(View view){
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("aaa","onPause-----");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("aaa","onStop-----");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("aaa","onRestart-----");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
        Log.i("aaa","onDestroy已关闭服务");
    }
}
