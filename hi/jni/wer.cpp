#include <jni.h>
#include <math.h>
#include <wer.h>
#include<string.h>
/**
 *  int
 */
int add (int x,int y){
	return x+y;
}

/**
 * int *  类型
 */
int getAge(int *age){
	*age=18;
	return 0;
}


/**
 *  char * 类型
 */
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



/**
 * 结构体类型
 */

int getStructInfo(struct _Person *  person){

	person->age=18;
	char name[10] ="大包子";
	memcpy(person->name, name, strlen(name));
	return 0;
}








