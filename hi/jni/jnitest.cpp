


#include <stdio.h>

#include "jni.h"

#include <wer.h>
#include<jnitest.h>


JNIEXPORT jint  JNICALL Java_jnitools_JniTools_Add(JNIEnv *je, jclass jc, jint x,jint y)
{
	return  add(x,y);


}


 JNIEXPORT jint JNICALL Java_com_example_hi_MainActivity_addtest(JNIEnv *env, jobject instance ,jint x,jint y) {

	return add(x,y);

}

