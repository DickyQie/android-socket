package com.zhangqie.sokect.demo4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhangqie.sokect.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by zhangqie on 2018/4/18.
 *
 * https://blog.csdn.net/lovoo/article/details/51764824
 * https://blog.csdn.net/hello_word2/article/details/66975779
 * https://blog.csdn.net/guang_liang_/article/details/52853134
 * https://www.jianshu.com/p/a04e131aac7b
 * https://www.cnblogs.com/zhujiabin/p/6252903.html
 * https://blog.csdn.net/vnanyesheshou/article/details/74896575
 */

public class Demo4Activity extends AppCompatActivity implements View.OnClickListener{


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
        new Thread() {

            @Override
            public void run() {

                try {
                    socket = new Socket("192.168.1.101", 30000);
                    //socket.setSoTimeout(10000);//设置10秒超时
                    Log.i("Android", "与服务器建立连接：" + socket);
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line = br.readLine();
                    Log.i("Android", "与服务器建立连接：" + line);
                    Toast.makeText(getApplicationContext(),"这是来自服务器的数据"+line,Toast.LENGTH_LONG).show();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /**
     * 建立服务端连接
     */
    public void conn() {
        new Thread(){
            public void run() {
                //获得客户端发来的数据
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while(true){
                        System.out.println("Receive from server : " + reader.readLine());
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            };
        }.start();

    }

    /**
     * 发送消息
     */
    public void send() {
        //回复服务端
        new Thread(){
            public void run() {
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    String readline = in.readLine();
                    System.out.println(readline);
                    out.println(readline);
                    System.out.println(" client send: " + readline);
                    readline = in.readLine();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            };
        }.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                //conn();

                break;
            case R.id.button2:
                //send();


                break;
        }
    }




}
