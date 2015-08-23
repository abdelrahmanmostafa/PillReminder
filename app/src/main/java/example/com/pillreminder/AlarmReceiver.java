package example.com.pillreminder;

/**
 * Created by abdelrahman on 8/23/15.
 */

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {



        // For our recurring task, we'll just display a message
        sendNotification(context, intent);
         //Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
    }

    public void sendNotification(Context context, Intent intent) {
        //Build the Notification
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notification;
        final int ID=1250;
        notification = new NotificationCompat.Builder(context);
        notification.setAutoCancel(true);
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("Pillify");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Pillify");
        //String msg = myintent.getStringExtra("msg");
        notification.setContentText("Take the medication");
        notification.setSound(soundUri);

        NotificationManager nm=(NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        nm.notify(ID,notification.build());

    }
}