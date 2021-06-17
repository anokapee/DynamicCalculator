package com.dynamicdevz.dynamiccalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AlphabetIndexer;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Operation{
        ADDITION,
        SUBTRACTION,
        DIVISION,
        MULTIPLY,
        MODULUS
    }
    private double current;
    private Operation Op = null;
    private TextView outputTV;

    public static final String NUM_KEY = "KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outputTV = findViewById(R.id.value_tv);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NUM_KEY, outputTV.getText().toString().trim());
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        outputTV.setText(savedInstanceState.getString(NUM_KEY));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
        outputTV = findViewById(R.id.value_tv);
    }

    public void onCalcClick(View view){
        switch (view.getId()){
            case R.id.one_btn:
                handleClick(1);
                break;
            case R.id.two_btn:
                handleClick(2);
                break;
            case R.id.three_btn:
                handleClick(3);
                break;
            case R.id.four_btn:
                handleClick(4);
                break;
            case R.id.five_btn:
                handleClick(5);
                break;
            case R.id.six_btn:
                handleClick(6);
                break;
            case R.id.seven_btn:
                handleClick(7);
                break;
            case R.id.eight_btn:
                handleClick(8);
                break;
            case R.id.nine_btn:
                handleClick(9);
                break;
            case R.id.zero_btn:
                handleClick(0);
                break;
            case R.id.div_btn:
                operation(Operation.DIVISION);
                break;
            case R.id.mult_btn:
                operation(Operation.MULTIPLY);
                break;
            case R.id.add_btn:
                operation(Operation.ADDITION);
                break;
            case R.id.minus_btn:
                operation(Operation.SUBTRACTION);
                break;
            case R.id.equal_btn:
                calculateOutput();
                break;
            case R.id.ac_btn:
                clearAll();
                break;
            case R.id.modulus_btn:
                operation(Operation.MODULUS);
                break;
            case R.id.neg_btn:
                negate();
                break;
            case R.id.sqrt_btn:
                square();
                break;
            case R.id.log_btn:
                log();
                break;
            case R.id.sin_btn:
                sin();
                break;
            case R.id.cos_btn:
                cos();
                break;
            case R.id.tan_btn:
                tan();
                break;
        }
    }

    private void tan() {
        Double tan = Math.tan(Double.parseDouble(outputTV.getText().toString().trim()));
        outputTV.setText(tan + "");
    }

    private void cos() {
        Double cos = Math.cos(Double.parseDouble(outputTV.getText().toString().trim()));
        outputTV.setText(cos + "");
    }

    private void sin() {
        Double sin = Math.sin(Double.parseDouble(outputTV.getText().toString().trim()));
        outputTV.setText(sin + "");
    }

    private void log() {
        Double log = Math.log(Double.parseDouble(outputTV.getText().toString().trim()));
        outputTV.setText(log + "");
    }

    private void square() {
        Double squr = Math.sqrt(Double.parseDouble(outputTV.getText().toString().trim()));
        outputTV.setText(squr + "");
    }

    private void negate() {
        Double neg = -1 * (Double.parseDouble(outputTV.getText().toString().trim()));
        outputTV.setText(neg + "");
    }

    private void clearAll() {
        Op = null;
        outputTV.setText("0");
        current = 0.0;
    }

    private void calculateOutput() {
        if (Op == null) {
            new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_AppCompat))
                    .setTitle("Operation error!")
                    .setMessage("Please select operator.")
                    .setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
            return;
        }
        double ans = 0.0;
        switch(Op){
            case ADDITION:
                ans = current + Double.parseDouble(outputTV.getText().toString().trim());
                outputTV.setText(ans + "");
                break;
            case SUBTRACTION:
                ans = current - Double.parseDouble(outputTV.getText().toString().trim());
                outputTV.setText(ans + "");
                break;
            case DIVISION:
                ans = current / Double.parseDouble(outputTV.getText().toString().trim());
                outputTV.setText(ans + "");
                break;
            case MULTIPLY:
                ans = current * Double.parseDouble(outputTV.getText().toString().trim());
                outputTV.setText(ans + "");
                break;
            case MODULUS:
                ans = current % Double.parseDouble(outputTV.getText().toString().trim());
                outputTV.setText(ans + "");
                break;

        }
    }

    private void operation(Operation operation) {
        Op = operation;
        current = Double.parseDouble(outputTV.getText().toString().trim());
        outputTV.setText("0");
    }

    private void handleClick(int i) {
        //Toast.makeText(this, "Pressed : "+ i, Toast.LENGTH_SHORT).show();

        String curr = outputTV.getText().toString();
        if(Double.parseDouble(curr) > 0)
        {
            outputTV.setText(curr + i);
        }
        else outputTV.setText(i+"");
    }
}