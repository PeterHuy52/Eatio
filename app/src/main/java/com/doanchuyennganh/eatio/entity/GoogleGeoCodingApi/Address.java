package com.doanchuyennganh.eatio.entity.GoogleGeoCodingApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TungHo on 05/10/2017.
 */

public class Address {


    @SerializedName("address_components")
    public List<AddressComponent> addressComponents;

    @SerializedName("formatted_address")
    public String formattedAddress;

    @SerializedName("place_id")
    public String placeId;

    @SerializedName("types")
    public List<String> types;


    public class AddressComponent{

        @SerializedName("short_name")
        public String shortName;

        @SerializedName("long_name")
        public String longName;

        @SerializedName("types")
        public List<String> types;
    }

}
