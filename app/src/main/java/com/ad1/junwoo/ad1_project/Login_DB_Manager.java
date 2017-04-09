package com.ad1.junwoo.ad1_project;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by junwoo on 2017. 4. 9..
 */



public class Login_DB_Manager {
    private String urlPath;

    private final String signup_user_information_UrlPath =
            "http://192.168.100.139/login.php";

    private String user_email;
    private String user_password;
    private String user_name;
    private String user_phone;
    private boolean user_sick;

    private ArrayList<String> results;

    public ArrayList<String> signup_user_information(String user_email, String user_password, String user_name, String user_phone, boolean user_sick){
        urlPath = signup_user_information_UrlPath;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_sick = user_sick;
        try {
            results = new SignupUserInformation().execute().get();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return results;
    }

    class SignupUserInformation extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Void... voids) {

            try {
                URL url = new URL(urlPath);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setUseCaches(false);
                con.setRequestMethod("POST");

                String param =
                        "user_email="+user_email+"&user_password="+user_password+"&user_name="+user_name+"&user_phone="+user_phone+"&user_sick="+user_sick;
                OutputStream outputStream = con.getOutputStream();
                outputStream.write(param.getBytes());
                outputStream.flush();
                outputStream.close();

                BufferedReader rd = null;
                rd = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
                String line = null;
                while ((line = rd.readLine()) != null){
                    Log.d("BufferReader:",line);
                }
            } catch (UnsupportedEncodingException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
        }
    }

}

