package com.roysylva.countriesapp.repositories;

import com.roysylva.countriesapp.datasource.ApiService;
import com.roysylva.countriesapp.datasource.NetworkModule;
import com.roysylva.countriesapp.models.CountryModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class CountryRespository {

   private NetworkModule networkModule = NetworkModule.getInstance();

   public Single<List<CountryModel>> getCountries(){
       return networkModule.getCountries();
   }


}
