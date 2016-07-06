package my.microsoft.com.day0627;

/**
 * 接口回调
 * Created by czw on 2016/6/27  15:05.
 */
public class A {
    public void loadFile(String str, final Callback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str="aaa";
                callback.getresult(str);
            }
        }).start();

    }
    public interface Callback{
        public void getresult(String str);

    }

}
