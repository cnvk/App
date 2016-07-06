package my.microsoft.com.myapplicationtest.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by my on 2016/6/24.
 */
public class HttpUtils {
    public static byte[] request(String urlpath){
        try {
            //创建URL对象
            URL url=new URL(urlpath);
            //调用openconnection方法
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            //设置超时
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            //设置输入流
            connection.setDoInput(true);
            //连接
            connection.connect();
            if (connection.getResponseCode()==200){
                InputStream is=connection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                byte[] buf=new byte[1024];
                int len=-1;
                while ((len=is.read(buf))!=-1){
                    byteArrayOutputStream.write(buf,0,len);
                }
                if (is!=null){
                    is.close();
                }
                return byteArrayOutputStream.toByteArray();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
