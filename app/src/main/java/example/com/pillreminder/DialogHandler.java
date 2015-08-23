package example.com.pillreminder;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Created by abdelrahman on 8/23/15.
 */

public class DialogHandler extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog;
        TimeSettings timeSettings = new TimeSettings(getActivity());
        dialog= new TimePickerDialog(getActivity(), timeSettings,hour,minute, DateFormat.is24HourFormat(getActivity()));
        return dialog;

        //return super.onCreateDialog(savedInstanceState);

    }
}
