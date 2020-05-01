package hu.agocs.adamm;

public class Todo {
    public Integer userId;
    public Integer Id;
    public String title;
    public Boolean completed;

    public Todo(Integer userId, Integer id, String title, Boolean completed) {
        this.userId = userId;
        Id = id;
        this.title = title;
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "userId=" + userId +
                ", Id=" + Id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
