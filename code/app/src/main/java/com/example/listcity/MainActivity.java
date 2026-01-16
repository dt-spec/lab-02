package com.example.listcity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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

    Button btnAddCity, btnDeleteCity;
    int selectedIndex = -1; // stores which item you tapped

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

        // Views
        cityList = findViewById(R.id.cityList);
        btnAddCity = findViewById(R.id.btnAddCity);
        btnDeleteCity = findViewById(R.id.btnDeleteCity);

        // Data
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        datalist = new ArrayList<>(Arrays.asList(cities));

        // IMPORTANT: because your content.xml TextView is @+id/content_view
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.content_view, datalist);
        cityList.setAdapter(cityAdapter);

        // Tap to select a city
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
            Toast.makeText(this, "Selected: " + datalist.get(position), Toast.LENGTH_SHORT).show();
        });

        // Add City button
        btnAddCity.setOnClickListener(v -> showAddCityDialog());

        // Delete City button
        btnDeleteCity.setOnClickListener(v -> {
            if (selectedIndex == -1) {
                Toast.makeText(this, "Tap a city first to select it.", Toast.LENGTH_SHORT).show();
                return;
            }

            String removed = datalist.remove(selectedIndex);
            cityAdapter.notifyDataSetChanged();
            selectedIndex = -1;

            Toast.makeText(this, "Deleted: " + removed, Toast.LENGTH_SHORT).show();
        });
    }

    private void showAddCityDialog() {
        EditText input = new EditText(this);
        input.setHint("Enter city name");

        new AlertDialog.Builder(this)
                .setTitle("Add City")
                .setView(input)
                .setPositiveButton("CONFIRM", (dialog, which) -> {
                    String city = input.getText().toString().trim();

                    if (TextUtils.isEmpty(city)) {
                        Toast.makeText(this, "City name cannot be empty.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    datalist.add(city);
                    cityAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Added: " + city, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("CANCEL", (dialog, which) -> dialog.dismiss())
                .show();
    }
}