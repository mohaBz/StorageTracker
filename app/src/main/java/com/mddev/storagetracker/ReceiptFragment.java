package com.mddev.storagetracker;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mddev.storagetracker.mainview.StockStoreViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiptFragment extends DaggerFragment {

    private StockStoreViewModel stockStoreViewModel;
    @Inject
    public ViewModelProviderFactory viewModelProviderFactory;
    private RecyclerView receiptRecyclerView;
    private Toolbar toolbar;

    public ReceiptFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stockStoreViewModel=new ViewModelProvider(getActivity(),viewModelProviderFactory).get(StockStoreViewModel.class);
        receiptRecyclerView=view.findViewById(R.id.rv_receipt);
        receiptRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        receiptRecyclerView.setLayoutManager(layoutManager);
        ReceitsAdapter receitsAdapter=new ReceitsAdapter(getContext());
        receitsAdapter.setProducts(stockStoreViewModel.getProductList());
        receiptRecyclerView.setAdapter(receitsAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_receipt, container, false);
    }

}
