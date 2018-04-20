package com.zhangqie.sokect.demo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zhangqie.sokect.R;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by zhangqie on 2018/4/18.
 */

public class Demo1Activity extends AppCompatActivity implements View.OnClickListener{


    private Socket socket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo1);

        initView();

    }

    private void initView(){
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }


    /**
     * 建立服务端连接
     */
    public void conn() {
        new Thread() {

            @Override
            public void run() {

                try {
                    socket = new Socket("192.168.1.101", 9999);
                    Log.i("Android", "与服务器建立连接：" + socket);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 发送消息
     */
    public void send() {
        new Thread() {
            @Override
            public void run() {

                try {
                    // socket.getInputStream()
                    DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
                    writer.writeUTF("嘿嘿，你好啊，服务器.."); // 写一个UTF-8的信息

                    System.out.println("发送消息");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                conn();
                break;
            case R.id.button2:
                send();
                break;
        }
    }
}
