package com.example.drivers.fragments.pagging;

import com.example.common.base.BaseViewModel;
import com.example.common.data.DriversItem;
import com.example.common.network.repository.DriversRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class DriversViewModel extends BaseViewModel {
    private DriversRepository driversRepository;

    private LiveData<PagedList<DriversItem>> drivers;

    private LiveData<Boolean> isLoading;

    public DriversViewModel(DriversRepository driversRepository) {
        this.driversRepository = driversRepository;

        DriversDataSourceFactory driversDataSourceFactory = new DriversDataSourceFactory(driversRepository,getCompositeDisposable());

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(driversRepository.getPageSize())
                .build();

        drivers = new LivePagedListBuilder<>(driversDataSourceFactory, config).build();

        isLoading = Transformations.switchMap(driversDataSourceFactory.getDriversDataSourceMutableLiveData(), DriversDataSource::getIsLoading);
    }

    public LiveData<PagedList<DriversItem>> getDrivers() {
        return drivers;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }
}
