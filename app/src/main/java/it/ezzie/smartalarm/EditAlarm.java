package it.ezzie.smartalarm;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import it.ezzie.smartalarm.Data_Access_Object.AlarmDAO;
import it.ezzie.smartalarm.Database.AppDatabase;
import it.ezzie.smartalarm.Entity.AlarmEntity;
import it.ezzie.smartalarm.databinding.ActivityEditAlarmBinding;

public class EditAlarm extends AppCompatActivity {
    private ActivityEditAlarmBinding binding;
    private AlarmDAO alarmDAO;
    private Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityEditAlarmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initDatabase();
        initData();
        initListener();
    }

    private void initListener() {
    }

    private void initDatabase() {
        var databaseBuilder = AppDatabase.getInstance(this);
        alarmDAO = databaseBuilder.alarmDAO();
        alarmDAO.getAllAlarms();
    }
    private void initData(){
          binding.timePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
          calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
          calendar.set(Calendar.MINUTE,minute);

          int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
          int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
          int totalCurrentMinute = currentHour * 60 + currentMinute;
          int totalSelectedMinute = hourOfDay * 60 + minute;
          int totalResultMinute = totalSelectedMinute - totalCurrentMinute;
          int resultHour = totalResultMinute / 60;
          int resultMinute = totalResultMinute % 60;
          if(totalResultMinute < 0){
              resultHour = -resultHour;
              resultMinute = -resultMinute;
          }
          binding.hour.setText(String.valueOf(resultHour));
          binding.minute.setText(String.format("%02d",resultMinute));

          //Init AlarmUnit
            var formattedUnit = new SimpleDateFormat("a").format(calendar.getTime());

          //Cancel Button
          binding.btnCancel.setOnClickListener(v -> {
              finish();
          });

          //OK Button
          binding.btnOK.setOnClickListener(v -> {
              AlarmEntity alarm;
              //Getting Alarm Label
              String label = binding.alarmEditTxt.getText().toString().trim();
              if(label.isEmpty()){
                  alarm = new AlarmEntity(String.valueOf(hourOfDay),String.format("%02d",minute),formattedUnit);
              }
              else {
                   alarm = new AlarmEntity(String.valueOf(hourOfDay), String.format("%02d", minute),formattedUnit, label);
              }
              Intent intent = new Intent(this, MainActivity.class);
              intent.putExtra("alarm",alarm);
              setResult(RESULT_OK,intent);
              finish();
          });
        });
    }

}