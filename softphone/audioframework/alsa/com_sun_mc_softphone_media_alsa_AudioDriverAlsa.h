/*
 * Copyright 2007 Sun Microsystems, Inc.
 *
 * This file is part of jVoiceBridge.
 *
 * jVoiceBridge is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU General Public License version 2 as 
 * published by the Free Software Foundation and distributed hereunder 
 * to you.
 *
 * jVoiceBridge is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Sun designates this particular file as subject to the "Classpath"
 * exception as provided by Sun in the License file that accompanied this 
 * code. 
 */

/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_sun_mc_softphone_media_alsa_AudioDriverAlsa */

#ifndef _Included_com_sun_mc_softphone_media_alsa_AudioDriverAlsa
#define _Included_com_sun_mc_softphone_media_alsa_AudioDriverAlsa
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_sun_mc_softphone_media_alsa_AudioDriverAlsa
 * Method:    nGetInputDevices
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_com_sun_mc_softphone_media_alsa_AudioDriverAlsa_nGetInputDevices
  (JNIEnv *, jobject);

/*
 * Class:     com_sun_mc_softphone_media_alsa_AudioDriverAlsa
 * Method:    nGetOutputDevices
 * Signature: ()[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_com_sun_mc_softphone_media_alsa_AudioDriverAlsa_nGetOutputDevices
  (JNIEnv *, jobject);

/*
 * Class:     com_sun_mc_softphone_media_alsa_AudioDriverAlsa
 * Method:    nInitializeMicrophone
 * Signature: (Ljava/lang/String;III)I
 */
JNIEXPORT jint JNICALL Java_com_sun_mc_softphone_media_alsa_AudioDriverAlsa_nInitializeMicrophone
  (JNIEnv *, jobject, jstring, jint, jint, jint);

/*
 * Class:     com_sun_mc_softphone_media_alsa_AudioDriverAlsa
 * Method:    nInitializeSpeaker
 * Signature: (Ljava/lang/String;III)I
 */
JNIEXPORT jint JNICALL Java_com_sun_mc_softphone_media_alsa_AudioDriverAlsa_nInitializeSpeaker
  (JNIEnv *, jobject, jstring, jint, jint, jint);

/*
 * Class:     com_sun_mc_softphone_media_alsa_AudioDriverAlsa
 * Method:    nStop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_sun_mc_softphone_media_alsa_AudioDriverAlsa_nStop
  (JNIEnv *, jobject);

/*
 * Class:     com_sun_mc_softphone_media_alsa_AudioDriverAlsa
 * Method:    nWriteSpeaker
 * Signature: ([SI)I
 */
JNIEXPORT jint JNICALL Java_com_sun_mc_softphone_media_alsa_AudioDriverAlsa_nWriteSpeaker
  (JNIEnv *, jobject, jshortArray, jint);

/*
 * Class:     com_sun_mc_softphone_media_alsa_AudioDriverAlsa
 * Method:    nReadMic
 * Signature: ([SI)I
 */
JNIEXPORT jint JNICALL Java_com_sun_mc_softphone_media_alsa_AudioDriverAlsa_nReadMic
  (JNIEnv *, jobject, jshortArray, jint);

/*
 * Class:     com_sun_mc_softphone_media_alsa_AudioDriverAlsa
 * Method:    nFlushMicrophone
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_sun_mc_softphone_media_alsa_AudioDriverAlsa_nFlushMicrophone
  (JNIEnv *, jobject);

/*
 * Class:     com_sun_mc_softphone_media_alsa_AudioDriverAlsa
 * Method:    nFlushSpeaker
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_sun_mc_softphone_media_alsa_AudioDriverAlsa_nFlushSpeaker
  (JNIEnv *, jobject);

/*
 * Class:     com_sun_mc_softphone_media_alsa_AudioDriverAlsa
 * Method:    nMicrophoneAvailable
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_sun_mc_softphone_media_alsa_AudioDriverAlsa_nMicrophoneAvailable
  (JNIEnv *, jobject);

/*
 * Class:     com_sun_mc_softphone_media_alsa_AudioDriverAlsa
 * Method:    nSpeakerAvailable
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_sun_mc_softphone_media_alsa_AudioDriverAlsa_nSpeakerAvailable
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif