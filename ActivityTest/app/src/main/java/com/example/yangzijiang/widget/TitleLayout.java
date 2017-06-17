package com.example.yangzijiang.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yangzijiang.activitytest.R;

/**
 * Created by yzj on 5/28/2017.
 */

public class TitleLayout extends LinearLayout implements View.OnClickListener{

    public TitleLayout(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.title, this);

        Button backButton = (Button) findViewById(R.id.title_back);
        backButton.setOnClickListener(this);

        Button editButton = (Button) findViewById(R.id.title_edit);
        editButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                Toast.makeText(getContext(), "back", Toast.LENGTH_SHORT).show();
                break;
            case R.id.title_edit:
                Toast.makeText(getContext(), "edit", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
