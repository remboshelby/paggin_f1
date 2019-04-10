package com.example.drivers.di;

import com.example.common.network.repository.DriversRepository;
import com.example.drivers.fragments.DriversListFragment;
import com.example.drivers.fragments.pagging.DriversViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;

@Module
public class DriversModule {
    @Provides
    @DriversScope
    public DriversViewModel provideDriversViewModel(DriversListFragment host,
                                                    final DriversRepository driversRepository){
        return ViewModelProviders.of(host, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new DriversViewModel(driversRepository);
            }
        }).get(DriversViewModel.class);
    }
}
