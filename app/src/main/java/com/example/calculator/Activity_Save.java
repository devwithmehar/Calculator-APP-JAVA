package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;


public class Activity_Save extends AppCompatActivity {
FloatingActionButton btnBirthDate;
TextView tvDate;
TextView tvAge;
TextView tvBirthDate;
EditText etName;
Button btnSave;
Button btnLoad;
SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

      btnBirthDate = findViewById(R.id.btnChooseDate);
      tvDate = findViewById(R.id.tvDate);
      tvAge = findViewById(R.id.tvAgeResult);
      tvBirthDate = findViewById(R.id.tvBirthDate);
      etName = findViewById(R.id.etName);

      btnBirthDate.setOnClickListener(showDialog);

      btnSave = findViewById(R.id.btnSave);
      btnSave.setOnClickListener(saveInfo);

      btnLoad = findViewById(R.id.btnLoad);
      btnLoad.setOnClickListener(loadInfo);
        SharedPreferences sharePref = getPreferences(Context.MODE_PRIVATE);
        editor = sharePref.edit();


        Intent passBackIntent = new Intent();

    }
    // It will save the name and age in the share prefernces
    View.OnClickListener saveInfo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String name;
            String yourAge;

            name = etName.getText().toString();
            yourAge = tvAge.getText().toString();

            // Store the Data
            editor.putString(getString(R.string.key_for_name),name);
            editor.putString(getString(R.string.key_for_age),yourAge);


            // Save the Date
            editor.apply();

            SharedPreferences sharePref = getPreferences(Context.MODE_PRIVATE);
            String getName = sharePref.getString(getString(R.string.key_for_name),"");
            String getAge = sharePref.getString(getString(R.string.key_for_age),"");

            if(getName.equalsIgnoreCase("") && getAge.equalsIgnoreCase("")){
                Toast.makeText(Activity_Save.this,"Data is not stored successfully",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(Activity_Save.this,"Data is  stored successfully",Toast.LENGTH_SHORT).show();
            }

        }
    };

    // Display the information which is saved
    View.OnClickListener loadInfo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedPreferences sharePref = getPreferences(Context.MODE_PRIVATE);
            String name = sharePref.getString(getString(R.string.key_for_name),"");
            String yourAge = sharePref.getString(getString(R.string.key_for_age),"");

            if(name.equalsIgnoreCase("") ){
                Toast.makeText(Activity_Save.this,"No data to show",Toast.LENGTH_SHORT).show();
            }
            else if (yourAge.equalsIgnoreCase("")){
                Toast.makeText(Activity_Save.this,"No data to show",Toast.LENGTH_SHORT).show();
            }
          else{

            etName.setText(name);
            tvAge.setText(yourAge);

            Toast.makeText(Activity_Save.this,"Congrat ! Your data is loaded now",Toast.LENGTH_SHORT).show();
            }
        }
    };

    View.OnClickListener showDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    Activity_Save.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    setListener,year,month,day);

            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            datePickerDialog.show();
        }

    };

    private DatePickerDialog.OnDateSetListener setListener = new
            DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day){
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.YEAR,year);
                    c.set(Calendar.MONTH,month);
                    c.set(Calendar.DAY_OF_MONTH,day);
                    Integer age;

                    String format = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
                    tvBirthDate.setText(format);

                    Calendar currentDate = Calendar.getInstance();
                    if(currentDate.get(Calendar.DAY_OF_MONTH) < c.get(Calendar.DAY_OF_MONTH)){
                         age = currentDate.get(Calendar.YEAR) - c.get(Calendar.YEAR);
                        age --;
                    }else {
                         age = currentDate.get(Calendar.YEAR) - c.get(Calendar.YEAR);
                    }
                    String currentAge = age.toString();

                    tvAge.setText(currentAge);
                }
        };

    @Override
    public void onBackPressed() {
        String name = etName.getText().toString();
        String yourAge = tvAge.getText().toString();

        if(name.equals("") ){
             AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Title")
                    .setMessage("Name is required")
                    .setPositiveButton("Ok", null)
                    .setNegativeButton("Cancel", null)
                    .show();

        }

   else {
            Intent passBackIntent = new Intent();
            passBackIntent.putExtra(MainActivity.NAME, name);
            passBackIntent.putExtra(MainActivity.AGE, yourAge);
            setResult(RESULT_OK, passBackIntent);


            super.onBackPressed();
        }
    }
}