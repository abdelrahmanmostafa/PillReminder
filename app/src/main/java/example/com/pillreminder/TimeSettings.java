package example.com.pillreminder;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimeSettings implements TimePickerDialog.OnTimeSetListener{



    Context context;
    int  selectedHour;
    int selectedMinute;

    public TimeSettings( Context context){
        this.context=context;
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(context, "Selected time is hour:" + hourOfDay + "minute:" + minute, Toast.LENGTH_LONG).show();
        selectedHour=hourOfDay;
        selectedMinute=minute;
    }

}
