package hu.agocs.adamm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class TodoViewModel extends ViewModel {
    private MutableLiveData<List<Todo>> todoLiveData;
    public MutableLiveData<List<Todo>> getTodoLiveData(){
        if (todoLiveData == null){
            todoLiveData = new MutableLiveData<List<Todo>>();
        }
        todoLiveData = TodoRepository.getInstance().getTodosFromRepository();
        return todoLiveData;
    }
}
