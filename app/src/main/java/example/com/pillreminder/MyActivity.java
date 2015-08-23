    package example.com.alarmmanager;

    import android.app.Activity;
    import android.app.AlarmManager;
    import android.app.PendingIntent;
    import android.content.Context;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Toast;
    import java.util.Calendar;

    public class MyActivity extends Activity {

        public PendingIntent pendingIntent;
        public PendingIntent pendingIntent2;
        public int  id =0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_my);

        /* Retrieve a PendingIntent that will perform a broadcast */
            Intent alarmIntent = new Intent(MyActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MyActivity.this,changeID(), alarmIntent, 0);
            pendingIntent2 = PendingIntent.getBroadcast(MyActivity.this,changeID(), alarmIntent, 0);

            findViewById(R.id.startRepeatingAlarm2Min).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startRepeatingAlarm1();
                }
            });

            findViewById(R.id.startRepeatingAlarm3Min).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startRepeatingAlarm2();
                }
            });
            findViewById(R.id.stopAlarm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel();
                }
            });



        }

        public int changeID()
        {

            return(id++);
        }
        public void startRepeatingAlarm1() {
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            // 2 seconds
            int interval = 1000 * 60 *2;


            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());

            calendar.set(Calendar.YEAR, 2015);
            calendar.set(Calendar.MONTH,7);

            calendar.set(Calendar.HOUR_OF_DAY,17);
            calendar.set(Calendar.MINUTE,37);
            calendar.set(Calendar.SECOND, 0);

        /* Repeating on  each  interval */
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    interval, pendingIntent);
        }

        public void startRepeatingAlarm2() {
            AlarmManager manager1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            // 3 seconds
            int interval = 1000 * 60 *2;


            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());

            calendar.set(Calendar.YEAR, 2015);
            // 7 means August because months' index starts at 0
            calendar.set(Calendar.MONTH,7);

            calendar.set(Calendar.HOUR_OF_DAY,17);
            calendar.set(Calendar.MINUTE,38);
            calendar.set(Calendar.SECOND, 0);

        /* Repeating on  each  interval */
            manager1.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    interval, pendingIntent2);
        }

        public void cancel() {
               AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);
            manager.cancel(pendingIntent2);
            Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
        }


    }