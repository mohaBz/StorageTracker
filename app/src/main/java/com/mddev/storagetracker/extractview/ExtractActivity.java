package com.mddev.storagetracker.extractview;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mddev.storagetracker.database.Product;
import com.mddev.storagetracker.R;
import com.mddev.storagetracker.ViewModelProviderFactory;
import com.mddev.storagetracker.database.StockProduct;
import com.mddev.storagetracker.database.TruckProduct;
import com.mddev.storagetracker.mainview.StockStoreViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ExtractActivity extends DaggerAppCompatActivity {



//    private RecyclerView productRv;
//    private ExtractProductAdapter extractProductAdapter;
//    private StockStoreViewModel stockStoreViewModel;
//    private RadioGroup radioGroup;
//    @Inject
//    public ViewModelProviderFactory viewModelProviderFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        stockStoreViewModel=new ViewModelProvider(this,viewModelProviderFactory).get(StockStoreViewModel.class);
        setContentView(R.layout.activity_extract);

    }



}
