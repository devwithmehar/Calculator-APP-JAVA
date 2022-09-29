package com.example.calculator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public  static  final String NAME= "com.example.calculator.NAME";
    public  static  final String AGE= "com.example.calculator.AGE";
    public static final int DATA_REQUEST_CODE = 521;
    Button btnNext;
    Button btnAdd;
    Button btnSubtract;
    Button btnDivide;
    Button btnMultiply;
    Button btnFactorial;
    EditText etFirstNumber;
    EditText etSecondNumber;
    EditText etResult;
    EditText etName;
    EditText etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext = findViewById(R.id.btnnext);
        btnNext.setOnClickListener(nextActivity);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(showResult);

        btnSubtract = findViewById(R.id.btnSubtract);
        btnSubtract.setOnClickListener(showResult);

        btnDivide = findViewById(R.id.btnDivide);
        btnDivide.setOnClickListener(divideNumber);

        btnMultiply = findViewById(R.id.btnMultiply);
        btnMultiply.setOnClickListener(showResult);

        btnFactorial = findViewById(R.id.btnFactorial);
        btnFactorial.setOnClickListener(factorialOfNumber);

        etFirstNumber =  findViewById(R.id.etFirstNumber);
        etSecondNumber =  findViewById(R.id.etSecondNumber);
        etResult = findViewById(R.id.etShowResult);
        etName = findViewById(R.id.etNameInput);
        etAge = findViewById(R.id.etAgeInput);

    }

    View.OnClickListener nextActivity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,Activity_Save.class);

            startActivityForResult(intent,DATA_REQUEST_CODE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == DATA_REQUEST_CODE && resultCode == RESULT_OK){
            String name = data.getStringExtra(NAME);
            String age = data.getStringExtra(AGE);

            etName.setText(name);
            etAge.setText(age);
        }

    }

    // For the calculation
    View.OnClickListener showResult= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(etFirstNumber.getText().toString().equals("")){
                etFirstNumber.setError("First Number is required");
            }
            else if(etSecondNumber.getText().toString().equals("")){
                etSecondNumber.setError("Second Number is required");
            }
            else {

                try {

                    Integer firstNum = Integer.parseInt(etFirstNumber.getText().toString());
                    Integer secondNum = Integer.parseInt(etSecondNumber.getText().toString());
                    Integer result;
                    String firstNumber = etFirstNumber.getText().toString();
                    String secondNumber = etSecondNumber.getText().toString();


                    // Add the number
                    if (view.getId() == R.id.btnAdd) {


                        Toast.makeText(MainActivity.this, firstNumber, Toast.LENGTH_SHORT).show();
                        if (firstNumber == "") {
                            Toast.makeText(MainActivity.this, "There is no first number", Toast.LENGTH_SHORT).show();
                        } else {

                            result = firstNum + secondNum;
                            String displayResult = result.toString();
                            etResult.setText(displayResult);
                        }

                    }
                    // Subtract the number
                    else if (view.getId() == R.id.btnSubtract) {
                        if (firstNum < secondNum) {
                            Toast.makeText(MainActivity.this, "First Number should be Greater than Second Number", Toast.LENGTH_SHORT).show();
                        } else {
                            result = firstNum - secondNum;
                            String displayResult = result.toString();
                            etResult.setText(displayResult);
                        }
                    }
                    // Mutiple the number
                    else if (view.getId() == R.id.btnMultiply) {
                        result = firstNum * secondNum;
                        String displayResult = result.toString();
                        etResult.setText(displayResult);

                    }

                } catch (NumberFormatException e) {
                    e.getMessage();
                }
            }

        }
    };


    View.OnClickListener divideNumber = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(etFirstNumber.getText().toString().equals("")){
                etFirstNumber.setError("First Number is required");
            }
            else if(etSecondNumber.getText().toString().equals("")){
                etSecondNumber.setError("Second Number is required");
            }
            else {
                try {

                    Float firstNum = Float.parseFloat(etFirstNumber.getText().toString());
                    Float secondNum = Float.parseFloat(etSecondNumber.getText().toString());


                    Float result = firstNum / secondNum;
                    String displayResult = result.toString();
                    etResult.setText(displayResult);


                } catch (NumberFormatException e) {
                    e.getMessage();
                }
            }
        }
    };


    View.OnClickListener factorialOfNumber = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(etFirstNumber.getText().toString().equals("")){
                etFirstNumber.setError("First Number is required");
            }else {
                try {
                    Integer firstNum = Integer.parseInt(etFirstNumber.getText().toString());
                    Integer factorial = 1;
                    if (firstNum.equals("")) {
                        etFirstNumber.setError("First Number is required");
                    } else {
                        for (Integer i = 1; i <= firstNum; i++) {
                            factorial = factorial * i;
                        }
                        Integer result = factorial;
                        String displayResult = result.toString();
                        etResult.setText(displayResult);
                    }

                } catch (NumberFormatException e) {
                    e.getMessage();
                }
            }
        }
    };


}