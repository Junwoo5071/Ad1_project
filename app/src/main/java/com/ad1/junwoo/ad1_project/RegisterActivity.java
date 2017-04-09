package com.ad1.junwoo.ad1_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by junwoo on 2017. 4. 9..
 */

public class RegisterActivity extends Activity {

    public int view_id = 0;
    public String symbolvalue;

    private Login_DB_Manager login_db_manager;
    private ArrayList<String> results;

    private EditText et_email;
    private EditText et_passWord;
    private EditText et_passWord2;
    private EditText et_name;
    private EditText et_phone;
    private RadioGroup rd_sick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_main);

        et_email = (EditText)findViewById(R.id.reg_email_val);
        et_passWord = (EditText)findViewById(R.id.reg_pw_val);
        et_passWord2 = (EditText)findViewById(R.id.reg_pw_2_val);
        et_name = (EditText)findViewById(R.id.reg_name_val);
        et_phone = (EditText)findViewById(R.id.reg_tel_val);
        rd_sick = (RadioGroup)findViewById(R.id.radioGroup);

        login_db_manager = new Login_DB_Manager();

        results = new ArrayList<String>();
    }


    public void registerfinishonClick(View view){
        String a = et_passWord.getText().toString();
        String b = et_passWord2.getText().toString();

        switch (view.getId()) {
            case R.id.reg_email_val:
                //popup(R.id.reg_email_val,"이메일입력");
                break;
            case R.id.reg_name_val:
                //popup(R.id.reg_name_val,"이름 입력");
                break;
            case R.id.reg_tel_val:
                TelephonyManager systemSerivce = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
                String PhoneNumber = systemSerivce.getLine1Number();
                PhoneNumber = PhoneNumber.substring(PhoneNumber.length() - 10, PhoneNumber.length());
                PhoneNumber = "0" + PhoneNumber;
                et_phone.setText(PhoneNumber);
                et_phone.setEnabled(false);
                //popup(reg_tel_val,"전화번호 입력");
                break;
            case R.id.reg_sick:
                //popup(R.id.reg_sick,"천식여부 확인");
                break;
            case R.id.reg_conform:
                //FragmentTransaction ft = getApplicationContext().get.getFragmentManager().beginTransaction();
                if (a.length() < 1)
                    Toast.makeText(getApplicationContext(), "비밀번호가 공백입니다.", Toast.LENGTH_SHORT).show();
                else if (!a.equals(b))
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                else {
                    String user_email = et_email.getText().toString();
                    String user_password = et_passWord.getText().toString();
                    String user_name = et_name.getText().toString();
                    String user_phone = et_phone.getText().toString();

                    login_db_manager.signup_user_information(user_email, user_password, user_name, user_phone, true);
                    Intent mainIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(mainIntent);
                }
                break;
        }

    }
}