package com.example.listcity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList = findViewById(R.id.cityList);
        String []cities = {"Edmonton", "Vancouver", "Moscow", "Berlin", "Vienna", "Tokoyo", "Bejjing", "Osaka", "new dehli "};
        datalist = new ArrayList<>();
        datalist.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this,R.layout.content,datalist);
        cityList.setAdapter(cityAdapter);




    }
}