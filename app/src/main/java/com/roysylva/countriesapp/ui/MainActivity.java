package com.roysylva.countriesapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.roysylva.countriesapp.R;
import com.roysylva.countriesapp.adapters.CountryListAdapter;
import com.roysylva.countriesapp.databinding.ActivityMainBinding;
import com.roysylva.countriesapp.viewmodels.CountryViewModel;

public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;
    TextView textView;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    private CountryViewModel countryViewModel;
    private CountryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.loading_View);
        textView = (TextView) findViewById(R.id.list_error);

        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);






    }
}