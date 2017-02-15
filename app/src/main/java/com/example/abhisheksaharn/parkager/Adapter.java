package com.example.abhisheksaharn.parkager;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Abhishek Saharn on 4/5/2015.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private   LayoutInflater inflater;
    private Context context;
    List<Information> data = Collections.emptyList();

    public Adapter(Context context, List<Information> data) {
        inflater = LayoutInflater.from(context);
        this.context=context;
        this.data = data;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row_recycle, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current = data.get(position);
        holder.titlee.setText(current.ttitle);
        holder.iconn.setImageResource(current.iicon);
        holder.titlee.setOnClickListener(new View.OnClickListener() {
            @Override//
            public void onClick(View view) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titlee;
        ImageView iconn;

        public MyViewHolder(View itemView) {
            super(itemView);
            titlee = (TextView) itemView.findViewById(R.id.cust_tv);
             iconn=(ImageView) itemView.findViewById(R.id.cust_iv);
            itemView.setOnClickListener(this);
            iconn.setOnClickListener(this);
            titlee.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

           switch (getPosition())
           {
               case 0:
                   view.getContext().startActivity(new Intent(context,Reports.class));
                break;
               case 1:
                   view.getContext().startActivity(new Intent(context,DataTable.class));
                   break;
               case 3:
                    view.getContext().startActivity(new Intent(context,AboutDev.class));
                   break;

           }

        }
    }
}
