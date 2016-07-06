// IMyAidlInterface.aidl
package com.cblue.processservice;
import com.cblue.processservice.Person;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
    //加法
    int add(int a,int b);
    //返回集合
    List<String> getData(in List<String> list);
    //返回一个自定义对象
    Person getPerson(in Person p);

}
