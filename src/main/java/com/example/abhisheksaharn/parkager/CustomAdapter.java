package com.example.abhisheksaharn.parkager;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.List;

/**
 * Created by Abhishek Saharn on 4/16/2015.
 */
public class CustomAdapter extends BaseAdapter {
    Context context;
    protected List<Data> dataList;
    LayoutInflater inflater;

    public CustomAdapter(Context context, List<Data> datal) {
        this.dataList = datal;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
        //return dataList.get(position).getIconId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.custom_row, parent, false);
            holder.txtTitle = (TextView) view.findViewById(R.id.list_title);
            holder.txtId = (TextView) view.findViewById(R.id.list_id);
            holder.imgCar = (ImageView) view.findViewById(R.id.list_image);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Data data = dataList.get(position);
        holder.txtTitle.setText(data.getTitle());
        holder.txtId.setText(data.getId());
        holder.imgCar.setImageURI(data.getIconId());

        // holder.imgCar.setOnClickListener(this);

        return view;
    }


    private class ViewHolder {
        TextView txtTitle;
        ImageView imgCar;
        TextView txtId;
    }
}
