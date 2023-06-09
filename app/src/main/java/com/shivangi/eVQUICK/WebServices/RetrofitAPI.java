package com.shivangi.eVQUICK.WebServices;

import com.shivangi.eVQUICK.models.DirectionPlaceModel.DirectionResponseModel;
import com.shivangi.eVQUICK.models.GooglePlaceModel.GoogleResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {

    @GET
    Call<GoogleResponseModel> getNearByPlaces(@Url String url);

    @GET
    Call<DirectionResponseModel> getDirection(@Url String url);
}