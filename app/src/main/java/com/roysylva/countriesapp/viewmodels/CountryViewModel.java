package com.roysylva.countriesapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.roysylva.countriesapp.models.CountryModel;

import java.util.ArrayList;
import java.util.List;

public class CountryViewModel extends ViewModel {

    private MutableLiveData<List<CountryModel>> _countries = new MutableLiveData<List<CountryModel>>();
    public LiveData<List<CountryModel>> countries = _countries;

    private MutableLiveData<Boolean> _loading = new MutableLiveData<Boolean>();
    public LiveData<Boolean> loading = _loading;

    private MutableLiveData<Boolean> _countryLoadError = new MutableLiveData<Boolean>();

    public LiveData<Boolean> countryLoadError = _countryLoadError;


    public void refreshFeed(){
        fetchCountries();
    }

    private void fetchCountries(){
        CountryModel country1 =  new CountryModel("Albania", "Tirania" , "");
        CountryModel country2 =  new CountryModel("Brasil", "Brasilia" , "");
        CountryModel country3 =  new CountryModel("Czech", "Praga" , "");

        List<CountryModel> countryList = new ArrayList();
        countryList.add(country1);
        countryList.add(country2);
        countryList.add(country3);

        _countries.setValue(countryList);
        _countryLoadError.setValue(false);
        _loading.setValue(false);



    }
}
