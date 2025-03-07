package com.example.webrtc

class NativeLib {
    /**
     * A native method that is implemented by the 'webrtc' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'webrtc' library on application startup.
        init {
            System.loadLibrary("webrtc")
        }
    }
}
