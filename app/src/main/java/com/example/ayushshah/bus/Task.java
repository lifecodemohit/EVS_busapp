package com.example.ayushshah.bus;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

class Task extends AsyncTask<String,String,String> {


    @Override
    protected String doInBackground(String... params) {

        JSONObject jsonobj = new JSONObject();
        try {
            String bus_no = params[0];
            String latitude = params[1];
            String longitude = params[2];
            jsonobj.put("bus_no",bus_no);
            jsonobj.put("latitude",latitude);
            jsonobj.put("longitude",longitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httppostreq = new HttpPost("http://192.168.53.36:8080/user");
        //HttpPost httppostreq = new HttpPost("http://192.168.53.36:8080/src");
        try {
            StringEntity se = new StringEntity(jsonobj.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
            httppostreq.setEntity(se);
            HttpResponse httpresponse = httpclient.execute(httppostreq);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Updated on Server";
    }
}
