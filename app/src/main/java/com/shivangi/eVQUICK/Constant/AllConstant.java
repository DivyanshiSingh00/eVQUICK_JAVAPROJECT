package com.shivangi.eVQUICK.Constant;

import com.shivangi.eVQUICK.PlaceModel;
import com.shivangi.eVQUICK.R;

import java.util.ArrayList;
import java.util.Arrays;

public interface AllConstant {

    int STORAGE_REQUEST_CODE = 1000;
    int LOCATION_REQUEST_CODE = 2000;



    ArrayList<PlaceModel> placesName = new ArrayList<>(
            Arrays.asList(
                    new PlaceModel(1, R.drawable.ic_restaurant, "Restaurant", "restaurant"),
                    new PlaceModel(2, R.drawable.baseline_local_atm_24, "ATM", "atm"),
                    new PlaceModel(3, R.drawable.ic_gas_station, "Charging Stations", "gas_station"),
                    new PlaceModel(4, R.drawable.ic_hospital, "Hospitals & Clinics", "hospital")


            )
    );
}
