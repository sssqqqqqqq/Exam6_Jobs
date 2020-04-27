package com.example.lpz_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lpz_6.Render.EditInit;
import com.example.lpz_6.bean.User;
import com.example.lpz_6.config.Config;
import com.example.lpz_6.util.GetPostUtil;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    class MyHandler extends Handler
    {
        private WeakReference<MainActivity> mainActivity;
        MyHandler(WeakReference<MainActivity> mainActivity)
        {
            this.mainActivity = mainActivity;
        }

        @Override
        public void handleMessage(Message msg)
        {

            Toast.makeText(getApplicationContext(), msg.obj.toString(),
                    Toast.LENGTH_LONG).show();

            if (msg.what == 0x123)
            {
                EditText name = findViewById(R.id.login_userId);
                EditText password = findViewById(R.id.login_password);
                password.setText("");

            }else if(msg.what == 0x324){
                Intent intent = new Intent(MainActivity.this,NewsActivity.class);
                startActivity(intent);
            }
        }
    }
    private Handler handler =  new MyHandler(new WeakReference<>(this));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MaterialButton button = findViewById(R.id.register_in_button);
        button.setOnClickListener(view -> goToRegister());
        MaterialButton login = findViewById(R.id.sign_in_button);
        EditText name = findViewById(R.id.login_userId);
        EditText password = findViewById(R.id.login_password);
        login.setEnabled(Boolean.FALSE);
        EditInit.setEnable(name,password,login);
        login.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E6E6FA")));
        login.setOnClickListener(view -> new Thread(() ->
        {

            User user = new User(name.getText().toString(),password.getText().toString(),null);
            System.out.println(user);
            String str = GetPostUtil.sendPost(
                    Config.BASE_URL+"user/login?"+user.getLogin(), null
            );

            try {
                JSONObject jsonObject = new JSONObject(str).getJSONObject("data").getJSONObject("value");
                System.out.println(jsonObject.toString());
                Message msg = new Message();

                if(!jsonObject.get("type").toString().equals("0")) {
                    msg.what = 0x123;
                    msg.obj = "用户名或密码不正确";
                }else{
                    msg.what = 0x324;
                    msg.obj = "登录成功";
                }
                handler.sendMessage(msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }).start());
    }

    public void goToRegister(){
        Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);
    }



}
