package com.example.shika.hyperlist;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import models.Data_Model;


public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.ViewHolder> {// Recyclerview will extend to
    // recyclerview adapter
    private java.util.List<Data_Model> arrayList;
    private Context context;

    public RecyclerView_Adapter(Context context, java.util.List<Data_Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }

    @Override
    public int getItemCount() {
        return arrayList.size();

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Data_Model model = arrayList.get(position);

        ViewHolder mainHolder = (ViewHolder) holder;// holder

        // setting title
       holder.Name.setText(model.getTitle());
        holder.money.setText("price: "+Integer.toString(model.getPrice())+"  $");

        Picasso.get()
                .load(model.getImage())
                   .resize(model.getWidth(),model.getHeight())
               .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(holder.ig);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.item_row, viewGroup, false);
        ViewHolder listHolder = new ViewHolder(mainGroup);
        return listHolder;

    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ig;
        public TextView Name;
        public TextView points;
        public TextView money;

        public ViewHolder(View itemView) {
            super(itemView);

            ig = (ImageView) itemView.findViewById(R.id.image);
            Name = (TextView) itemView.findViewById(R.id.title);
            money = (TextView) itemView.findViewById(R.id.offer);

        }
    }
}
