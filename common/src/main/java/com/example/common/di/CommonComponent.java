package com.example.common.di;

import com.example.common.network.api.DriversApiService;
import com.example.common.network.repository.DriversRepository;

public interface CommonComponent {
    DriversRepository driversRepository();
}
