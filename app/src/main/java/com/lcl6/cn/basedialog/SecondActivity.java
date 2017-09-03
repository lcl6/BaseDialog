package com.lcl6.cn.basedialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.lcl6.cn.component.widget.PickDateTimeDialog;

import butterknife.OnClick;

/**
 * Created by liancl on 2017/6/19.
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public static void start(Context context){
        Intent intent = new Intent();
        intent.setClass(context,SecondActivity.class);
        context.startActivity(intent);
    }

    public void btn(View v){
        switch (v.getId()){
            case R.id.tv_cander:
                PickDateTimeDialog.pickDate(this, selectDateListener);
                break;
        }
    }

    private DatePickerDialog.OnDateSetListener selectDateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            String mounthOfYear = PickDateTimeDialog.getMounthOfYear(monthOfYear);
            String dayOfMonthNew = PickDateTimeDialog.getDayOfMonth(dayOfMonth);
            PickDateTimeDialog.pickTime(SecondActivity.this,selectTimeListener);
        }
    };

    private TimePickerDialog.OnTimeSetListener selectTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String newHour = PickDateTimeDialog.getHourOrMinute(hourOfDay);
            String newMinute = PickDateTimeDialog.getHourOrMinute(minute);
            Log.e("onTimeSet: ",newHour+newMinute );
        }
    };

}
