package com.example.ayushshah.bus;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by lifecodemohit on 4/5/15.
 */
public class getTask extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... params) {
        HttpResponse response = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI("http://192.168.53.36:8080/getData"));
            response = client.execute(request);
            return convertStreamToString(response.getEntity().getContent());
        } catch (URISyntaxException e) {
            Log.i("Hi","syntax");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            Log.i("Hi","protocol");
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("Hi","exception");
            e.printStackTrace();
        }

        return "Get data from Server";
    }

    public static String convertStreamToString(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"),1024);
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                inputStream.close();
            }
            Log.i("Hi",writer.toString());
            return writer.toString();
        } else {
            return "";
        }
    }

}
