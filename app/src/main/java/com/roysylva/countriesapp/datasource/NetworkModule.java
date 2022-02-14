package com.roysylva.countriesapp.datasource;

import com.roysylva.countriesapp.models.CountryModel;
import com.roysylva.countriesapp.viewmodels.Constants;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {

    private static NetworkModule instance;

    private ApiService apiService = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //converts object to an observable
            .build()
            .create(ApiService.class);


    public static NetworkModule getInstance() {

        if (instance == null){
            instance = new NetworkModule();
        }
        return instance;
    }

    public Single<List<CountryModel>> getCountries(){
        return apiService.getCountries();
    }
}
