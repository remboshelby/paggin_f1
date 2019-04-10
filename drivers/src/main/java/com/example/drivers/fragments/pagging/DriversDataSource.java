package com.example.drivers.fragments.pagging;

import com.example.common.data.DriversItem;
import com.example.common.network.repository.DriversRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PositionalDataSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DriversDataSource extends PositionalDataSource<DriversItem> {
    private DriversRepository driversRepository;
    private CompositeDisposable compositeDisposable;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public int totalCount;

    public DriversDataSource(DriversRepository driversRepository, CompositeDisposable compositeDisposable) {

        this.driversRepository = driversRepository;
        this.compositeDisposable = compositeDisposable;

        compositeDisposable.add(driversRepository.getTotalCount()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(integer -> setTotalCount(integer))
        );
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<DriversItem> callback) {
        isLoading.postValue(true);

        compositeDisposable.add(driversRepository.getDrivers(params.requestedLoadSize, driversRepository.getPageSize())
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(driversItems -> {
                            callback.onResult(driversItems, params.requestedStartPosition, DriversDataSource.this.getTotalCount());
                            isLoading.postValue(false);
                        },
                        throwable -> throwable.printStackTrace()));
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<DriversItem> callback) {
        isLoading.postValue(true);

        compositeDisposable.add(driversRepository.getDrivers(params.loadSize, params.startPosition)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(driversItems -> {
                    callback.onResult(driversItems);
                    isLoading.postValue(false);
                }));
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
