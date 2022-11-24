package com.example.ttsapplication;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import java.util.Locale;

public class TTSManager {

    private TextToSpeech mTts = null;
    private boolean isLoaded = false;

    public void init(Context context) {
        try {
            mTts = new TextToSpeech(context, i -> {
                //系统语音初始化成功
                Log.e("TTS status",Integer.toString(i));
                if (i == TextToSpeech.SUCCESS) {
                    int result = mTts.setLanguage(Locale.CHINA);
                    mTts.setPitch(0.5f);// Set the tone, the higher the value, the sharper the voice (female), the smaller the value, it becomes a male voice. 1.0 is routine.
                    mTts.setSpeechRate(1.0f);
                    isLoaded = true;
//                    mTts.setOnUtteranceProgressListener(TTSUtils.this);
//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                        //系统不支持中文播报
//                        isSuccess = false;
//                    }
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void shutDown() {
        mTts.shutdown();
    }

    public void addQueue(String text) {
        if (isLoaded)
            mTts.speak(text, TextToSpeech.QUEUE_ADD, null);
        else
            Log.e("TTS error", "TTS Not Initialized");
    }

    public void initQueue(String text) {

        if (isLoaded)
            mTts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        else
            Log.e("TTS error", "TTS Not Initialized");
    }
}
