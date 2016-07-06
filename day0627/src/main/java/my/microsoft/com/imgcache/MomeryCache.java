package my.microsoft.com.imgcache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by czw on 2016/6/27  16:54.
 */
public class MomeryCache {
    //key  代表当前图片url地址  value  当前图片的bitmap
    private LruCache<String,Bitmap> lruCache;
    public MomeryCache() {
        //获得缓存大小
        //当前app能够占用的内存大小
        int maxMomery = (int) Runtime.getRuntime().maxMemory();//安卓4.0以前每个app运行时,最多占用32M空间
        //把总内存的1/8做为缓存内存
        int cacheMomery = maxMomery / 8;
        lruCache=new LruCache<String,Bitmap>(cacheMomery){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //得出当前每张图片的缓存大小
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
    }
    //把bitmap放到缓存lruCache中的方法
    //synchronized  同步,就是让bitmap一个一个的加,避免出现两个同时运行,造成错误
    public synchronized void addBitmapFromLruCache(String key,Bitmap bitmap){
        if (key!=null){
            if (bitmap!=null){
                lruCache.put(key,bitmap);
            }
        }
    }
    //从缓存中拿图片的方法
    public Bitmap getBitmapFromLruCache(String key){
        if (key!=null){
            if (lruCache.get(key)!=null){
                return lruCache.get(key);
            }
        }
        return null;
    }
    //删除缓存里的bitmap
    public synchronized void removeBitmapLruCache(String key){

        if (key!=null){
            Bitmap bitmap=lruCache.get(key);
            if (bitmap!=null){
                bitmap.recycle();//recycle调用GC回收
            }
        }
    }
    //清空lruCache的方法
    public void clear(){
        if (lruCache.size()>0){
            lruCache.evictAll();
        }
        lruCache=null;
    }


}
