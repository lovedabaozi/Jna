LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := wer
LOCAL_SRC_FILES := wer.cpp

include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := jnitest
LOCAL_SHARED_LIBRARIES := wer
LOCAL_SRC_FILES := jnitest.cpp 


include $(BUILD_SHARED_LIBRARY)