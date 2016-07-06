package my.microsoft.com.myapplicationtest.utils;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import my.microsoft.com.myapplicationtest.MyService;

/**
 * Created by my on 2016/6/24.
 */
public class JsonUtils {

    public static List<News> jsonTONews(String json){
        List<News> list=new ArrayList<>();
        if (json!=null){
            Log.i("aaa","json不为空");
            try {
                JSONObject root=new JSONObject(json);
                JSONObject data=root.getJSONObject("data");

                for (int i=0;i<10;i++){
                    JSONObject jsonObject=data.getJSONObject(i+"");
                    String id=jsonObject.getString("id");

                    String title=jsonObject.getString("title");
                    String shorttitle=jsonObject.getString("shorttitle");
                    String litpic="http://www.3dmgame.com"+jsonObject.getString("litpic");
                    String description=jsonObject.getString("description");
                    byte[] b=HttpUtils.request(litpic);
                    //二次采集,缩小图片,得到bitmap
                    //140,110是目标图片的尺寸,目标尺寸必须要比原图小,二次采集才起作用
                    Bitmap bitmap=ImageCompression.getCompressionImage(b,140,110);
                    Log.i("aaa","bitmap大小---"+bitmap.getByteCount());
                    //将bitmap转成byte[],调方法存入sd卡
                    byte[] bytes1=Bitmap2Bytes(bitmap);
                    Log.i("aaa","bytes1.length---"+bytes1.length);
                    //调用下载图片的方法,并返回图片保存路径
                    String imgpath=MyService.saveFile(bytes1,id+".jpg");
                    Log.i("aaa","图片保存路径"+imgpath);

                     News news=new News(id,title,shorttitle,imgpath,description);

                    list.add(news);

                }
                //调用存入数据库的方法
                boolean flag=MyService.saveData(list);
                Log.i("aaa","数据存完"+flag);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
    //把bitmap转成byte[]的方法
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);

        return baos.toByteArray();
    }
}
