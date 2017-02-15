package com.example.abhisheksaharn.parkager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Abhisheks Saharn on 4/24/2015.
 */
public class CustomAdapter2 extends BaseAdapter {
    Context context;
    List<ReportData> reportDataList;
    LayoutInflater inflater;
    public CustomAdapter2(Context context,List<ReportData> reportDataList)
    {
        this.context=context;
        this.reportDataList=reportDataList;
        this.inflater=LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return reportDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return  reportDataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        ViewHolder holder2;
        if (view == null) {
            holder2 = new ViewHolder();
            view = inflater.inflate(R.layout.custom_report_row, parent, false);
            holder2.txtName = (TextView) view.findViewById(R.id.rtvName);
            holder2.txtId = (TextView) view.findViewById(R.id.rtvId);
            holder2.txtAmount = (TextView) view.findViewById(R.id.rtvAmount);
            view.setTag(holder2);
        } else {
            holder2 = (ViewHolder) view.getTag();
        }
        ReportData repodata = reportDataList.get(position);
        holder2.txtName.setText(repodata.getName());
        holder2.txtId.setText(repodata.getId());
        holder2.txtAmount.setText(repodata.getAmount());

        return  view;

    }
    private class ViewHolder {
        TextView txtName,txtId,txtAmount;

    }
}
