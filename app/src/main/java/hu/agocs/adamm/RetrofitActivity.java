package hu.agocs.adamm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RetrofitActivity extends AppCompatActivity {

    ListView mListView;
    ArrayAdapter<String> mAdapter;
    ArrayList<String> todoArray;
    TodoViewModel mTodoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        todoArray = new ArrayList<String>();
        mListView = findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,todoArray);
        mListView.setAdapter(mAdapter);

        mTodoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        final Observer<List<Todo>> todoObserver = new Observer<List<Todo>>(){
            @Override
            public void onChanged(List<Todo> todos){
                if (todos != null){
                    for (Todo t: todos){
                        todoArray.add(t.toString());
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        };
        mTodoViewModel.getTodoLiveData().observe(this,todoObserver);

    }
}
