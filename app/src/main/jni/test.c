//
// Created by mozzie on 2019/4/22.
//

#include "jni.h"
#include "com_example_mozzie_mozlearning_agjni_MyJniTest.h"

JNIEXPORT jstring JNICALL Java_com_example_mozzie_mozlearning_agjni_MyJniTest_getString
  (JNIEnv *env, jclass jz){

  return (*env)->NewStringUTF(env,"this is the first time for me to use jni");

 }



