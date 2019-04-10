package com.example.common.network.repository;

import com.example.common.data.DriversItem;
import com.example.common.data.DriversResponse;
import com.example.common.network.api.DriversApiService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DriversRepository {
    private static final int PAGE_SIZE = 15;


    private DriversApiService driversApiService;

    public DriversRepository(DriversApiService driversApiService) {
        this.driversApiService = driversApiService;
    }

    public Observable<List<DriversItem>> getDrivers(int size, int startPos){
        return driversApiService.getSomeDrivers(size, startPos).map(driversResponse -> driversResponse.getMRData().getDriverTable().getDrivers());
    }
    public Observable<Integer> getTotalCount() {
        return driversApiService.getSomeDrivers(10, 10).map(driversResponse -> Integer.valueOf(driversResponse.getMRData().getTotal()));
    }

    public static int getPageSize() {
        return PAGE_SIZE;
    }
}
