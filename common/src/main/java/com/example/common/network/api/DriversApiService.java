package com.example.common.network.api;

import com.example.common.data.DriversResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DriversApiService {
    @GET("api/f1/drivers.json")
    Observable<DriversResponse> getSomeDrivers(@Query("limit") int limit,
                                               @Query("offset") int offset);
}
