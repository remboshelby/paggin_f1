package com.example.drivers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.common.base.BaseFragment;
import com.example.common.base.BaseViewHolder;
import com.example.common.data.DriversItem;
import com.example.common.di.CommonApplication;
import com.example.drivers.R;
import com.example.drivers.R2;
import com.example.drivers.di.DaggerDriversComponent;
import com.example.drivers.di.DriversComponent;
import com.example.drivers.fragments.pagging.DriversViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DriversListFragment extends BaseFragment {


    @BindView(R2.id.progress_bar_toolbar)
    ProgressBar progress_bar_toolbar;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R2.id.progress_bar)
    ProgressBar progress_bar;
    @Inject
    protected DriversViewModel driversViewModel;

    private LinearLayoutManager layoutManager;

    private DriverAdapter driverAdapter;

    private DriversComponent driversComponent;
    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.drivers_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        getRoot().setSupportActionBar(toolbar);

        driverAdapter = new DriverAdapter();

        layoutManager = new LinearLayoutManager(getRoot());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler_view.getContext(), layoutManager.getOrientation());

        recycler_view.addItemDecoration(dividerItemDecoration);
        recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_view.setAdapter(driverAdapter);

        driversViewModel.getDrivers().observe(this, driversItems -> driverAdapter.submitList(driversItems));
        driversViewModel.getIsLoading().observe(this, isLoading -> DriversListFragment.this.setLoadingState(isLoading));
        driversViewModel.onViewCreated();
    }

    @Override
    protected void inject() {
        CommonApplication commonApplication = (CommonApplication)getRoot().getApplication();
        driversComponent = DaggerDriversComponent
                .builder()
                .root(this)
                .commonComponent(commonApplication.componen())
                .build();

        driversComponent.inject(this);

    }

    private static class DriverAdapter extends PagedListAdapter<DriversItem, DriverViewHodler> {
        public DriverAdapter() {
            super(DIFF_CALLBACK);
        }

        @NonNull
        @Override
        public DriverViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DriverViewHodler(LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull DriverViewHodler holder, int position) {
            DriversItem driversItem= getItem(position);

            if (driversItem==null){
                holder.loading.setText("Загрузка... ");
                holder.progressBar.setVisibility(View.VISIBLE);
                holder.loading.setVisibility(View.VISIBLE);

                holder.tvGivenName.setVisibility(View.GONE);
                holder.tvFamilyName.setVisibility(View.GONE);
                holder.tvDateOfBirth.setVisibility(View.GONE);
                holder.tvNationality.setVisibility(View.GONE);
            }
            else {
                holder.progressBar.setVisibility(View.GONE);
                holder.loading.setVisibility(View.GONE);

                holder.tvGivenName.setVisibility(View.VISIBLE);
                holder.tvFamilyName.setVisibility(View.VISIBLE);
                holder.tvDateOfBirth.setVisibility(View.VISIBLE);
                holder.tvNationality.setVisibility(View.VISIBLE);

                holder.tvGivenName.setText("Name: " +  driversItem.getGivenName());
                holder.tvFamilyName.setText("Surname: " + driversItem.getFamilyName());
                holder.tvDateOfBirth.setText("BirthDay: " + driversItem.getDateOfBirth());
                holder.tvNationality.setText("Nationality: " + driversItem.getNationality());
            }
        }

    }
    private static class DriverViewHodler extends BaseViewHolder<DriversItem>{
        private TextView tvGivenName;
        private TextView tvFamilyName;
        private TextView tvDateOfBirth;
        private TextView tvNationality;
        private TextView loading;

        private ProgressBar progressBar;

        public DriverViewHodler(@NonNull View itemView) {
            super(itemView);

            tvGivenName = (TextView)itemView.findViewById(R.id.givenName);
            tvFamilyName = (TextView)itemView.findViewById(R.id.familyName);
            tvDateOfBirth = (TextView)itemView.findViewById(R.id.dateOfBirth);
            tvNationality = (TextView)itemView.findViewById(R.id.nationality);
            loading = (TextView)itemView.findViewById(R.id.loading);

            progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar_item);
        }


        @Override
        public void bind(DriversItem item) {
            if (progressBar.getVisibility()==View.VISIBLE){
                progressBar.setVisibility(View.GONE);
                loading.setVisibility(View.GONE);
            }
            tvGivenName.setText(item.getGivenName());
            tvFamilyName.setText(item.getFamilyName());
            tvDateOfBirth.setText(item.getDateOfBirth());
            tvNationality.setText(item.getNationality());
        }
    }

    public static final DiffUtil.ItemCallback<DriversItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<DriversItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull DriversItem oldItem, @NonNull DriversItem newItem) {
            return oldItem.getDriverId().equals(newItem.getDriverId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull DriversItem oldItem, @NonNull DriversItem newItem) {
            return oldItem.getDriverId().equals(newItem.getDriverId());
        }
    };
    private void setLoadingState(boolean isLoading) {
        if (isLoading) {
            recycler_view.setVisibility(driverAdapter.getItemCount() > 0 ? View.VISIBLE : View.GONE);
            progress_bar.setVisibility(driverAdapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
            progress_bar_toolbar.setVisibility(driverAdapter.getItemCount() > 0 ? View.VISIBLE : View.GONE);
        } else {
            recycler_view.setVisibility(View.VISIBLE);
            progress_bar.setVisibility(View.GONE);
            progress_bar_toolbar.setVisibility(View.GONE);
        }
    }
}
