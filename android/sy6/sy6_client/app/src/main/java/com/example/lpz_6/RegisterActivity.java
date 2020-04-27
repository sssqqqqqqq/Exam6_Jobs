package com.example.lpz_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lpz_6.Render.EditInit;
import com.example.lpz_6.bean.User;
import com.example.lpz_6.config.Config;
import com.example.lpz_6.util.GetPostUtil;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    class MyHandler extends Handler
    {
        private WeakReference<RegisterActivity> mainActivity;
        MyHandler(WeakReference<RegisterActivity> mainActivity)
        {
            this.mainActivity = mainActivity;
        }

        @Override
        public void handleMessage(Message msg)
        {

            EditText editpassword = findViewById(R.id.register_password);
            Toast.makeText(getApplicationContext(), msg.obj.toString(),
                    Toast.LENGTH_LONG).show();
            if (msg.what == 0x123)
            {

                editpassword.setText("");


            }else if(msg.what == 0x324){
                finish();
            }
        }
    }
    private Handler handler = new MyHandler(new WeakReference<>(this));
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final MaterialButton register = findViewById(R.id.register_in_button2);
        register.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E6E6FA")));
        TextInputLayout textInputLayout = findViewById(R.id.email);
        EditText editname = findViewById(R.id.register_userId);
        EditText editpassword = findViewById(R.id.register_password);
        EditText editemail = findViewById(R.id.register_email);
        register.setEnabled(Boolean.FALSE);
        EditInit.setEnable(editname,editpassword,editemail,textInputLayout,register);


        register.setOnClickListener(view -> new Thread(() ->
        {

            User user = new User(editname.getText().toString(),editpassword.getText().toString(),editemail.getText().toString());
            System.out.println(user);
            String str = GetPostUtil.sendPost(
                    Config.BASE_URL+"user/register?"+user.toString(), null
            );

            try {
                JSONObject jsonObject = new JSONObject(str).getJSONObject("data").getJSONObject("value");
                System.out.println(jsonObject.toString());
                Message msg = new Message();

                if(!jsonObject.get("type").toString().equals("0")) {
                    msg.what = 0x123;
                    msg.obj = jsonObject.get("message");
                }else{
                    msg.what = 0x324;
                    msg.obj = jsonObject.get("message");
                }
                handler.sendMessage(msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }).start());
    }




}
