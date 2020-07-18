package com.mddev.storagetracker;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.mddev.storagetracker.database.Product;
import com.mddev.storagetracker.database.StockProduct;
import com.mddev.storagetracker.database.TruckProduct;
import com.mddev.storagetracker.extractview.ExtractActivity;
import com.mddev.storagetracker.extractview.ExtractProductAdapter;
import com.mddev.storagetracker.mainview.StockStoreViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectProductsFragment extends DaggerFragment {


    private RecyclerView productRv;
    private ExtractProductAdapter extractProductAdapter;
    private StockStoreViewModel stockStoreViewModel;
    private RadioGroup radioGroup;
    private androidx.appcompat.widget.Toolbar toolbar;
    @Inject
    public ViewModelProviderFactory viewModelProviderFactory;

    public SelectProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_products, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stockStoreViewModel=new ViewModelProvider(this,viewModelProviderFactory).get(StockStoreViewModel.class);
        productRv=view.findViewById(R.id.rv_extract_list);
        radioGroup=view.findViewById(R.id.add_radio_group);
        toolbar=view.findViewById(R.id.toolbar_select);
        toolbar.inflateMenu(R.menu.extract_app_bar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);

            }

        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            }
        });
        productRv.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        productRv.setLayoutManager(layoutManager);
        extractProductAdapter=new ExtractProductAdapter(getContext());
        productRv.setAdapter(extractProductAdapter);
        stockStoreViewModel.loadAllStockProduct().observe(getViewLifecycleOwner(), new Observer<List<StockProduct>>() {
            @Override
            public void onChanged(List<StockProduct> stockProducts) {
                stockStoreViewModel.setStockProducts(stockProducts);
                extractProductAdapter.setProducts(stockProducts);
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radio_to_truck){
                    stockStoreViewModel.loadAllStockProduct().observe(getViewLifecycleOwner(), new Observer<List<StockProduct>>() {
                        @Override
                        public void onChanged(List<StockProduct> stockProducts) {
                            stockStoreViewModel.setStockProducts(stockProducts);
                            extractProductAdapter.setProducts(stockProducts);
                        }
                    });
                }
                else
                    stockStoreViewModel.loadAllTruckProduct().observe(getViewLifecycleOwner(), new Observer<List<TruckProduct>>() {
                        @Override
                        public void onChanged(List<TruckProduct> stockProducts) {
                            extractProductAdapter.setProducts(stockProducts);
                        }
                    });
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                // User chose the "Settings" item, show the app settings UI...
                List<? extends Product> truckProductList =extractProductAdapter.getProductList();
                if(radioGroup.getCheckedRadioButtonId()==R.id.radio_from_truck){
                    for (int i=0;i<truckProductList.size();i++){
                        stockStoreViewModel.diductAmountFromTruck(truckProductList.get(i),Integer.parseInt(extractProductAdapter.getAmounts().get(i).getText().toString()));
                    }
                }
                else
                    for (int i=0;i<truckProductList.size();i++){
                        stockStoreViewModel.diductAmountToTruck(truckProductList.get(i),Integer.parseInt(extractProductAdapter.getAmounts().get(i).getText().toString()));
                    }
                    getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
