package com.example.ttsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button speakNowButton;
    private Button speakAddButton;
    private Button speakStopButton;
    private Button speakIsPlayButton;
    private Button speakSwitchButton;
    private EditText editText;
    private TTSManager ttsManager = null;

    @Override
    protected void onDestroy() {
        ttsManager.shutDown();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize TTSManager */
        ttsManager = new TTSManager();
        ttsManager.init(this);

        editText = (EditText) findViewById(R.id.input_text);
        speakNowButton = (Button) findViewById(R.id.speak_now);
        speakAddButton = (Button) findViewById(R.id.speak_add);
        speakStopButton = (Button) findViewById(R.id.speak_stop);
        speakIsPlayButton = (Button) findViewById(R.id.speak_isplay);
        speakSwitchButton = (Button) findViewById(R.id.speak_switch);

        speakNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String text = editText.getText().toString();
                ttsManager.initQueue(text);
            }
        });

        speakAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String text = editText.getText().toString();
                ttsManager.addQueue(text);
            }
        });


        speakStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ttsManager.onStop();
            }
        });

        speakIsPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                boolean res = ttsManager.isSpeaking();
                editText.setText(Boolean.toString(res));
            }
        });

        speakSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ttsManager.switchLanguage();
            }
        });



    }



}