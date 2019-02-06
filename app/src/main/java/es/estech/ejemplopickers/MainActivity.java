package es.estech.ejemplopickers;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hour
    public final Calendar c = Calendar.getInstance();
    //Fecha
    final int month = c.get(Calendar.MONTH);
    final int day = c.get(Calendar.DAY_OF_MONTH);
    final int year = c.get(Calendar.YEAR);
    //Hora
    final int hour = c.get(Calendar.HOUR_OF_DAY);
    final int minute = c.get(Calendar.MINUTE);

    EditText etDate, etHour;
    ImageButton ibGetDate, ibGetHour;
    Button btShowDateTime, btChangeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDate = findViewById(R.id.showDateEt);
        etHour = findViewById(R.id.showTimeEt);
        ibGetDate = findViewById(R.id.getDateIBtn);
        ibGetHour = findViewById(R.id.getTimeIBtn);
        ibGetDate.setOnClickListener(this);
        ibGetHour.setOnClickListener(this);
        btShowDateTime = findViewById(R.id.showDateTimeBtn);
        btShowDateTime.setOnClickListener(this);
        btChangeActivity = findViewById(R.id.changeActivityBtn);
        btChangeActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getDateIBtn:
                getDate();
                break;
            case R.id.getTimeIBtn:
                getTime();
                break;
            case R.id.showDateTimeBtn:
                showDateTime(v);
                break;
            case R.id.changeActivityBtn:
                changeActivity();
                break;
        }
    }


    private void getDate(){
        DatePickerDialog date = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                final int currentMonth = month + 1;

                String formatDay = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String formatMonth = (currentMonth < 10)? CERO + String.valueOf(currentMonth):String.valueOf(currentMonth);

                etDate.setText(formatDay + BARRA + formatMonth + BARRA + year);


            }
        }, year, month, day);

        date.show();

    }

    private void getTime(){
        TimePickerDialog time = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String formatHour =  (hourOfDay < 9)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                String formatTime = (minute < 9)? String.valueOf(CERO + minute):String.valueOf(minute);

                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }

                etHour.setText(formatHour + DOS_PUNTOS + formatTime + " " + AM_PM);
            }

        }, hour, minute, false);

        time.show();
    }


    public void showDateTime(View view) {
        String date = etDate.getText().toString();
        String time = etHour.getText().toString();
        if (date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Selecciona fecha y hora", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "La fecha y hora seleccionadas son: "+date + " " + time, Toast.LENGTH_LONG).show();

        }
    }


    private void changeActivity() {
        Intent intent = new Intent (MainActivity.this, MorePickersActivity.class);
        startActivity(intent);
    }
}
