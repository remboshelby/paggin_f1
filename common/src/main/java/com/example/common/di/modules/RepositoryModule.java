package com.example.common.di.modules;

import com.example.common.network.api.DriversApiService;
import com.example.common.network.repository.DriversRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Singleton
    @Provides
    DriversRepository driversRepository(DriversApiService driversApiService){
        return new DriversRepository(driversApiService);
    }
}
