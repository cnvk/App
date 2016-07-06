package com.cblue.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pavel on 2016/6/22.
 */
public class IntentService02 extends IntentService {

    MyHandler myHandler = null;
    public IntentService02(){
        super("IntentService02");
        myHandler = new MyHandler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
           boolean flag =  saveFile(downloadFile(intent.getStringExtra("urlStr")),"baidu.png");
            if(flag){
                myHandler.sendEmptyMessage(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what==1){
                Toast.makeText(getApplicationContext(),"下载成功",Toast.LENGTH_LONG).show();
            }
        }
    }

    //下载文件
    private byte[] downloadFile(String urlStr)throws Exception{
        URL url = new URL(urlStr);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        //设置请求方式
        httpURLConnection.setRequestMethod("GET");
        //设置输入流
        httpURLConnection.setDoInput(true);
        //连接
        httpURLConnection.connect();
        //连接成功，
        Log.i("aaa","code="+httpURLConnection.getResponseCode());
        if(httpURLConnection.getResponseCode()==200){
            //获得文件的总大小
            int fileTotal = httpURLConnection.getContentLength();
            //获得文件下载的当前进度
            int currentFile = 0;

            InputStream inputStream =  httpURLConnection.getInputStream();
            //创建输出流的对象
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] data = new byte[100];
            int len = -1;
            while((len=inputStream.read(data))!=-1){

                byteArrayOutputStream.write(data,0,len);
            }
            if(inputStream!=null){
                inputStream.close();
            }
            byte[] result =  byteArrayOutputStream.toByteArray();
          /*  if(byteArrayOutputStream!=null){
                byteArrayOutputStream.close();
            }*/
            return result;

        }

        return null;
    }

    //保存下载文件
    private boolean saveFile(byte[] data,String fileName)throws  Exception{
        boolean flag = false;
        //说明SD卡挂载成功
        Log.i("aaa","Environment.getExternalStorageState()==Environment.MEDIA_MOUNTED="+(Environment.getExternalStorageState()==Environment.MEDIA_MOUNTED));
        //==数值  equals字符串进行比较

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            //得到SD卡的保存路径 /mnt/sdcard/donwnload
            File root =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            //创建一个写入文件  /mnt/sdcard/download/filename
            File file = new File(root,fileName);
            Log.i("aaa","file="+file.getAbsolutePath());
            //往文件中写数据
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data,0,data.length);
            flag = true;
            fileOutputStream.close();
        }
        return flag;
    }

}
