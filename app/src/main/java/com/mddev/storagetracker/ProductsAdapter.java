package com.mddev.storagetracker;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mddev.storagetracker.database.TruckProduct;

import java.io.IOException;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHoler> {
    private static final String TAG ="ProductViewHoler" ;
    private List<TruckProduct> products;
    private Context context;
    public ProductsAdapter(Context context) {
        this.context=context;
    }

    public void setProducts(List<TruckProduct> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        return new ProductViewHoler(inflater.inflate(R.layout.product_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHoler holder, int position) {
            TruckProduct product=products.get(position);
            holder.onbindView(product);
    }

    @Override
    public int getItemCount() {
        if( products==null)
            return 0;
        else
            return products.size();
    }



    public class ProductViewHoler extends RecyclerView.ViewHolder {


        private TextView nameTx;
        private TextView priceTx;
        private TextView amountTx;
        private ImageView productImage;

        public ProductViewHoler(@NonNull View itemView) {
            super(itemView);
            nameTx=itemView.findViewById(R.id.product_name);
            priceTx=itemView.findViewById(R.id.product_price);
            amountTx=itemView.findViewById(R.id.product_amount);
            productImage=itemView.findViewById(R.id.product_Image);

        }
        public void onbindView(TruckProduct truckProduct){
            nameTx.setText(truckProduct.getName());
            amountTx.setText(""+truckProduct.getAmount());
            priceTx.setText(""+truckProduct.getPrice());
            Bitmap bitmap = null;
            if (truckProduct.getImageUri()!=null){
                try {
                    Uri imageUri
                        =Uri.parse(truckProduct.getImageUri());

                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
                    productImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
