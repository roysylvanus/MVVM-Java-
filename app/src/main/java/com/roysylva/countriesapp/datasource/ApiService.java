package com.roysylva.countriesapp.datasource;

import com.roysylva.countriesapp.models.CountryModel;
import com.roysylva.countriesapp.viewmodels.Constants;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET(Constants.COUNTRIES)
    Single<List<CountryModel>> getCountries();


}
