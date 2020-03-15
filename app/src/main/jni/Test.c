//
// Created by Administrator on 2020/1/13 0013.
//E:\android\androidBook\StudyVedio\app\src\main\jni\com_example_studyvedio_MyJni.h

#include "jni.h"
#include "com_example_studyvedio_MyJni.h"

JNIEXPORT jstring JNICALL Java_com_example_studyvedio_MyJni_getString
  (JNIEnv *env, jclass jz){

  return (*env)->NewStringUTF(env,"this is the first time for me to use jni");

  }
