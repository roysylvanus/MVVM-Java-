package com.roysylva.countriesapp.repositories;

import com.roysylva.countriesapp.datasource.ApiService;
import com.roysylva.countriesapp.di.DaggerApiComponent;
import com.roysylva.countriesapp.datasource.NetworkModule;
import com.roysylva.countriesapp.models.CountryModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;


public class CountryRespository {
    private static CountryRespository instance;

    private CountryRespository(){
        DaggerApiComponent.create().inject(this);
    }

    @Inject
    public ApiService apiService;

    public static CountryRespository getInstance() {

        if (instance == null){
            instance = new CountryRespository();
        }
        return instance;
    }

    public Single<List<CountryModel>> getCountries(){
        return apiService.getCountries();
    }


}
