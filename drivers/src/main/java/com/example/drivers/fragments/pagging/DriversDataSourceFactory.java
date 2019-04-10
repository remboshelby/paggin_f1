package com.example.drivers.fragments.pagging;

import com.example.common.data.DriversItem;
import com.example.common.network.repository.DriversRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import io.reactivex.disposables.CompositeDisposable;

public class DriversDataSourceFactory extends DataSource.Factory<Integer, DriversItem> {
    private DriversRepository driversRepository;
    private CompositeDisposable compositeDisposable;

    private MutableLiveData<DriversDataSource> driversDataSourceMutableLiveData;

    public DriversDataSourceFactory(DriversRepository driversRepository, CompositeDisposable compositeDisposable) {
        this.driversRepository = driversRepository;
        this.compositeDisposable = compositeDisposable;
        this.driversDataSourceMutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer, DriversItem> create() {
        DriversDataSource driversDataSource = new DriversDataSource(driversRepository, compositeDisposable);
        driversDataSourceMutableLiveData.postValue(driversDataSource);
        return driversDataSource;
    }

    public MutableLiveData<DriversDataSource> getDriversDataSourceMutableLiveData() {
        return driversDataSourceMutableLiveData;
    }
}
