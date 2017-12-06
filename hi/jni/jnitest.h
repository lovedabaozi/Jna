
#ifndef JNITEST_H_
#define JNITEST_H_



#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint  JNICALL Java_jnitools_JniTools_Add(JNIEnv *je, jclass jc, jint x,jint y);

JNIEXPORT jint JNICALL Java_com_example_hi_MainActivity_addtest(JNIEnv *env, jobject instance ,jint x,jint y);

#ifdef __cplusplus
};
#endif






#endif
