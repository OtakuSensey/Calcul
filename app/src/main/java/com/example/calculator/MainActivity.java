package com.example.calculator;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.RoundingMode;
import java.text.DecimalFormat;


/**
 * Окно Калькулятора
 */
public class MainActivity extends AppCompatActivity {

   //Кнопки
    Button mBtn0;
    Button mBtn1;
    Button mBtn2;
    Button mBtn3;
    Button mBtn4;
    Button mBtn5;
    Button mBtn6;
    Button mBtn7;
    Button mBtn8;
    Button mBtn9;
    Button mBtnS;
    Button mBtnD;
    Button mBtnU;
    Button mBtnDEL;
    Button mBtnM;
    Button mBtnP;
    Button mBtnR;
    Button mBtnZ;
    Button mBtnPr;
    Button mBtnPM;
    TextView mDisplay;
    TextView mDisplay1;
    TextView mDisplay2;
    TextView mDisplay3;
    float mValue = 0;
    String mOperator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeManager.setTheme(this);
        setContentView(R.layout.activity_main);
        mBtn0 = findViewById(R.id.btn0);
        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mBtn3 = findViewById(R.id.btn3);
        mBtn4 = findViewById(R.id.btn4);
        mBtn5 = findViewById(R.id.btn5);
        mBtn6 = findViewById(R.id.btn6);
        mBtn7 = findViewById(R.id.btn7);
        mBtn8 = findViewById(R.id.btn8);
        mBtn9 = findViewById(R.id.btn9);
        mBtnS = findViewById(R.id.btnS);
        mBtnD = findViewById(R.id.btnD);
        mBtnU = findViewById(R.id.btnU);
        mBtnDEL = findViewById(R.id.btnDEL);
        mBtnM = findViewById(R.id.btnM);
        mBtnR = findViewById(R.id.btnR);
        mBtnZ = findViewById(R.id.btnZ);
        mBtnP = findViewById(R.id.btnP);
        mBtnPr = findViewById(R.id.btnPR);
        mBtnPM = findViewById(R.id.btnPM);
        mDisplay = findViewById(R.id.Display);
        mDisplay1 = findViewById(R.id.display1);
        mDisplay2 = findViewById(R.id.display2);
        mDisplay3 = findViewById(R.id.display3);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }

            private void onNumberClick(View button) {

                String number = ((Button) button).getText().toString();
                String display = getDisplayString(mDisplay);
                String display2 = getDisplayString(mDisplay2);
                if (display.equals("0"))
                    display = number;
                else
                    display += number;

                displayText(display, display2);
            }


        };
        //Подписки
        mBtn0.setOnClickListener(numberListener);
        mBtn1.setOnClickListener(numberListener);
        mBtn2.setOnClickListener(numberListener);
        mBtn3.setOnClickListener(numberListener);
        mBtn4.setOnClickListener(numberListener);
        mBtn5.setOnClickListener(numberListener);
        mBtn6.setOnClickListener(numberListener);
        mBtn7.setOnClickListener(numberListener);
        mBtn8.setOnClickListener(numberListener);
        mBtn9.setOnClickListener(numberListener);
/**
 * обработка нажаьтя на числовую кнопку
 * @param button - кнопка
 */
        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorListener(v);
            }


            public void onOperatorListener(View button) {


                String operator = ((Button) button).getText().toString();
                mOperator = operator;
                String display = mDisplay.getText().toString();
                mValue = Float.parseFloat(display);

                mDisplay.setText("0");
                mDisplay2.setText(operator);
            }

        };

        mBtnP.setOnClickListener(operatorListener);
        mBtnM.setOnClickListener(operatorListener);
        mBtnD.setOnClickListener(operatorListener);
        mBtnU.setOnClickListener(operatorListener);
        mBtnPr.setOnClickListener(operatorListener);

        /**
         *обработка нжатия на оператор
         * @program button - кнопка
         */


        View.OnClickListener resultListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResultListener(v);
            }

            public void onResultListener(View button) {

                //1
                String display = mDisplay.getText().toString();
                float value = Float.parseFloat(display);
                //2
                float result = value;
                boolean flag = false;
                //3
                switch (mOperator) {


                    case "+": {
                        result = value + mValue;
                        break;
                    }
                    case "-": {
                        result = mValue - value;
                        break;
                    }
                    case "*": {
                        result = mValue * value;
                        break;
                    }
                    case "/": {
                        if (value == 0) {
                            flag = true;
                        } else {
                            flag = false;
                            result = mValue / value;
                        }
                        break;
                    }
                }

                String resultText = floatToStringForDisplay(result);

                if (flag) {
                    mDisplay.setTextSize(24);
                    mDisplay.setText(getResources().getString(R.string.error_zero));

                } else {
                    mDisplay.setText(resultText);
                    mValue = result;
                    mOperator = "";
                }
            }
        };
        mBtnR.setOnClickListener(resultListener);
        View.OnClickListener clearListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearListener(v);
            }

            public void onClearListener(View button) {
                mValue = 0;
                mOperator = "";
                mDisplay.setText("0");
                mDisplay1.setText("");
                mDisplay2.setText("");
                mDisplay3.setText("");
            }
        };
        mBtnS.setOnClickListener(clearListener);

        View.OnClickListener backspaceListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackspaceListener(v);
            }

            public void onBackspaceListener(View button) {
                String display = getDisplayString(mDisplay);
                String display2 = getDisplayString(mDisplay2);

                display = display.substring(0, display.length() - 1);
                displayText(display, display2);
            }
        };
        mBtnDEL.setOnClickListener(backspaceListener);

        View.OnClickListener changeSinListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeSinListener(v);
            }

            public void onChangeSinListener(View button) {
                String display2 = getDisplayString(mDisplay);
                float value = getDisplayFloat(mDisplay);

                value = value * -1;
                String resultText = floatToStringForDisplay(value);
                displayText(resultText, display2);
            }
        };
        mBtnPM.setOnClickListener(changeSinListener);

        View.OnClickListener commaListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommaListener(v);
            }

            public void onCommaListener(View button) {
                String display = getDisplayString(mDisplay);
                String display2 = getDisplayString(mDisplay2);

                display = display + ".";

                displayText(display, display2);
            }
        };

        mBtnZ.setOnClickListener(commaListener);

        View.OnClickListener percentListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPercentListener(v);
            }

            public void onPercentListener(View button) {
                String display2 = getDisplayString(mDisplay);
                float value = getDisplayFloat(mDisplay);

                value = value / 100;
                String resultText = floatToStringForDisplay(value);
                displayText(resultText);
            }
        };
        mBtnPr.setOnClickListener(percentListener);

    }

    private void displayText(String display, String display2) {
        display = display.replace('.', '.');
        mDisplay.setText(display);

        if (display2.equals("")) {
            mDisplay1.setText(display);
        } else {
            mDisplay3.setText(display);
        }
    }

    private void displayText(String display) {
        display = display.replace('.', '.');
        mDisplay.setText(display);
    }

    private String floatToStringForDisplay(float result) {
        //4
        DecimalFormat format = new DecimalFormat(("0.######"));
        format.setRoundingMode(RoundingMode.DOWN);
        return format.format(result);
    }

    private String getDisplayString(TextView mDisplay) {
        String display = mDisplay.getText().toString();
        display = display.replace(',', '.');
        return display;
    }

    private Float getDisplayFloat(TextView mDisplay) {
        String display = getDisplayString(mDisplay);
        float value = Float.parseFloat(display);
        return value;
    }

    /**
     * Создание меню
     *
     * @param menu - объект меню
     * @return успешность обрабоки
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    /**
     * Обработка нажатий меню
     *
     * @param item - элемент меню
     * @return успешность обработки
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startSettings();
                return true;
            case R.id.about:
                about();
                return true;
            case R.id.copy:
                copy();
                return true;
            case R.id.paste:
                paste();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void paste() {
        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null)
        { if (clipboard.hasPrimaryClip()
        && clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
            {
                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                String pasteData = item.getText().toString();
                if(isNumeric(pasteData))
                    mDisplay.setText(pasteData);
            }
        }
    }


    private void about() {
        Intent activityIntent = new Intent(getApplicationContext(), about.class);
        startActivity(activityIntent);

        }

        private void startSettings () {
            Intent activity2Intent = new Intent(getApplicationContext(), settings.class);
            startActivity(activity2Intent);
        }

        /**
         * скопировать текст с дисплея в буйфер обмена
         */
        public void copy () {
            ClipboardManager clipboard = (ClipboardManager)
                    getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard != null) {
                ClipData clip = ClipData.newPlainText("", mDisplay.getText());
                clipboard.setPrimaryClip(clip);
            }
        }

        /**
         * Проверка строки на чисто
         *
         * @param text -текст на проврку
         * @return успешность проверки
         */
        public static boolean isNumeric(String text)
        {
            if (text == null)
                return false;
            try {
                Double.parseDouble(text);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }


    }



