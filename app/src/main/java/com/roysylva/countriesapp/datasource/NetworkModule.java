package com.roysylva.countriesapp.datasource;

import com.roysylva.countriesapp.models.CountryModel;
import com.roysylva.countriesapp.viewmodels.Constants;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {


    @Provides
    public ApiService providesApiService() {

       return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //converts object to an observable
                .build()
                .create(ApiService.class);

    }

}
