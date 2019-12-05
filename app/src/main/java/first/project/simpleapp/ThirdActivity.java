package first.project.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

public class ThirdActivity extends AppCompatActivity {

    Button convert;
    EditText editText, label;
    String var;
    RadioButton temperature;
    RadioButton currency;
    Switch reverse;
    double num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        convert = findViewById(R.id.editText);
        editText = findViewById(R.id.editText3);
        label = findViewById(R.id.editText2);
        temperature = findViewById(R.id.temeprature);
        currency = findViewById(R.id.currency);
        reverse = findViewById(R.id.reverse);

        currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currency.isChecked()) {
                    temperature.setChecked(false);
                    if (!reverse.isChecked()) {
                        label.setText("Euro to Pound");
                    }
                    else {
                        label.setText("Pound to Euro");
                    }
                }
            }
        });
        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temperature.isChecked()) {
                    currency.setChecked(false);
                    if (!reverse.isChecked()) {
                        label.setText("Celsius to Fahrenheit");
                    }
                    else {
                        label.setText("Fahrenheit to Celsius");
                    }
                }
            }
        });
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!reverse.isChecked()) {
                    if (temperature.isChecked()) {
                        //from Celsius to Fahrenheit
                        var = editText.getText().toString();
                        num = Double.parseDouble(var);
                        num = (num * 9 / 5) + 32;
                        var = Double.toString(num);
                        editText.setText(var);
                    }
                    else if (currency.isChecked()) {
                        //Euro to Pound
                        var = editText.getText().toString();
                        num = Double.parseDouble(var);
                        num = num * 0.79;
                        var = Double.toString(num);
                        editText.setText(var);
                    }
                }
                else {
                    if (temperature.isChecked()) {
                        //From Fahrenheit to Celsius
                        var = editText.getText().toString();
                        num = Double.parseDouble(var);
                        num = (num - 32) * 5 / 9;
                        var = Double.toString(num);
                        editText.setText(var);
                    }
                    else if (currency.isChecked()) {
                        var = editText.getText().toString();
                        num = Double.parseDouble(var);
                        num = num * 1.18;
                        var = Double.toString(num);
                        editText.setText(var);
                    }
                }
            }
        });
    }
}
