package com.example.ameen.calculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.ParseException;

public class MainActivity extends ActionBarActivity {

    double result = 0;
    double x;
    double memory = 0;
    EditText disp;
    final static int mul = 1, div = 2, add = 3, sub = 4;
    int operation = 0;
    boolean flag = false;
    boolean dotFlag = flag;
    boolean evalFlag = false;


    DecimalFormat df = new DecimalFormat("#.#");
    Button zero, one, two, three, four, five, six, seven, eight, nine, dot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        disp = (EditText) findViewById(R.id.Result_view);
        zero = (Button) findViewById(R.id.zero);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        dot = (Button) findViewById(R.id.dot);

        df.setDecimalSeparatorAlwaysShown(false);
        df.setMaximumIntegerDigits(8);
        df.setMaximumFractionDigits(5);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clear(View v) {
        disp.setText(df.format(0));
        flag = false;
        dotFlag = false;
        operation = 0;

    }

    public void memClear(View v) {
        memory = 0;
        flag = false;
        dotFlag = false;
    }

    public void memLoad(View v) {
        disp.setText(df.format(memory));
        try {
            result = df.parse(disp.getText().toString()).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dotFlag = false;
        flag = false;
    }

    public void memStore(View v) {
        try {
            memory = df.parse(disp.getText().toString()).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        disp.setText("0");
        dotFlag = false;
        flag = false;

    }

    public void back(View v) {
        if (flag == true) {
            int length = disp.getText().length();
            if (length > 1) {
                disp.getText().delete(length - 1, length);
            } else {
                disp.setText("0");
                flag = false;
                dotFlag = false;

            }
        }
    }

    public void negate(View v) {
        try {
            result = df.parse(disp.getText().toString()).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (result != 0) {
            result *= -1;
            disp.setText(df.format(result));
            flag = true;
        }
    }

    public void num(View v) {
        String temp = "";
        if (evalFlag) {
            operation = 0;
            evalFlag = flag;
        }


        Button b = (Button) findViewById(v.getId());

        temp = b.getText().toString();
        if (flag) {
            if (v.getId() == R.id.dot) {
                if (!dotFlag) {
                    dotFlag = true;
                    disp.append(temp);
                    flag = true;
                }

            } else {

                disp.append(temp);
                flag = true;
            }


        } else {
            if (v.getId() == R.id.zero) {

                flag = false;
            } else if (v.getId() == R.id.dot) {
                if (!dotFlag) {
                    dotFlag = true;
                    flag = true;
                    temp = "0.";
                    disp.setText(temp);
                }
            } else {
                flag = true;
                disp.setText(temp);
            }

        }


    }

    public void mul(View v) {
        try {
            result = df.parse(disp.getText().toString()).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        operation = mul;
        flag = false;
        dotFlag = false;
        evalFlag = false;
    }

    public void div(View v) {
        try {
            result = df.parse(disp.getText().toString()).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        operation = div;
        flag = false;
        dotFlag = false;
        evalFlag = false;
    }

    public void add(View v) {
        try {
            result = df.parse(disp.getText().toString()).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        operation = add;
        flag = false;
        dotFlag = false;
        evalFlag = false;
    }

    public void sub(View v) {
        try {
            result = df.parse(disp.getText().toString()).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        operation = sub;
        flag = false;
        dotFlag = false;
        evalFlag = false;
    }


    public void eval(View v) {
        switch (operation) {
            case 0:
                try {
                    result = df.parse(disp.getText().toString()).doubleValue();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                disp.setText(df.format(result));
                break;
            case mul:
                try {
                    if (!evalFlag) {
                        x = df.parse(disp.getText().toString()).doubleValue();
                    } else {
                        result = df.parse(disp.getText().toString()).doubleValue();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                disp.setText(df.format(result * x));

                break;
            case add:
                try {
                    if (!evalFlag) {
                        x = df.parse(disp.getText().toString()).doubleValue();
                    } else {
                        result = df.parse(disp.getText().toString()).doubleValue();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                disp.setText(df.format(result + x));
                break;
            case sub:
                try {
                    if (!evalFlag) {
                        x = df.parse(disp.getText().toString()).doubleValue();
                    } else {
                        result = df.parse(disp.getText().toString()).doubleValue();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                disp.setText(df.format(result - x));
                break;
            case div:
                try {
                    if (!evalFlag) {
                        x = df.parse(disp.getText().toString()).doubleValue();
                    } else {
                        result = df.parse(disp.getText().toString()).doubleValue();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (x != 0.0) {

                    disp.setText(df.format(result / x));
                } else {
                    disp.setText("Error x/0");
                }

                break;
            default:
                break;
        }
        flag = false;
        dotFlag = false;
        evalFlag = true;
    }


}
