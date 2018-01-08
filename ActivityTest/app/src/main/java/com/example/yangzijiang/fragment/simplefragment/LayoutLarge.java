package com.example.yangzijiang.fragment.simplefragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.yangzijiang.activitytest.R;

/*第一行代码4.4节，动态加载布局的技巧*/
public class LayoutLarge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_large);
    }
}
