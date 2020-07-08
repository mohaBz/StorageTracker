package com.mddev.storagetracker;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mddev.storagetracker.database.StockProduct;
import com.mddev.storagetracker.database.TruckProduct;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class StockFragment extends DaggerFragment {
    private FloatingActionButton addFab;
    private NavController navController;
    @Inject
    public ViewModelProviderFactory viewModelProviderFactory;
    private StockStoreViewModel stockStoreViewModel;
    private RecyclerView productRecyclerView;
    private StockProductAdapter productsAdapter;

    public StockFragment() {
        // Required empty public constructor
    }





    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = Navigation.findNavController(container);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_truck, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stockStoreViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(StockStoreViewModel.class);
        addFab = view.findViewById(R.id.add_to_truck);
        productRecyclerView = view.findViewById(R.id.rv_product_list);
        productRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        productRecyclerView.setLayoutManager(layoutManager);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_stockFragment_to_addProductFragment2);
            }
        });
        productsAdapter = new StockProductAdapter(getContext());
        productRecyclerView.setAdapter(productsAdapter);
        stockStoreViewModel.loadAllTruckProduct().observe(getViewLifecycleOwner(), new Observer<List<StockProduct>>() {
            @Override
            public void onChanged(List<StockProduct> stockProducts) {
                productsAdapter.setProducts(stockProducts);
            }
        });

    }
}