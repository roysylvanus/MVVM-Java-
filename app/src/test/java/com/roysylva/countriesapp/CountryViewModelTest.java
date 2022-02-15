package com.roysylva.countriesapp;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.roysylva.countriesapp.models.CountryModel;
import com.roysylva.countriesapp.repositories.CountryRespository;
import com.roysylva.countriesapp.viewmodels.CountryViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class CountryViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    public CountryRespository countryRespository;

    @InjectMocks
    CountryViewModel countryViewModel = new CountryViewModel();

    private Single<List<CountryViewModel>> testSingle;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setupRxSchedulers(){
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(runnabe ->{runnabe.run();});
            }
        };
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }


    @Test
    public void getCountrySuccess(){
        CountryModel countryModel = new CountryModel("CountryName","Capiatl","flag");
        ArrayList<CountryModel> countryList = new ArrayList<CountryModel>();
        countryList.add(countryModel);

        testSingle = Single.just(countryList);

        Mockito.when(countryRespository.getCountries()).thenReturn(testSingle);

        countryViewModel.refreshFeed();

        Assert.assertEquals(1,countryViewModel.countries.getValue().size());
        Assert.assertEquals(false,countryViewModel.countryLoadError.getValue());
        Assert.assertEquals(false,countryViewModel.loading.getValue());

    }


    @Test
    public void getCountryFailure(){
        testSingle = Single.error(new Throwable());

        Mockito.when(countryRespository.getCountries()).thenReturn(testSingle);

        countryViewModel.refreshFeed();

        Assert.assertEquals(true,countryViewModel.countryLoadError.getValue());
        Assert.assertEquals(true,countryViewModel.loading.getValue());

    }
}
