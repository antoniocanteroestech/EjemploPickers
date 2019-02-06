package es.estech.ejemplopickers;

import android.content.Intent;
import android.os.BadParcelableException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MorePickersActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    private static final String BARRA = "/";

    private DatePicker datePicker;
    private TimePicker timePicker;
    Button btShowDateTime, btChangeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_pickers);

        datePicker = findViewById(R.id.event_date_picker);
        timePicker = findViewById(R.id.event_time_picker);

        timePicker.setIs24HourView(true);

        btShowDateTime = findViewById(R.id.showDateTimeBtn);
        btShowDateTime.setOnClickListener(this);
        btChangeActivity = findViewById(R.id.changeActivityBtn);
        btChangeActivity.setOnClickListener(this);
    }


    public void showDateTime(View view) {
        // Se crea una instancia de Calendar y se guardan todos los datos de los formularios
        Calendar date = new GregorianCalendar();
        int day, month, year, hours, minutes;
        day = datePicker.getDayOfMonth();
        month = datePicker.getMonth() + 1;
        year = datePicker.getYear();
        hours = timePicker.getHour();
        minutes = timePicker.getMinute();

        date.set(year, month, day, hours, minutes);
        String dateString = day+BARRA+month+BARRA+year+" "+hours+DOS_PUNTOS+minutes;

        Toast.makeText(this, "La fecha y hora seleccionadas son: "+dateString, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.showDateTimeBtn:
                showDateTime(v);
                break;
            case R.id.changeActivityBtn:
                changeActivity();
                break;
        }
    }

    private void changeActivity() {
        Intent intent = new Intent (MorePickersActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
