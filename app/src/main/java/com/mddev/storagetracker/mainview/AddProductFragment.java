package com.mddev.storagetracker.mainview;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mddev.storagetracker.R;
import com.mddev.storagetracker.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFragment extends DaggerFragment {
    private static final int RESULT_LOAD_IMG = 22;
    private static final String TAG = "AddProductFragmentTag";
    @Inject
    public ViewModelProviderFactory viewModelProviderFactory;

    private AddProductViewModel addProductViewModel;
    private EditText nameEd;
    private EditText priceEd;
    private EditText amountEd;
    private Button addBt;
    private ImageView productImage;
    private Button insertButton;
    private String imageUrString;

    public AddProductFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addProductViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(AddProductViewModel.class);
        nameEd = view.findViewById(R.id.rd_product_name);
        priceEd = view.findViewById(R.id.rd_product_price);
        amountEd = view.findViewById(R.id.rd_product_amount);
        addBt = view.findViewById(R.id.add_button);
        productImage = view.findViewById(R.id.product_Image);
        insertButton=view.findViewById(R.id.insert_button);
        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewClicked) {
                addProductViewModel.addToTruck(nameEd.getText().toString(), Float.parseFloat(priceEd.getText().toString()), Integer.parseInt(amountEd.getText().toString()),imageUrString);
                getParentFragmentManager().popBackStack();
            }
        });
        configurImagePicker();
    }



    public void configurImagePicker() {
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewClicked) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                AddProductFragment.this.startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });
    }
        @Override
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RESULT_LOAD_IMG) {
                try {
                    Uri imageUri = data.getData();
                    // Get the path from the Uri
                    imageUrString =imageUri.toString();
                    Log.d(TAG,imageUrString);
                    // Set the image in ImageView
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                    productImage.setImageBitmap(bitmap);
                } catch (Exception e) {

                }

            }
        }

    }