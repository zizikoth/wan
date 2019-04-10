@file:Suppress("DEPRECATION")
package com.memo.iframe.tools.utils

import android.hardware.Camera
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder

/**
 * title: 安卓6.0一下获取了权限 但是有系统软件拦截 导致不能录制音视频造成的问题
 * tip:
 *
 * @author zhou
 * @date 2018-09-12 上午11:05
 */
object MediaHelper {

    /**
     * 判断是否录音可以使用
     *
     * @return 是否
     *
     * 开始录制音频
     * 防止某些手机崩溃，例如联想
     * 根据开始录音判断是否有录音权限
     */
    @JvmStatic
    val isAudioUsable: Boolean
        get() {
            val audioFormat = AudioFormat.ENCODING_PCM_16BIT
            val channelConfig = AudioFormat.CHANNEL_IN_STEREO
            val sampleRateInHz = 44100
            val bufferSizeInBytes = AudioRecord.getMinBufferSize(
                sampleRateInHz,
                channelConfig, audioFormat
            )
            val audioSource = MediaRecorder.AudioSource.MIC
            val audioRecord: AudioRecord = AudioRecord(
                audioSource, sampleRateInHz,
                channelConfig, audioFormat, bufferSizeInBytes
            )
            try {
                audioRecord.startRecording()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
                return false
            }
            if (audioRecord.recordingState != AudioRecord.RECORDSTATE_RECORDING) {
                return false
            }
            audioRecord.stop()
            audioRecord.release()
            return true
        }

    /**
     * 判断是否摄像头可以使用
     *
     * @return 是否可以使用
     * setParameters 是针对魅族MX5 做的。MX5 通过Camera.open() 拿到的Camera
     */
    @JvmStatic
    val isCameraUsable: Boolean
        get() {
            var canUse = true
            var mCamera: Camera? = null
            try {
                mCamera = Camera.open()
                val mParameters = mCamera!!.parameters
                mCamera.parameters = mParameters
            } catch (e: Exception) {
                canUse = false
            }

            mCamera?.release()
            return canUse
        }


}
