package hu.agocs.adama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_a, new FragmentA())
                .add(R.id.container_b, new FragmentB()).commit();

    }
}
