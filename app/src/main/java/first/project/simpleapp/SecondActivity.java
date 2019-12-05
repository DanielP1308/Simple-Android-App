package first.project.simpleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

class Operations {

    static float num1 ;
    static String str = "" ;

    //Numbers
    public static float zero(float num1)  {
        return 0 ;
    }

    public static float one(float num1)  {
        return 1;
    }

    public static float two(float num1)  {
        return 2;
    }

    public static float three(float num1)  {
        return 3;
    }

    public static float four(float num1)  {
        return 4;
    }

    public static float five(float num1)  {
        return 5;
    }

    public static float six(float num1)  {
        return 6;
    }

    public static float seven(float num1)  {
        return 7;
    }

    public static float eight(float num1)  {
        return 8;
    }

    public static float nine(float num1)  {
        return 9;
    }

    //Operations
    public static String clear() {
        return "" ;
    }

    public static String plus()  {
        return "+";
    }

    public static String minus()  {
        return "-";
    }

    public static String multiply()  {
        return "*";
    }

    public static String divide()  {
        return "/";
    }

    public static String opBracket()  {
        return "(";
    }

    public static String clBracket()  {
        return ")";
    }

    public static String power()  {
        return "^" ;
    }

    public static String sqr()  {
        return "sqrt" ;
    }
    public static String decimal()  {
        return "." ;
    }

    // Delete method
    public  String back(String str) {
        int position = str.length() -1;

        StringBuilder builder = new StringBuilder(str);
        if (builder.charAt(position) == 't') { //if the last character is 't' delete characters from that position to position -3 (only used on sqrt function)
            for (int i = 0; i <= 3; i++) {
                builder.deleteCharAt(str.length() -1) ;
                str = builder.toString() ;
            }// End for loop
        }// End if
        else {
            builder.deleteCharAt(str.length() -1) ;
            str = builder.toString() ;
        }// End else

        return str ;
    }

    // Method for evaluating the String
    public static String evaluation(final String str) {
        return new Object() {
            int position = -1 ;
            int charAtPosition ;

            public void nextNumber() {
                charAtPosition = (++position < str.length()) ? str.charAt(position) : -1; // If the position is less than length of a string then return character at that position otherwise return -1
            }//End nextNumber()

            public boolean eat(int charToEat) {
                boolean tOrF = false ;
                while (charAtPosition == ' ') { //While ch is equal to empty go to nextNumber method and do next charAt
                    nextNumber();
                }
                if (charAtPosition == charToEat) {
                    nextNumber();
                    tOrF = true ;
                }//End if in eat()

                return tOrF;

            }//End eat()

            public String parse() {
                nextNumber();
                float num = (float) parseExpression();
                if (position < str.length()) ;
                String str = "" ;
                str += (float) num ;
                return str;
            }//End parse

            public float parseFactor() {
                if (eat('+')) {
                    return parseFactor(); // Plus
                }
                if (eat('-')) {
                    return -parseFactor(); // Minus
                }

                float num = 0 ;
                int startPos = this.position;

                if (eat('(')) { // Brackets
                    num = parseExpression();
                    eat(')');
                }

                else if ((charAtPosition >= '0' && charAtPosition <= '9') || charAtPosition == '.') { // numbers

                    while ((charAtPosition >= '0' && charAtPosition <= '9') || charAtPosition == '.') {
                        nextNumber();
                    }
                    num = Float.parseFloat(str.substring(startPos, this.position));
                }

                else if (charAtPosition >= 'a' && charAtPosition <= 'z') { // functions

                    while (charAtPosition >= 'a' && charAtPosition <= 'z') {
                        nextNumber();
                    }

                    String function = str.substring(startPos, this.position);
                    num = parseFactor();
                    if (function.equals("sqrt")) {
                        num = (float) Math.sqrt(num); // Square root
                    } // End if sqrt
                }

                if (eat('^')) {
                    num = (float) Math.pow(num, parseFactor()); // power of
                }

                return num;
            }// End parseFactor()

            public float parseTerm() { //Makes sure that multiplication and division are done first
                float num = parseFactor();

                while(true) {
                    if (eat('*')) {
                        num *= parseFactor(); // multiplication
                    }
                    else if (eat('/')) {
                        num /= parseFactor(); // division
                    }
                    else {
                        return num;
                    }
                }
            }//End parseTerm

            public float parseExpression() { //This is called after multiplication and division are done
                float num = parseTerm();

                while(true) {
                    if      (eat('+')) {
                        num += parseTerm(); // addition
                    }
                    else if (eat('-')) {
                        num -= parseTerm(); // subtraction
                    }
                    else {
                        return num;
                    }
                }//End for in parseExpresion
            }//End pasreExpresion()

        }.parse();
    }//End evaluation

}//End class
public class SecondActivity extends AppCompatActivity {

    Button zero, one, two, three, four, five, six, seven, eight, nine, power, multiply, divide, equal, plus, minus, clear, Ans, sqrt, opBracket, clBracket, back, decimal;
    EditText editText;
    Intent get = getIntent();
    private float num1 = 0;
    private String str = "" ;
    private String answer = "" ;
    private boolean tOrF = false ;
    Operations numberMethods = new Operations();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText = findViewById(R.id.editText);


        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        power = findViewById(R.id.power);
        multiply = findViewById(R.id.multiply);
        divide = findViewById(R.id.divide);
        equal = findViewById(R.id.equal);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        clear = findViewById(R.id.clear);
        Ans = findViewById(R.id.Ans);
        sqrt = findViewById(R.id.sqrt);
        opBracket = findViewById(R.id.opBracket);
        clBracket = findViewById(R.id.clBracket);
        back = findViewById(R.id.back);
        decimal = findViewById(R.id.decimal);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = numberMethods.one(num1);
                if (tOrF) {
                    str = numberMethods.clear();
                    str += (int) num1 ;
                    tOrF = false;
                }
                else {
                    str += (int) num1 ;
                }
                editText.setText(str);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = numberMethods.two(num1);
                if (tOrF) {
                    str = numberMethods.clear();
                    str += (int) num1 ;
                    tOrF = false;
                }
                else {
                    str += (int) num1 ;
                }
                editText.setText(str);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = numberMethods.three(num1);
                if (tOrF) {
                    str = numberMethods.clear();
                    str += (int) num1 ;
                    tOrF = false;
                }
                else {
                    str += (int) num1 ;
                }
                editText.setText(str);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = numberMethods.four(num1);
                if (tOrF) {
                    str = numberMethods.clear();
                    str += (int) num1 ;
                    tOrF = false;
                }
                else {
                    str += (int) num1 ;
                }
                editText.setText(str);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = numberMethods.five(num1);
                if (tOrF) {
                    str = numberMethods.clear();
                    str += (int) num1 ;
                    tOrF = false;
                }
                else {
                    str += (int) num1 ;
                }
                editText.setText(str);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = numberMethods.six(num1);
                if (tOrF) {
                    str = numberMethods.clear();
                    str += (int) num1 ;
                    tOrF = false;
                }
                else {
                    str += (int) num1 ;
                }
                editText.setText(str);
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = numberMethods.seven(num1);
                if (tOrF) {
                    str = numberMethods.clear();
                    str += (int) num1 ;
                    tOrF = false;
                }
                else {
                    str += (int) num1 ;
                }
                editText.setText(str);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = numberMethods.eight(num1);
                if (tOrF) {
                    str = numberMethods.clear();
                    str += (int) num1 ;
                    tOrF = false;
                }
                else {
                    str += (int) num1 ;
                }
                editText.setText(str);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = numberMethods.nine(num1);
                if (tOrF) {
                    str = numberMethods.clear();
                    str += (int) num1 ;
                    tOrF = false;
                }
                else {
                    str += (int) num1 ;
                }
                editText.setText(str);
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = numberMethods.zero(num1);
                str += (int) num1 ;
                editText.setText(str);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str += numberMethods.plus() ;
                editText.setText(str);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str += numberMethods.minus() ;
                editText.setText(str);
            }
        });
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str += numberMethods.power() ;
                editText.setText(str);
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str += numberMethods.multiply() ;
                editText.setText(str);
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str += numberMethods.divide() ;
                editText.setText(str);
            }
        });
        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str += numberMethods.sqr() ;
                editText.setText(str);
            }
        });
        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str += numberMethods.decimal() ;
                editText.setText(str);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = numberMethods.back(str) ;
                editText.setText(str);
            }
        });
        opBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str += numberMethods.opBracket() ;
                editText.setText(str);
            }
        });
        clBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str += numberMethods.clBracket() ;
                editText.setText(str);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = numberMethods.clear() ;
                editText.setText(str);
            }
        });
        Ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tOrF == true) {
                    str = numberMethods.clear() ;
                    str += answer ;
                    tOrF = false ;
                }
                else {
                    str += answer ;
                }

                editText.setText(str) ;
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = numberMethods.evaluation(str) ;
                editText.setText(str);
                answer = str ;
                tOrF = true ;
            }
        });
    }
}
