package com.example.kanbanBord;

import java.util.List;

public interface TaskService {


    List<Tasks> getAllTasks();

    Tasks saveTask(Tasks task);

    Tasks editTask(long id);

    void DeleteTask(long id);
}
