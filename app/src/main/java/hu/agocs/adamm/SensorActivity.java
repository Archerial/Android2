package hu.agocs.adamm;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener{


    ListView listSens;
    private SensorManager sensorManager;
    Sensor accelerometer;

    TextView xValue, yValue, zValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        listSens = findViewById(R.id.listSensor);
        SensorManager sensorManager = (SensorManager) this.getSystemService(this.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        List<String> sensorNames = new ArrayList<>();
        for (int i=0; i < sensorList.size(); i++){
            sensorNames.add(((Sensor) sensorList.get(i)).getName() + sensorList.get(i).getVersion() + sensorList.get(i).getVendor() + sensorList.get(i).getType());
        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,sensorNames);

        listSens.setAdapter(itemsAdapter);

        xValue = findViewById(R.id.textViewXvalue);
        yValue = findViewById(R.id.textViewYvalue);
        zValue = findViewById(R.id.textViewZvalue);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener((SensorEventListener) SensorActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        xValue.setText("xValue: " + sensorEvent.values[0]);
        yValue.setText("yValue: " + sensorEvent.values[1]);
        zValue.setText("zValue: " + sensorEvent.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
