package com.zhangqie.sokect.demo2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.zhangqie.sokect.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by zhangqie on 2018/4/20.
 */

public class Demo2Activity extends AppCompatActivity {

    static TextView textView;
    Socket socket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo2);
        initView();
    }


    private void initView(){
        textView = (TextView) findViewById(R.id.txt_1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("192.168.1.101", 30000);
                    // socket.setSoTimeout(10000);//设置10秒超时
                    Log.i("Android", "与服务器建立连接：" + socket);
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line = br.readLine();
                    Log.i("Android", "与服务器建立连接：" + line);
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = line;
                    handler.sendMessage(msg);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                textView.setText("这是来自服务器的数据:"+msg.obj.toString());
            }
        }
    };

}
