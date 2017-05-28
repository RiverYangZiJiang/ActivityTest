package com.example.yangzijiang.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.yangzijiang.activitytest.R;

public class TextViewActivity extends AppCompatActivity {
    private static final String TAG = "TextViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        EditText editText = (EditText) findViewById(R.id.editText2);
        String inputText = editText.getText().toString();
        Log.d(TAG, "onCreate: ");
    }
}
