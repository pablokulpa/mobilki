package pl.bien.sql_lite.database;

public interface QueryResponse<T> {
    void onSuccess(T data);
    void onFailure(String message);
}