package com.example.shru.notificationondate;

import android.app.Activity;
import android.os.Bundle;

import java.sql.Time;
import java.util.Calendar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.shru.notificationondate.R;

public class MainActivity extends Activity {

    DatePicker pickerDate;
    TimePicker pickerTime;
    Button buttonSetAlarm;
    TextView info;

    final static int RQS_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = (TextView) findViewById(R.id.info);
        pickerDate = (DatePicker) findViewById(R.id.datePicker);
        pickerTime = (TimePicker) findViewById(R.id.timePicker);

        final Calendar now = Calendar.getInstance();

        pickerDate.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);

        pickerTime.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        pickerTime.setCurrentMinute(now.get(Calendar.MINUTE));


        buttonSetAlarm = (Button) findViewById(R.id.btnSetAlarm);
        buttonSetAlarm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Calendar current = Calendar.getInstance();
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                int hours = new Time(System.currentTimeMillis()).getHours();
                int minutes=new Time(System.currentTimeMillis()).getMinutes();

                Calendar cal = Calendar.getInstance();
                cal.set(pickerDate.getYear(),
                        pickerDate.getMonth(),
                        pickerDate.getDayOfMonth(),
                        pickerTime.getCurrentHour(),
                        pickerTime.getCurrentMinute(),
                        00);

                if (cal.compareTo(current) <= 0)
                {
                    //The set Date/Time already passed
                    Toast.makeText(getApplicationContext(),
                            "Invalid Date/Time",
                            Toast.LENGTH_LONG).show();
                }

                else if( pickerTime.getCurrentHour() >= hours+2 ){

                    Intent i = new Intent("com.example.shru.DisplayNotification");

                    PendingIntent displayIntent = PendingIntent.getActivity(
                            getBaseContext(), 0, i, 0);

                    //---sets the alarm to trigger---
                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            cal.getTimeInMillis() - 7200000, displayIntent);
                    info.setText("Alarm is set@ " + cal.getTime());


                }
                else if(hours+2 != pickerTime.getCurrentHour()){

                   /* Toast.makeText(getApplicationContext(),"time difference is not more than two hours",Toast.LENGTH_SHORT).show();
*/                  Intent i = new Intent("com.example.shru.DisplayNotification");

                    PendingIntent displayIntent = PendingIntent.getActivity(
                            getBaseContext(), 0, i, 0);

                    //---sets the alarm to trigger---
                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            cal.getTimeInMillis(), displayIntent);
                    info.setText("Alarm is set@ " + cal.getTime());
                }
                else {
                    Intent i = new Intent("com.example.shru.DisplayNotification");

                    PendingIntent displayIntent = PendingIntent.getActivity(
                            getBaseContext(), 0, i, 0);

                    //---sets the alarm to trigger---
                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            cal.getTimeInMillis(), displayIntent);
                    info.setText("Alarm is set@ " + cal.getTime());
                }
            }
        });
    }
}