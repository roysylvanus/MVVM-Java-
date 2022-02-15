package com.roysylva.countriesapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.roysylva.countriesapp.models.CountryModel;
import com.roysylva.countriesapp.repositories.CountryRespository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CountryViewModel extends ViewModel {

    private CountryRespository countryRespository = new CountryRespository();

    private MutableLiveData<List<CountryModel>> _countries = new MutableLiveData<List<CountryModel>>();
    public LiveData<List<CountryModel>> countries = _countries;

    private MutableLiveData<Boolean> _loading = new MutableLiveData<Boolean>();
    public LiveData<Boolean> loading = _loading;

    private MutableLiveData<Boolean> _countryLoadError = new MutableLiveData<Boolean>();

    public LiveData<Boolean> countryLoadError = _countryLoadError;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public void refreshFeed(){
        fetchCountries();
    }

    private void fetchCountries(){
       _loading.setValue(true);
       compositeDisposable.add(
               countryRespository.getCountries()
                       .subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(new DisposableSingleObserver<List<CountryModel>>(){
                    @Override
                   public void onSuccess(List<CountryModel> countryModels){
                        _countries.setValue(countryModels);
                        _countryLoadError.setValue(false);
                        _loading.setValue(false);

                    }
                    @Override
                   public void onError(Throwable e){
                        _countryLoadError.setValue(true);
                        _loading.setValue(true);
                        e.printStackTrace();
                    }
               })
       );



    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
