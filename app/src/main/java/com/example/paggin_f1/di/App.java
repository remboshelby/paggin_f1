package com.example.paggin_f1.di;

import android.app.Application;

import com.example.common.di.CommonApplication;
import com.example.common.di.CommonComponent;
import com.example.paggin_f1.R;
import com.example.paggin_f1.di.app.ApplicationComponent;
import com.example.paggin_f1.di.app.DaggerApplicationComponent;

public class App extends Application implements CommonApplication {
    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        makeComponent();
    }

    private void makeComponent() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .application(this)
                .serverUrl(getString(R.string.server_url))
                .build();
    }

    @Override
    public CommonComponent componen() {
        return applicationComponent;
    }
}
