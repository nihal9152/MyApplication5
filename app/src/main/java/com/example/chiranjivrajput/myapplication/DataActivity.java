package com.example.chiranjivrajput.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class DataActivity extends AppCompatActivity {
    public static String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        PlacePicker.IntentBuilder intentBuilder=new PlacePicker.IntentBuilder();
        Intent intent;
        try
        {
            intent= intentBuilder.build(DataActivity.this);
            //startActivity(new Intent(TaskActivity.this,DataActivity.class));
            startActivityForResult(intent,1);
        }
        catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                Place place=PlacePicker.getPlace(data,this);
                address= place.getAddress().toString();
            }
        }
    }
}
