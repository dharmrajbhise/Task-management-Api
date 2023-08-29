package com.example.kanbanBord;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/KanbanBoard/api")
public class Homecontroller {

    @Autowired
    private TaskService Ts;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/")
    public List<Tasks> home(Model model){

        return Ts.getAllTasks();
    }

    @PostMapping
    public Tasks saveTask(@RequestBody Tasks task){

        return Ts.saveTask(task);
    }

    @PutMapping("/edit/{id}")
    public Tasks updateTask(@PathVariable("id") long id,@RequestBody Tasks task){
        Tasks existingTask = Ts.editTask(id);

        if(existingTask != null){
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.getStatus());
            return Ts.saveTask(task);
        } else{

            try{
                throw new NullPointerException("Task not Found");
            }catch (NullPointerException e){
                System.out.println(e.getMessage());
            }
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
   public void deletetask(@PathVariable("id") long id){
        Ts.DeleteTask(id);
   }
}
