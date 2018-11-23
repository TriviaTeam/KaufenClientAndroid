package com.delivery.kaufen.kaufen.APICall;

import com.delivery.kaufen.kaufen.Models.Client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ClientDataService {

    @POST("clients/")
    Call<Client> postClientData(@Body Client client);
}
