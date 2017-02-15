package com.example.abhisheksaharn.parkager;

import android.content.Context;

import java.util.List;

/**
 * Created by Abhisheks Saharn on 4/25/2015.
 */
public class ReportData {
    String Name,Id,Amount;
    public ReportData(String name,String id,String amount){
        this.Name=name;
        this.Id=id;
        this.Amount=amount;

    }
    public void setName(String name){this.Name=name;}
    public void setId(String id){this.Id=id;}
    public void Amounts(String amount){this.Amount=amount;}
    public String getName(){return Name;}
    public String getId(){return Id;}
    public String getAmount(){return Amount;}



}
