package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayList<String> dataList;
    ArrayAdapter<String> cityAdapter;
    Button addButton;
    Button deleteButton;
    Button inputButton;
    EditText inputField;


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


        /* lab-02 */

        cityList = findViewById(R.id.city_list);
        dataList = new ArrayList<>();
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addButton = findViewById(R.id.add_city_button);
        deleteButton = findViewById(R.id.delete_city_button);
        inputButton = findViewById(R.id.input_button);
        inputField = findViewById(R.id.input_field);

        addButton.setOnClickListener(v -> {
            inputButton.setVisibility(View.VISIBLE);
            inputField.setVisibility(View.VISIBLE);
            inputButton.setTag("ADD");
        });

        deleteButton.setOnClickListener(v -> {
            inputButton.setVisibility(View.VISIBLE);
            inputField.setVisibility(View.VISIBLE);
            inputButton.setTag("DELETE");
        });

        inputButton.setOnClickListener(v -> {
            String inputText = inputField.getText().toString().trim();
            if(inputText.isEmpty()){
                Toast.makeText(MainActivity.this, "Please enter something", Toast.LENGTH_SHORT).show();
            } else if (inputButton.getTag().equals("ADD")){
                dataList.add(inputText);
                Toast.makeText(MainActivity.this, "City Added", Toast.LENGTH_SHORT).show();
            } else if (inputButton.getTag().equals("DELETE")){
                if (dataList.contains(inputText)){
                    dataList.remove(inputText);
                    Toast.makeText(MainActivity.this, "City Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "City Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            /* update adapter and make input layout disappear */
            cityAdapter.notifyDataSetChanged();
            inputField.setText("");
            inputField.setVisibility(View.GONE);
            inputButton.setVisibility(View.GONE);
        });



    }
}