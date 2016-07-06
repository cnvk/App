package my.microsoft.com.day02;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 下载图片
 */
public class StartService05 extends Service {
    private String urlstr = null;
    private MyHandler myHandler;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;

    @Override
    public void onCreate() {
        super.onCreate();
        myHandler=new MyHandler();
        notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder=new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("下载图片");
        builder.setContentText("正在下载..");
    }
    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1!=0){
                //下面的这个false是表示显示进度  true表示循环流动,进度条会一直循环流动,看不出具体进度
                builder.setProgress(100,msg.arg1,false);
                //显示通知,100是id,可以随便写
                notificationManager.notify(100,builder.build());
            }
            if (msg.what==1){
                builder.setContentText("下载完成");
                notificationManager.notify(100,builder.build());
                //关闭服务
                stopSelf();
                Toast.makeText(getApplicationContext(),"下载完成",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        urlstr = intent.getStringExtra("urlstr");
        if (urlstr != null) {
            new Thread() {
                @Override
                public void run() {
                   byte[] data= downFile(urlstr);
                   boolean flag= saveFile(data,"001.jpg");
                    if (flag){
                        //下载完成发送一个what值为1的空Message,以便更新通知的进度
                        myHandler.sendEmptyMessage(1);
                        Log.i("aaa", "下载完成");
                    }

                }
            }.start();

        }
        return super.onStartCommand(intent, flags, startId);
    }
//下载文件的方法
    public byte[] downFile(String urlstr) {
        try {
            URL url = new URL(urlstr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            httpURLConnection.setRequestMethod("GET");
            //设置输入流
            httpURLConnection.setDoInput(true);
            //连接
            httpURLConnection.connect();
            //判断连接成功
            if (httpURLConnection.getResponseCode() == 200) {
                //获取文件的总大小
                int fileTotal=httpURLConnection.getContentLength();
                //获得文件下载的当前进度
                int currentFile=0;

                InputStream inputStream = httpURLConnection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] data = new byte[100];
                int len = 0;
                while ((len = inputStream.read(data)) != -1) {
                    //获得当前下载的长度
                    currentFile+=len;
                    //计算比率
                    int rate= (int) (currentFile/(float)fileTotal*100);
                    //把当前进度传递给Handler
                    Message message=Message.obtain();
                    message.arg1=rate;
                    myHandler.sendMessage(message);
                    byteArrayOutputStream.write(data, 0, len);

                }
                if (inputStream != null) {
                    inputStream.close();
                }
                byte[] result = byteArrayOutputStream.toByteArray();
                if(byteArrayOutputStream!=null){
                    byteArrayOutputStream.close();
                }
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    //保存下载文件的方法
    public boolean saveFile(byte[] data,String fileName){
        boolean flag=false;
        //判断SD卡是否挂载成功
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //得到SD卡的保存路径 /mnt/sdcard/donwnload
            File root=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            //创建一个写入文件  /mnt/sdcard/download/filename
            File file=new File(root,fileName);
            try {
                FileOutputStream fileOutputStream=new FileOutputStream(file);
                fileOutputStream.write(data,0,data.length);
                flag=true;
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return flag;

    }

}
