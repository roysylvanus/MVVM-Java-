package com.roysylva.countriesapp.di;

import com.roysylva.countriesapp.repositories.CountryRespository;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    public CountryRespository providesRepository(){
        return CountryRespository.getInstance();
    }
}
