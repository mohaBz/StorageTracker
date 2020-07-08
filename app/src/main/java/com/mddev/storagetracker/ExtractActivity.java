package com.mddev.storagetracker;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.mddev.storagetracker.database.StockProduct;
import com.mddev.storagetracker.database.TruckProduct;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ExtractActivity extends DaggerAppCompatActivity {


    private RecyclerView productRv;
    private ExtractProductAdapter extractProductAdapter;
    private StockStoreViewModel stockStoreViewModel;
    private RadioGroup radioGroup;
    @Inject
    public ViewModelProviderFactory viewModelProviderFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stockStoreViewModel=new ViewModelProvider(this,viewModelProviderFactory).get(StockStoreViewModel.class);
        setContentView(R.layout.activity_extract);
        productRv=findViewById(R.id.rv_extract_list);
        radioGroup=findViewById(R.id.add_radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            }
        });
        productRv.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        productRv.setLayoutManager(layoutManager);
        extractProductAdapter=new ExtractProductAdapter(this);
        productRv.setAdapter(extractProductAdapter);
        stockStoreViewModel.loadAllTruckProduct().observe(this, new Observer<List<StockProduct>>() {
            @Override
            public void onChanged(List<StockProduct> stockProducts) {
                extractProductAdapter.setProducts(stockProducts);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.extract_app_bar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                // User chose the "Settings" item, show the app settings UI...
                List<StockProduct> truckProductList =extractProductAdapter.getProductList();
                if(radioGroup.getCheckedRadioButtonId()==R.id.radio_from_truck){
                    for (int i=0;i<truckProductList.size();i++){
                        stockStoreViewModel.diductAmountFromTruck(truckProductList.get(i),Integer.parseInt(extractProductAdapter.getAmounts().get(i).getText().toString()));
                    }
                }
                else
                    for (int i=0;i<truckProductList.size();i++){
                        stockStoreViewModel.diductAmountToTruck(truckProductList.get(i),Integer.parseInt(extractProductAdapter.getAmounts().get(i).getText().toString()));
                    }
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    

}
