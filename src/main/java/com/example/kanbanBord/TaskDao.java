package com.example.kanbanBord;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskDao implements TaskService {

    @Autowired
    private TaskRepo repo;

    @Override
    public List<Tasks> getAllTasks() {

        if(repo.findAll() == null){
            try {
                throw new NullPointerException("List is empty");
            } catch (NullPointerException e) {
                System.out.println("Custom Exception: " + e.getMessage());
                // You can also handle the exception in other ways, such as logging or propagating it
            }
        }

        return repo.findAll();
    }

    @Override
    public Tasks saveTask(Tasks task) {
        return repo.save(task);
    }

    @Override
    public Tasks editTask(long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void DeleteTask(long id) {
        repo.deleteById(id);
    }
}
