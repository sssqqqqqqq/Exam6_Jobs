package com.example.lpz_6.Render;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.lpz_6.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditInit {

    public static void setEnable(EditText editText, EditText editText1, EditText editText2, TextInputLayout textInputLayout, MaterialButton button){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(editText.getText()) ||
                        TextUtils.isEmpty(editText1.getText())||
                        TextUtils.isEmpty(editText2.getText())||!checkEmail(editText2.getText().toString())) {
                    button.setEnabled(Boolean.FALSE);
                    button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E6E6FA")));

                } else {
                    button.setEnabled(Boolean.TRUE);
                    button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6200EE")));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(editText.getText()) ||
                        TextUtils.isEmpty(editText1.getText())||
                        TextUtils.isEmpty(editText2.getText())||!checkEmail(editText2.getText().toString())) {
                    button.setEnabled(Boolean.FALSE);
                    button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E6E6FA")));
                } else {
                    button.setEnabled(Boolean.TRUE);
                    button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6200EE")));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(editText.getText()) ||
                        TextUtils.isEmpty(editText1.getText())||
                        TextUtils.isEmpty(editText2.getText())||!checkEmail(editText2.getText().toString())) {
                    button.setEnabled(Boolean.FALSE);
                    button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E6E6FA")));
                } else {
                    button.setEnabled(Boolean.TRUE);
                    button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6200EE")));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //如果不包含@认为是非法邮箱地址
                if (!checkEmail(s.toString())) {
                    //允许TextInputLayout显示错误信息
                    textInputLayout.setErrorEnabled(true);
                    //设置错误信息
                    textInputLayout.setError("邮箱格式不正确");
                } else {
                    textInputLayout.setErrorEnabled(false);
                }
            }


        });
    }
    public static void setEnable(EditText editText, EditText editText1, MaterialButton button){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(editText.getText()) ||
                        TextUtils.isEmpty(editText1.getText())
                        ) {
                    button.setEnabled(Boolean.FALSE);
                    button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E6E6FA")));

                } else {
                    button.setEnabled(Boolean.TRUE);
                    button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6200EE")));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(editText.getText()) ||
                        TextUtils.isEmpty(editText1.getText())
                ) {
                    button.setEnabled(Boolean.FALSE);
                    button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E6E6FA")));

                } else {
                    button.setEnabled(Boolean.TRUE);
                    button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6200EE")));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
    }

    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
