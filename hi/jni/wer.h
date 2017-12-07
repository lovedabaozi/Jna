

#ifndef WER_H_
#define WER_H_



#ifdef __cplusplus
extern "C" {
#endif
struct  _Person
{
	int age;
	char name[11];
	char type[20];

}Person;


int add(int x,int y);
int getAge(int *age);

int getName(char *name,char * s);


int getStructInfo(struct _Person *Person);

#ifdef __cplusplus
};
#endif






#endif /* WER_H_ */
