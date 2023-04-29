package com.example.speechtotext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.TextView
import java.util.*

// Enable user permission in manifests > AndroidManifest.xml
//    <uses-permission android:name="android.permission.INTERNET"/>
//    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
class MainActivity : AppCompatActivity() {
    lateinit var btMic:Button
    lateinit var tvText:TextView
    private val REQUEST_CODE_SPEECH_INPUT=100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btMic=findViewById(R.id.btMic)
        tvText=findViewById(R.id.tvText)

        btMic.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH) //speech recognition on the OS
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())//takes default language set in OS

            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hello speak...!")

            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //checking if you are getting proper inputs for the app
        if(requestCode==REQUEST_CODE_SPEECH_INPUT && resultCode==RESULT_OK && data!=null)
        {
            var res=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)//here all the information in contained in data variable and we use this line to extract that data\
            var text = res?.get(0) //to extract only text data
            tvText.text = text
        }
    }
}
