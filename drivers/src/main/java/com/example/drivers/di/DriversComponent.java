package com.example.drivers.di;

import com.example.common.di.CommonComponent;
import com.example.drivers.fragments.DriversListFragment;

import dagger.BindsInstance;
import dagger.Component;

@DriversScope
@Component(dependencies = CommonComponent.class, modules = {DriversModule.class})
public interface DriversComponent {
    void inject(DriversListFragment driversListFragment);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder root(DriversListFragment driversListFragment);

        Builder commonComponent(CommonComponent commonComponent);

        DriversComponent build();
    }
}
