#include "stdio.h"
#include "top_laonaailifa_jdk_java_thread_YuThread.h"


JNIEXPORT void JNICALL Java_top_laonaailifa_jdk_java_thread_YuThread_callback(JNIEnv * env, jobject cl){
    jclass cls;
//    jobject obj;
    jmethodID cid;
    jmethodID rid;
    jint ret = 0;
    cls = (*env)->FindClass(env, "top/laonaailifa/jdk/java/thread/YuThread");
    if(cls == NULL){
        printf("FindClass error \n");
        return;
    }
    cid = (*env) ->GetMethodID(env,cls,"<init>","()V");
    if(cid == NULL){
        printf("query constructor error \n");
        return;
    }
//    obj = (*env) ->NewObject(env, cls, cid);
//    if(obj == NULL){
//        printf("NewObject error \n");
//        return;
//    }
    rid = (*env) ->GetMethodID(env,cls,"run","()V");
    ret = (*env) -> CallIntMethod(env,cl,rid,NULL);
    printf("finish call method");
}

