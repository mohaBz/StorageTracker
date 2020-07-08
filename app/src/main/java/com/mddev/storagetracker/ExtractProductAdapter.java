package com.mddev.storagetracker;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mddev.storagetracker.database.StockProduct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtractProductAdapter extends RecyclerView.Adapter<ExtractProductAdapter.ProductViewHolder> {

    private List<StockProduct> products;
    private List<EditText> amounts;
    private Context context;
    public ExtractProductAdapter(Context context) {
        this.context=context;
    }

    public void setProducts(List<StockProduct> products) {
        this.products = products;
        this.amounts=new ArrayList<>();
        notifyDataSetChanged();
    }

    public List<EditText> getAmounts() {
        return amounts;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        return new ProductViewHolder(inflater.inflate(R.layout.extract_product_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  ProductViewHolder holder, int position) {
        StockProduct product=products.get(position);
        holder.onbindView(product);
    }

    @Override
    public int getItemCount() {
        if( products==null)
            return 0;
        else
            return products.size();
    }

    public List<StockProduct> getProductList() {
        return this.products;
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTx;
        private TextView priceTx;
        private EditText amountTx;
        private ImageView productImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTx=itemView.findViewById(R.id.product_name);
            priceTx=itemView.findViewById(R.id.product_price);
            amountTx=itemView.findViewById(R.id.product_amount);
            productImage=itemView.findViewById(R.id.product_Image);

        }
        public void onbindView(StockProduct truckProduct){
            nameTx.setText(truckProduct.getName());
            amounts.add(amountTx);
            amountTx.setMaxEms(truckProduct.getAmount());
            amountTx.setText("0");
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
