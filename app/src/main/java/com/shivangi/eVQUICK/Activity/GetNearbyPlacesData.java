package com.shivangi.eVQUICK.Activity;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.shivangi.eVQUICK.Activity.DownloadUserURl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {

    String googlePlacesData;
    GoogleMap googleMap;
    String url;


    @Override
    protected String doInBackground(Object... objects) {

        try{
            googleMap = (GoogleMap) objects[0];
            url = (String) objects[1];
            DownloadUserURl downloadUserURl = new DownloadUserURl();
            googlePlacesData = downloadUserURl.readUrl(url);

        } catch (IOException e) {
             e.printStackTrace();
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {
       try {
           JSONObject parentObj = new JSONObject(s);
           JSONArray resultArr = parentObj.getJSONArray("results");
           for (int i = 0; i<resultArr.length(); i++){
               JSONObject jsonObject = resultArr.getJSONObject(i);
               JSONObject locationObj = jsonObject.getJSONObject("geometry").getJSONObject("location");

               String lat = locationObj.getString("lat");
               String lng = locationObj.getString("lng");

               JSONObject nameObj = resultArr.getJSONObject(i);
               String name = nameObj.getString("name");

               LatLng latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng ));

               MarkerOptions markerOptions = new MarkerOptions();
               markerOptions.title(name);
               markerOptions.position(latLng);

               googleMap.addMarker(markerOptions);
               googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));


           }

       } catch (JSONException e) {
           e.printStackTrace();
       }
    }
}
