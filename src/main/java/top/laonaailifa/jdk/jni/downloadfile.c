#include "pthread.h"
#include "stdio.h"
#include "stdlib.h"
#include "unistd.h"
#include "top_laonaailifa_jdk_java_thread_YuThread.h"

#define NUM_OF_TASKS 5

void *downloadfile(void *filename)
{
    printf("I an downloading file %s\n", (char *)filename);
    sleep(10);
    long downloadtime = rand() % 100;
    printf("I finish downloading the file wtihin %d minutes!\n", downloadtime);
    pthread_exit((void *)downloadtime);
}

JNIEXPORT void JNICALL Java_top_laonaailifa_jdk_java_thread_YuThread_start0(JNIEnv * env, jobject c1)
{
    char *files[NUM_OF_TASKS] = {"file1", "file2", "file3", "file4", "file5"};
    pthread_t threads[NUM_OF_TASKS];
    int rc;
    int downloadtime;
    pthread_attr_t thread_attr;
    pthread_attr_init(&thread_attr);
    pthread_attr_setdetachstate(&thread_attr, PTHREAD_CREATE_JOINABLE);

    for (int i = 0; i < NUM_OF_TASKS; i++)
    {
        printf("create thread %d \n", i);
        rc = pthread_create(&threads[i], &thread_attr, downloadfile, (void *)files[i]);
        if (rc)
        {
            printf("create thread error, code : %d \n", rc);
            exit(-1);
        }
    }

    pthread_attr_destroy(&thread_attr);

    for (int i = 0; i < NUM_OF_TASKS; i++)
    {
        pthread_join(threads[i], (void **)&downloadtime);
        printf("Thread %d download the file %s in %d munites.\n", i, files[i], downloadtime);
    }

    pthread_exit(NULL);
}


