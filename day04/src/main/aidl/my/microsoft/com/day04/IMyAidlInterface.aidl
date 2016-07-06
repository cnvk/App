// IMyAidlInterface.aidl
package my.microsoft.com.day04;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
//基本数据类型
   int add(int a,int b);
   //集合
   List<String> getData(in List<String> list);
   //自定义对象
   //Person getPerson(in Person p);
}
