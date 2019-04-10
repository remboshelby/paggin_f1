package com.example.paggin_f1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.common.base.BaseActivity;
import com.example.drivers.fragments.DriversListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int containerResId() {
        return R.id.activity_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pushFragment(new DriversListFragment());
    }
}
