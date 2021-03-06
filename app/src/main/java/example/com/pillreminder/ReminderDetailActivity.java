package example.com.pillreminder;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ReminderDetailActivity extends AppCompatActivity implements OnClickListener {

    public PendingIntent pendingIntent;
    private Button settingDate;
    public Button settingTime;
    public EditText medicineName;
    public TextView test;

    private DatePickerDialog datePickerDialog;

    private SimpleDateFormat dateFormatter;
    private int hour;
    private int minute;
    private int year;
    private int month;
    private int day;

    static final int TIME_DIALOG_ID = 0;
    int item_selection=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Create New Reminder");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();

        setDateTimeField();

        setCurrentTimeOnView();
        addButtonListener();
    }

// for frequency popup menu

public void frequencyButtonClicked(View view){
    registerForContextMenu(view);
    openContextMenu(view);

}

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.freq_popup_menu,menu);
        MenuItem item_once= menu.findItem(R.id.once);
        MenuItem item_twoTimes= menu.findItem(R.id.twoTimes);
        MenuItem item_threeTimes= menu.findItem(R.id.threeTimes);
        MenuItem item_fourTimes= menu.findItem(R.id.threeTimes);

        if (item_selection==1){
            item_once.setChecked(true);
        }
        if (item_selection==2){
            item_twoTimes.setChecked(true);
        }
        if (item_selection==3){
            item_threeTimes.setChecked(true);
        }
        if (item_selection==4){
            item_fourTimes.setChecked(true);
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.once:
                //Toast.makeText(getApplicationContext(), "once is selected", Toast.LENGTH_SHORT).show();
                item.setChecked(true);
                item_selection=1;
                return true;
            case R.id.twoTimes:
                //Toast.makeText(getApplicationContext(),"two times is selected",Toast.LENGTH_SHORT).show();
                item.setChecked(true);
                item_selection=2;
                return true;
            case R.id.threeTimes:
                //Toast.makeText(getApplicationContext(),"three times is selected",Toast.LENGTH_SHORT).show();
                item.setChecked(true);
                item_selection=3;
                return true;
            case R.id.fourTimes:
                //Toast.makeText(getApplicationContext(),"three times is selected",Toast.LENGTH_SHORT).show();
                item.setChecked(true);
                item_selection=4;
                return true;

        }
        return super.onContextItemSelected(item);
    }

    private void findViewsById() {
        settingDate = (Button) findViewById(R.id.startingDate);
        settingDate.setInputType(InputType.TYPE_NULL);
        //settingDate.requestFocus();
    }

    public void setDateTimeField() {
        settingDate.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int selectedYear, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(selectedYear, monthOfYear, dayOfMonth);
                year=selectedYear;
                month=monthOfYear;
                day=dayOfMonth;

                settingDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }
    public void setCurrentTimeOnView() {

        settingTime= (Button) findViewById(R.id.startingTime);

        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        // set current time into textview
        settingTime.setText(new StringBuilder().append(padding_str(hour)).append(":").append(padding_str(minute)));

        // set current time into timepicker
    }



    public void addButtonListener() {

        settingTime = (Button) findViewById(R.id.startingTime);

        settingTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOG_ID);

            }

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this, timePickerListener, hour, minute,false);

        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener =  new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;

            // set current time into textview
            settingTime.setText(new StringBuilder().append(padding_str(hour)).append(":").append(padding_str(minute)));

            // set current time into timepicker

        }
    };

    private static String padding_str(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    @Override
    public void onClick(View view) {
        if(view == settingDate) {
            datePickerDialog.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_alarm_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                finish();
                break;
            }
            case R.id.action_save_reminder:
                updateReminders();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void updateReminders()
    {
        ReminderModel model= new ReminderModel();
        medicineName = (EditText)findViewById(R.id.reminder_name);
        model.drugName=medicineName.getText().toString();
        model.year= year;
        model.month=month;
        model.day=day;
        model.hour=hour;
        model.minute=minute;
        model.interval_id=item_selection;
        createReminder(model);

    }

    public void createReminder(ReminderModel myModel)

    {

        test = (TextView)findViewById(R.id.testButton);

         int  id =0;

        /* Retrieve a PendingIntent that will perform a broadcast */

        Intent alarmIntent = new Intent(ReminderDetailActivity.this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(ReminderDetailActivity.this,++id, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        switch (myModel.interval_id)
        {
            case 1:
                // update interval
            case 2:
                // update interval
            case 3:
                //update interval
            case 4:
                //update interval

        }


        int interval = 1000 * 60 *2;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.YEAR, myModel.year);
        calendar.set(Calendar.MONTH,myModel.month);
        calendar.set(Calendar.DAY_OF_MONTH,myModel.day);
        calendar.set(Calendar.HOUR_OF_DAY,myModel.hour);
        calendar.set(Calendar.MINUTE,myModel.minute);
        calendar.set(Calendar.SECOND, 0);

        /* Repeating on  each  interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                interval, pendingIntent);

        test.setText(myModel.drugName + " " + Integer.toString(myModel.year) + " " + Integer.toString(myModel.month) + " "
                + Integer.toString(myModel.day) + " " + Integer.toString(myModel.hour) + " " + Integer.toString(myModel.minute));

    }

}
