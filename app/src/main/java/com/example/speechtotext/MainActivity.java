package com.example.speechtotext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView t1,t2;
    private final int searchinput=100;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==searchinput)
        {
                if(resultCode==RESULT_OK && null!= data)

            {
                ArrayList<String> result =data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                t2.setText(result.get(0));
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=findViewById(R.id.btnspeak);
        t2=findViewById(R.id.voiceinput);



        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               //   Implicite Intent Called here
                // action_recognize_speech is used to trigger speech recogniser
                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                //by using putExtra method used to send value from this intent to another
                //  EXTRA_LANGUAGE_MODEL it have its own dictionary it is searched for matching result in dictionary , dictionary contains whatever previously serched by user in mobile
                // LANGUAGE_MODEL_FREE_FORM  is used to say intent that don't consider any language , just serch and show the matching results
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                //it is consider any language
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi Speak Something");

                startActivityForResult(intent, searchinput);



            }
        });


    }
}
