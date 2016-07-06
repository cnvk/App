package com.cblue.callback;

/**
 * Created by pavel on 2016/6/27.
 */
public class A {

    public void loadFile(String url,final  CallBack callBack){

        new Thread(new Runnable() {
            @Override
            public void run() {
               //下载文件
                String str = "aaa";
                callBack.getResult(str);

            }
        }).start();
    }

    public interface CallBack{
        public void getResult(String str);
    }
}
