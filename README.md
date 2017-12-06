# JNA的使用
## 使用背景
- 在安卓开发过程中，常常会需要和C，C++等交互，这时候我们就想起了JNI，但是JNI的使用过程十分繁琐，而且容易出现各种形形色色的问题，还得封装而且问题不好定位所以对调用大量的c程序造成困扰。相比之下JNA的简易程度令人发指，你只需要一个jar包，就可以像调用一个java方法一样简单。假如我们只有一个.so文件，我们使用JNI去调用，我们需要另外用C语音写一个.so的共享文件，并且得使用SUN规定的数据结构去替代C语言的数据结构，至此才能调用so文件里面公布的函数。作为搞JAVA的程序员这个过程是令人头疼的。

![对比](http://upload-images.jianshu.io/upload_images/2196112-f9b06c55de8ca0ff.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


> 如上图所示Jna省去了令Java程序员头疼的Jni中间键共享库的封装。官方言简意赅的描述：JNA provides Java programs easy access to native shared libraries without writing anything but Java code - no JNI or native code is required.

- 为了更加直观的体现Jna的优势，我编了一个简单的so库，分别用JNA和Jni来调用Native方法。
Native 方法和头文件内容如下：
![2.png](http://upload-images.jianshu.io/upload_images/2196112-94c738e3cb319e31.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![3.png](http://upload-images.jianshu.io/upload_images/2196112-abe2ed86b689f8b7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

编译得到wer.so里面有add方法。

- 现在我们使用Jni方式去调用wer里面的add方法。
- 先去封装jni调用的中间键，代码如如图：
![4.png](http://upload-images.jianshu.io/upload_images/2196112-2260dbd20a69c5df.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![5.png](http://upload-images.jianshu.io/upload_images/2196112-bca91d1d3b142bf5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![6.png](http://upload-images.jianshu.io/upload_images/2196112-58ab604e7a753dd5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>在编写jni中间键的时候还要注意头文件的引用，方法的全路径，Android.mk的配置，稍有不慎在调用的时候就会引起喜闻乐见的崩溃。

- 在项目中调用封装好的库，如下图：
![7.png](http://upload-images.jianshu.io/upload_images/2196112-b2dc27e88178b844.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
>注意这块的方法全路径必须和native里面的一致。Jni必须这样，然后jna就可以不这样。

### Jna的调用
- 引入jna的jar包和对应的jnidispatch.so.然后编写jnatools 如下：
  ![8.png](http://upload-images.jianshu.io/upload_images/2196112-381ff0b3f91d5c95.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>仅仅这么一步就搞定了，激动不！！！

## JNA简介

- JNA（Java Native Access ）提供一组Java工具类用于在运行期动态访问系统本地库（native library：如Window的dll，Android的so）而不需要编写任何Native/JNI代码。开发人员只要在一个Java接口中描述目标native library的函数与结构，JNA将自动实现Java接口到native function的映射。
- 在github上的传送门---[JNA](https://github.com/java-native-access/jna)
- 对应的数据类型如下图：
![type.png](http://upload-images.jianshu.io/upload_images/2196112-a05945e667afcdd9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


## JNA的使用




- 在github上下载jna.jar和对应平台的 libjnidispatch.so ，并在项目中配置好。
>[懒人传送门CSDN下载](http://download.csdn.net/download/qq_30247473/10147314)。[也可以clone我的demo里面有项目源码](https://github.com/HiBigBuns/Jna)

- 根据Native的头文件愉快的编写Java代码。如下
'神挡杀神所所所所所所所所所所所所所所所所所所所所所所'
- 一路哼着小歌就把Native方法调起来了，再也不用考虑jni的路径问题，可以在任意路径下折腾啦。
运行结果如下：


- **jna中 一些常用数据类型的简单示例。**

- char *

c代码实现

```
int getName(char *name,char * s) {
	if(name==NULL||s==NULL){
		return -1;
	}
	int result=strcmp(name, "大包子");
	char  dabaozi[40] ="大包子最帅！";
	char other [40] = "其他人一般帅。。。";
		if (result == 0) {

		memcpy(s, dabaozi, strlen(dabaozi));
	}
	else {
		memcpy(s, other, strlen(other));
	}

	return 0;
}
```

java代码调用

 
```
 String s = et_name.getText().toString();
 byte [] bytename=new byte[50];
ByteBuffer call=ByteBuffer.wrap(bytename);
ByteBuffer name=ByteBuffer.wrap(new String(s).getBytes());
int re = JnaTools.INSTANCE.getName(name, call);
tv_result.setText(new String(call.array()));
```



- int *

c代码实现


```
int getAge(int *age){
	*age=18;
	return 0;
}
```

java代码


```
String s = et_name.getText().toString();
 byte [] bytename=new byte[50];
ByteBuffer call=ByteBuffer.wrap(bytename);
ByteBuffer name=ByteBuffer.wrap(new String(s).getBytes());
int re = JnaTools.INSTANCE.getName(name, call);
tv_result.setText(new String(call.array()));
```


- C结构体
c代码实现

```
int getStructInfo(struct _Person *  person){

	person->age=18;
	char name[10] ="大包子";
	memcpy(person->name, name, strlen(name));
	return 0;
}
```
java代码调用


```
JnaTools._Person person=new JnaTools._Person();
JnaTools.INSTANCE.getStructInfo(person);
tv_result.setText("name="+new String(person.name)+"age="+person.age);
```
- JnaTools代码

```
package com.bigbaozi.jna.api;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;

/**
 * Name: JnaTools
 * Author: dabaozi
 * Email:
 * Comment: //TODO
 * Date: 2017-11-09 14:04
 */
public interface JnaTools extends Library {

        public static final String JNA_LIBRARY_NAME = "wer";
        public static final JnaTools INSTANCE = (JnaTools) Native.loadLibrary(JnaTools.JNA_LIBRARY_NAME, JnaTools.class);
        public static class _Person extends Structure {
        public int age;
        public byte[] name = new byte[11];
        public byte[] type = new byte[20];
        public _Person() {
            super();
        }
        protected List<String> getFieldOrder() {
            return Arrays.asList("age", "name", "type");
        }
        public _Person(int age, byte name[], byte type[]) {
            super();
            this.age = age;
            if ((name.length != this.name.length))
                throw new IllegalArgumentException("Wrong array size !");
            this.name = name;
            if ((type.length != this.type.length))
                throw new IllegalArgumentException("Wrong array size !");
            this.type = type;
        }
        public static class ByReference extends _Person implements Structure.ByReference {

        };
        public static class ByValue extends _Person implements Structure.ByValue {

        };
    };
    int add(int x, int y);

    int getAge(IntBuffer age);

    int getName(ByteBuffer name, ByteBuffer s);
    int getStructInfo(JnaTools._Person Person);
}
```







**这时候有人会说每次去写JnaTools这个里面的数据类型转换我不想干，其实我也不想干，么么哒那么便成全你吧 高不高兴！！！**

![哈哈.jpg](http://upload-images.jianshu.io/upload_images/2196112-fc137b13647f9815.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>前方高能，放大招中。。。。

![jnatools.png](http://upload-images.jianshu.io/upload_images/2196112-34e18c62a5d8eb23.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 如上图所示将c的头文件复制到左边，配置好类名，然后点击JNAerate ，自动转换成我们所有的Java文件。





**写在最后，作为程序员的我保存住了程序员的优良传统（语言表达能力捉急）以上文章中如有不当之处，欢迎大家狠狠的批评指正**
































