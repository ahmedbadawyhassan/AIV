package com.anonymousshadow

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class BlindAidService(context: Context) : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech = TextToSpeech(context, this)

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.US
        }
    }

    fun speak(description: String) {
        tts.speak(description, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    fun shutdown() {
        tts.stop()
        tts.shutdown()
    }
}
