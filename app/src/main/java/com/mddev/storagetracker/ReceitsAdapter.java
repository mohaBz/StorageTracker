package com.mddev.storagetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mddev.storagetracker.database.Product;

import java.util.List;

import javax.inject.Inject;

public class ReceitsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Product> products;
    private Context context;
    private float totalPrice=0;
    @Inject
    public ReceitsAdapter(Context context){
        this.context=context;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        switch (viewType){
            case 0:
                View view=inflater.inflate(R.layout.receipt_item_layout,parent,false);
                return new ReceitViewHolder(view);
            case 1:
                View view1=inflater.inflate(R.layout.totale_receipt_item,parent,false);
                return new TotalViewHolder(view1);
            default: return null;
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position==products.size())
        return 1;
        else
            return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            switch (holder.getItemViewType()){
                case 0:
                    Product product=products.get(position);
                    totalPrice+=product.getPrice()*product.getAmount();
                    ReceitViewHolder receitViewHolder=(ReceitViewHolder) holder;
                    receitViewHolder.onBind(product);
                    break;
                case 1:
                    TotalViewHolder totalViewHolder=(TotalViewHolder) holder;
                    totalViewHolder.onBind();
                    break;
            }

    }

    @Override
    public int getItemCount() {
        if(products==null)return 0;
        else
            return products.size()+1;
    }

    public class ReceitViewHolder extends RecyclerView.ViewHolder {

        private TextView productNameTx;
        private TextView priceTx;
        private TextView amountTx;
        private TextView costTx;

        public ReceitViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTx=itemView.findViewById(R.id.product_name);
            priceTx=itemView.findViewById(R.id.product_price);
            amountTx=itemView.findViewById(R.id.product_amount);
            costTx=itemView.findViewById(R.id.cost);

        }
        public void onBind(Product product){
            productNameTx.setText(product.getName());
            priceTx.setText(""+product.getPrice());
            amountTx.setText(""+product.getAmount());
            costTx.setText(""+product.getPrice()*product.getAmount());
        }
    }
    public class TotalViewHolder extends RecyclerView.ViewHolder {


        private TextView totalTx;

        public TotalViewHolder(@NonNull View itemView) {
            super(itemView);
           totalTx=itemView.findViewById(R.id.tx_total);


        }
        public void onBind(){
            totalTx.setText(""+totalPrice);
        }
    }
}
