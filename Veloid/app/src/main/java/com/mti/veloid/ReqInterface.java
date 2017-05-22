package com.mti.veloid;

/**
 * Created by TheTrope on 22/05/2017.
 */
import retrofit2.Call;
import retrofit2.http.GET;
public interface ReqInterface {


    @GET("api/records/1.0/search/?dataset=stations-velib-disponibilites-en-temps-reel&rows=100&facet=banking&facet=bonus&facet=status&facet=contract_name")
    Call<JSONResponse> getJSON();
}
