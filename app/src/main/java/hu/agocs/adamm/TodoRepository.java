package hu.agocs.adamm;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TodoRepository {
    private static TodoRepository mTodoRepository;
    public static TodoRepository getInstance(){
        if(mTodoRepository == null){
            mTodoRepository = new TodoRepository();
        }
        return mTodoRepository;
    }
    TodoAPI mTodoAPI;
    private TodoRepository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mTodoAPI = retrofit.create(TodoAPI.class);
    }

    public MutableLiveData<List<Todo>> getTodosFromRepository(){
        MutableLiveData<List<Todo>> todoRepository = new MutableLiveData<>();
        mTodoAPI.listAllTodos().enqueue(new Callback<List<Todo>>(){
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.isSuccessful()){
                    todoRepository.setValue( response.body() );
                }
            }
            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                todoRepository.setValue( null );
            }
        });
        return todoRepository;
    }
}
