package com.example.vibhor.datepickerdemoapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private Button changeDateButton;
    private DatePicker datePicker;
    private TextView textView;

    private int day;
    private int month;
    private int year;

    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setCurrentDateOnView();
        addListenerOnButton();
    }

    /**
     * this will set current date on view as the function
     *  says .........
     */
    public void setCurrentDateOnView()
    {
        textView= (TextView) findViewById(R.id.tvDate);
        datePicker= (DatePicker) findViewById(R.id.dpResult);


        final Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);



        textView.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // set current date into datepicker
        datePicker.init(year, month, day, null);


    }


    public void addListenerOnButton(){
        changeDateButton = (Button) findViewById(R.id.btnChangeDate);

        changeDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener,
                        year, month,day);
        }

        return  null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into text view
            textView.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

            // set selected date into date picker also
            datePicker.init(year, month, day, null);

        }
    };
}
