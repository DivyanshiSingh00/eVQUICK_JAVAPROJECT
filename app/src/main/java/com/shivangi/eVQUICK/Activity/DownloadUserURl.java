package com.shivangi.eVQUICK.Activity;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadUserURl {
    public String readUrl(String myurl) throws IOException {
        String data = "";
        InputStream inputstream = null;
        HttpURLConnection urlconnection = null;

        try{
            URL url = new URL(myurl);
            urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.connect();

            inputstream = urlconnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputstream));

            StringBuffer sb = new StringBuffer();
            String line = "";
            while((line = br.readLine()) != null){
                sb.append(line);
            }
            data = sb.toString();
            br.close();
        } catch (MalformedURLException e) {
            Log.d("Exception",e.toString());
        }

        finally {
            inputstream.close();
            urlconnection.disconnect();
        }
        return data;
    }
}
