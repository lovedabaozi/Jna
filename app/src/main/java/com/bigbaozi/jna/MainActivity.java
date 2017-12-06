package com.bigbaozi.jna;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigbaozi.jna.api.JnaTools;
import com.sun.jna.ptr.PointerByReference;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import jnitools.JniTools;

/**
 * Name: MainActivity
 * Author: dabaozi
 * Email:
 * Comment: //TODO
 * Date: 2017-11-09 16:29
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_add;
    private  Button btn_handsome;
    private Button btn_struct;
    private EditText et_num1;
    private EditText et_num2;
    private EditText et_name;
    private TextView tv_result;
    private  Button btn_intp;
    static {

        System.loadLibrary("wer");
        System.loadLibrary("jnidispatch");
    }
    private static PointerByReference hCard = new PointerByReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        TextView tv = (TextView) findViewById(R.id.tv);
       tv.setText("jni调用ADD方法："+JniTools.Add(3,8));
    }

    private void initView() {

        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_handsome = (Button) findViewById(R.id.btn_handsome);
        btn_handsome.setOnClickListener(this);
        btn_struct = (Button) findViewById(R.id.btn_struct);
        btn_struct.setOnClickListener(this);

        btn_intp = (Button) findViewById(R.id.btn_intp);
        btn_intp.setOnClickListener(this);
        et_num1 = (EditText) findViewById(R.id.et_num1);

        et_num2 = (EditText) findViewById(R.id.et_num2);

        et_name = (EditText) findViewById(R.id.et_name);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                if(TextUtils.isEmpty(et_num1.getText().toString())||TextUtils.isEmpty(et_num2.getText().toString())){
                    Toast.makeText(MainActivity.this,"数字不能为空" ,Toast.LENGTH_SHORT).show();
                    return;
                }
               int num1= Integer.parseInt(et_num1.getText().toString());
                int num2= Integer.parseInt(et_num2.getText().toString());
                tv_result.setText("所得结果为："+JnaTools.INSTANCE.add(num1,num2));
                break;
            case R.id.btn_handsome:
                String s = et_name.getText().toString();
                byte [] bytename=new byte[50];
                ByteBuffer call=ByteBuffer.wrap(bytename);
                ByteBuffer name=ByteBuffer.wrap(new String(s).getBytes());
                int re = JnaTools.INSTANCE.getName(name, call);
                tv_result.setText(new String(call.array()));
                break;
            case R.id.btn_struct:
                JnaTools._Person person=new JnaTools._Person();
                JnaTools.INSTANCE.getStructInfo(person);
                tv_result.setText("name="+new String(person.name)+"age="+person.age);
                break;
            case R.id.btn_intp:
                IntBuffer retry = IntBuffer.allocate(1);
                JnaTools.INSTANCE.getAge(retry);
                tv_result.setText("大包子的年龄永远为："+retry.array()[0]+"岁");
                break;
        }
    }
}
