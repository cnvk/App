package com.cblue.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
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
public class StartService05 extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    private String urlStr = null;
    private MyHandler myHandler;
    private NotificationManager manager;
    private NotificationCompat.Builder builder;


    @Override
    public void onCreate() {
        super.onCreate();
        myHandler = new MyHandler();
        manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("下载图片");
        builder.setContentText("正在下载....");

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //执行耗时操作
        urlStr = intent.getStringExtra("urlStr");
        if(urlStr!=null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //执行耗时操作
                    try {
                       byte[] data =  downloadFile(urlStr);
                        Log.i("aaa","datalength="+data.length);
                        boolean flag = saveFile(data,"bingbing.jpg");
                        Log.i("aaa","flag="+flag);
                        if(flag) {
                            myHandler.sendEmptyMessage(1);
                            //Log.i("aaa", "下载完成");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }



        return START_REDELIVER_INTENT;
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1!=0){
                //设置为false的 显示进度  true循环流动
                builder.setProgress(100,msg.arg1,false);
                //Notification显示出来
                manager.notify(100,builder.build());
            }
            if(msg.what==1){
                //更新进度条
                builder.setContentText("下载完成");
                manager.notify(100,builder.build());
                //关闭服务 stopService
                stopSelf();
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
                currentFile+=len;
                //获得当前进度相对于总大小的比率
                //30/40.0 = 0.75
                int currentRate = (int)(currentFile/(float)fileTotal*100);
                //把当前进度传递给Handler
                Message message = Message.obtain();
                message.arg1 = currentRate;
                myHandler.sendMessage(message);

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
