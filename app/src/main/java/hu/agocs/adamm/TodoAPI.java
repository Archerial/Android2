package hu.agocs.adamm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoAPI {
    @GET("todos")
    Call<List<Todo>> listAllTodos();
}
