package com.example.yangzijiang.fragment.simplefragment;

import com.example.yangzijiang.activitytest.R;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*第一行代码4.2节，碎片的使用方式*/
public class SimpleFragmentActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_fragment);
        replaceFragment(new RightFragment());

        Button button = (Button) findViewById(R.id.left_fragment_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_fragment_button:
                replaceFragment(new AnotherRightFragment());
                break;
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null);  // 在碎片中模拟返回栈，这样点击返回按钮不会直接退出，而是返回到上一个碎片
        transaction.replace(R.id.framelayout_right, fragment);  // 碎片替换

        transaction.commit();
    }
}
