package com.roysylva.countriesapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roysylva.countriesapp.R;
import com.roysylva.countriesapp.databinding.ItemCountryBinding;
import com.roysylva.countriesapp.models.CountryModel;
import com.roysylva.countriesapp.utils.AppUtils;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private List<CountryModel> countriesList;

    public CountryListAdapter(List<CountryModel> countriesList) {
        this.countriesList = countriesList;
    }

    public void updateCountries(List<CountryModel> newCountriesList){
        countriesList.clear();
        countriesList.addAll(newCountriesList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
       CountryModel countryModel = countriesList.get(position);

       holder.item.name.setText(countryModel.getCountryName());
       holder.item.capital.setText(countryModel.getCapital());
        Log.e("Adapter",countryModel.getCountryName());
        AppUtils.loadImage(holder.item.imageView, countryModel.getFlag(),AppUtils.getProgressDrawable(holder.item.imageView.getContext()));


    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemCountryBinding binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new  CountryViewHolder(binding);


    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder{

        private ItemCountryBinding item;

        public CountryViewHolder(@NonNull ItemCountryBinding binding) {
            super(binding.getRoot());
            this.item = binding;

        }
    }
}
