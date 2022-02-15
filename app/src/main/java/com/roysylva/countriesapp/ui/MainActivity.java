package com.roysylva.countriesapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.roysylva.countriesapp.R;
import com.roysylva.countriesapp.adapters.CountryListAdapter;
import com.roysylva.countriesapp.databinding.ActivityMainBinding;
import com.roysylva.countriesapp.viewmodels.CountryViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;

    private CountryViewModel countryViewModel;
    private CountryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        adapter = new CountryListAdapter(new ArrayList<>());

        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        countryViewModel.refreshFeed();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        binding.refreshLayout.setOnRefreshListener(()->{
            countryViewModel.refreshFeed();
            binding.refreshLayout.setRefreshing(false);
        });

        observeViewModel();


    }

    private void observeViewModel(){
        countryViewModel.countries.observe(this,countryModels -> {
            if (countryModels!=null){
                binding.recyclerView.setVisibility(View.VISIBLE);
                Log.e("Main",countryModels.toString());
                adapter.updateCountries(countryModels);
            }
        });
        countryViewModel.countryLoadError.observe(this, isError ->{
            if (isError!=null){
                binding.listError.setVisibility(isError? View.VISIBLE :View.GONE);
            }
        });
        countryViewModel.loading.observe(this,isLoading ->{
            if (isLoading!=null){
                binding.loadingView.setVisibility(isLoading? View.VISIBLE : View.GONE);

                if (isLoading){
                    binding.listError.setVisibility(View.GONE);
                    binding.recyclerView.setVisibility(View.GONE);
                }
            }
        });
    }
}