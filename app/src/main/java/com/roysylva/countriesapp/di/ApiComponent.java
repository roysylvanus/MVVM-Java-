package com.roysylva.countriesapp.di;


import com.roysylva.countriesapp.datasource.NetworkModule;
import com.roysylva.countriesapp.repositories.CountryRespository;
import com.roysylva.countriesapp.viewmodels.CountryViewModel;

import dagger.Component;

@Component(modules = {NetworkModule.class,AppModule.class})
public interface ApiComponent {

    void inject(CountryRespository countryRespository);


    void inject(CountryViewModel countryViewModel);
}
