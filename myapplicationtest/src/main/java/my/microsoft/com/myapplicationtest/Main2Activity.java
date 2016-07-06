package my.microsoft.com.myapplicationtest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import my.microsoft.com.myapplicationtest.utils.HttpUtils;

public class Main2Activity extends AppCompatActivity {
    private ListView lv;
    //sd卡路径
    private String sdpath= Environment.getExternalStorageDirectory().getAbsolutePath();
    //数据库文件名
    private String dbname="news.db";
    final SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(sdpath+ File.separator+dbname,null);
    List<HashMap<String,Object>> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lv= (ListView) this.findViewById(R.id.lv);
        data=selectTolist();
        SimpleAdapter adapter=new SimpleAdapter(this,data,R.layout.listviewitem,new String[]{"imgpath","title"},new int[]{R.id.item_iv,R.id.item_tv});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HashMap<String,Object> map=data.get(position);
                String itemid= (String) map.get("id");
                String title= (String) map.get("title");
                String imgpath= (String) map.get("imgpath");
                String shorttitle= (String) map.get("shorttitle");
                String description= (String) map.get("description");
                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                intent.putExtra("id",itemid);
                intent.putExtra("title",title);
                intent.putExtra("imgpath",imgpath);
                intent.putExtra("shorttitle",shorttitle);
                intent.putExtra("description",description);
                startActivity(intent);
            }
        });

    }

    //查询数据库,并转换成list集合的方法
    public List<HashMap<String,Object>> selectTolist(){

        final List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
        Cursor cursor= db.rawQuery("select * from news",null);
        Bitmap bitmap=null;
        while(cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndex("id"));
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String imgpath=cursor.getString(cursor.getColumnIndex("imgpath"));
            String shorttitle=cursor.getString(cursor.getColumnIndex("shorttitle"));
            String description=cursor.getString(cursor.getColumnIndex("description"));
            //下面这一部分是把得到的图片路径转化成bitmap,在用自定义适配器的时候可以使用这样的方式
            //本次使用的是SimpleAdapter,所以可以直接使用图片的SD卡路径填充
//            byte [] b= HttpUtils.request(imgpath);
//            if (b != null) {
//                bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
//
//            }
            HashMap<String,Object> map=new HashMap<String, Object>();
            map.put("imgpath",imgpath);
            map.put("id",id);
            map.put("title",title);
            map.put("shorttitle",shorttitle);
            map.put("description",description);
            //map.put(title,bitmap);
            list.add(map);
        }
        return list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在页面关闭时,清空图片和数据库,防止下次启动应用时,出错
        //得到SD卡存图片的路径/mnt/sdcard/donwnload
        File root= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
         deleteAllFiles(root);
        Log.i("aaa","已清空图片文件夹");
        //清空数据库表内的数据
        db.execSQL("delete from news");
        Log.i("aaa","已清空数据库");

    }

    //页面关闭时清空图片文件夹
    private void deleteAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null)
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹
                    deleteAllFiles(f);
                    try {
                        f.delete();
                    } catch (Exception e) {
                    }
                } else {
                    if (f.exists()) { // 判断是否存在
                        deleteAllFiles(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                        }
                    }
                }
            }
    }
}
