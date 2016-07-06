package my.microsoft.com.myapplicationtest;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import my.microsoft.com.myapplicationtest.utils.HttpUtils;
import my.microsoft.com.myapplicationtest.utils.JsonUtils;
import my.microsoft.com.myapplicationtest.utils.News;

public class MyService extends Service {
    private NotificationManager manager;

    @Override
    public void onCreate() {
        // 初始化通知管理
        manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       final String path=intent.getStringExtra("path");
        if(path!=null){
            new Thread(){
                @Override
                public void run() {
                   byte[] b=HttpUtils.request(path);
                    try {
                        String json=new String(b,"utf-8");
                        List<News> list=JsonUtils.jsonTONews(json);

                        //图片下载完成后,发送通知
                            // 1创建通知的构建器
                            Log.i("aaa","图片下载完成");
                            NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext());
                            builder.setSmallIcon(R.mipmap.ic_launcher);
                            builder.setContentText("图片下载完成");
                            manager.notify(1,builder.build());
                            Log.i("aaa","发送通知");


                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        return super.onStartCommand(intent, flags, startId);
    }
    //把图片存入SD卡的方法
    public static String saveFile(byte[] data,String filename){
        File file=null;
        //判断SD卡挂载
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //获得SD卡的下载目录 /mnt/sdcard/donwnload
            File root=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            //完整路径mnt/sdcard/download/filename
            file=new File(root,filename);
            try {
                FileOutputStream fileOutputStream=new FileOutputStream(file);
                fileOutputStream.write(data,0,data.length);
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file.toString();
    }
    //保存到数据库的方法
    public static boolean saveData(List<News> list){
        //sd卡路径
        String sdpath=Environment.getExternalStorageDirectory().getAbsolutePath();
        String dbname="news.db";
        if (list!=null){
            for (News news:list){
                String id=news.getId();
                String title=news.getTitle();
                String shorttitle=news.getShorttitle();
                String imgpath=news.getLitpic();
                String description=news.getDescription();
                //新建或打开数据库
                SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(sdpath+File.separator+dbname,null);
                db.execSQL("create table if not exists news(id integer,title varchar(50), shorttitle varchar(50),imgpath varchar(50),description varchar(200))");
                db.execSQL("insert into news(id,title,shorttitle,imgpath,description) values("+id+",'"+title+"','"+shorttitle+"','"+imgpath+"','"+description+"')");
                Log.i("aaa","正在存入数据库一条数据");
            }

            return true;

        }
        return false;
    }

}
