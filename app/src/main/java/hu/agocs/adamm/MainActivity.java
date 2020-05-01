package hu.agocs.adamm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;


public class MainActivity extends AppCompatActivity {


    private Button button;
    private Button buttonNoti;
    private Button buttonFragment;
    private Button buttonToNavFrag;
    private Button buttonVoll;
    private Button buttonRetro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        button = findViewById(R.id.buttonToSensors);
        buttonNoti = findViewById(R.id.buttonNoti);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSensorActivity();
            }
        });
        buttonFragment = findViewById(R.id.buttonToFragment);
        buttonFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragmentActivity();
            }
        });
        buttonToNavFrag = findViewById(R.id.buttonToFragNav);
        buttonToNavFrag.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openFragmentNavActivity();
            }
        });
        buttonVoll = findViewById(R.id.buttonVolley);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"MyNotification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("My notification")
                .setContentText("This is my notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

         final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);



        buttonNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.notify(100, builder.build());
            }
        });

        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class).setConstraints(constraints).build();
        WorkManager.getInstance().enqueue(oneTimeWorkRequest);


        buttonVoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVolleyActivity();
            }
        });
        buttonRetro = findViewById(R.id.buttonIntent);
        buttonRetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntentAct();
            }
        });

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel";
            String description = "descriptionxd";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("MyNotification", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void openSensorActivity(){
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }

    public void openFragmentActivity(){
        Intent intent = new Intent(this, FragmentActivity.class);
        startActivity(intent);
    }

    public void openFragmentNavActivity(){
        Intent intent = new Intent(this, NavigationBetweenFragments.class);
        startActivity(intent);
    }

    public void openVolleyActivity(){
        Intent intent = new Intent(this, ActivityVolley.class);
        startActivity(intent);
    }

    public void openIntentAct(){
        Intent intent = new Intent(this, RetrofitActivity.class);
        startActivity(intent);
    }

}
