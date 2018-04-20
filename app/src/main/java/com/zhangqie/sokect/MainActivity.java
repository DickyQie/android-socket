package com.zhangqie.sokect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhangqie.sokect.demo1.Demo1Activity;
import com.zhangqie.sokect.demo2.Demo2Activity;
import com.zhangqie.sokect.demo3.Demo3Activity;


/***
 * https://blog.csdn.net/lovoo/article/details/51764824
 * https://blog.csdn.net/wanxuedong/article/details/54865288
 * https://blog.csdn.net/qq_17007915/article/details/77980633
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();

    }

    private void initView(){
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, Demo1Activity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(MainActivity.this, Demo2Activity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(MainActivity.this, Demo3Activity.class));
                break;
            case R.id.button4:
                break;
        }
    }

}
