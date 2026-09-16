package com.spring.todolist.service;

import com.spring.todolist.dto.TaskRequestDTO;
import com.spring.todolist.dto.TaskResponseDTO;
import com.spring.todolist.exception.TaskNotFoundException;
import com.spring.todolist.model.Task;
import com.spring.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository rep;

    public TaskService(TaskRepository rep){
        this.rep = rep;
    }

    public List<TaskResponseDTO> findAll(){
        return rep.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public TaskResponseDTO create(TaskRequestDTO dto){
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        return toResponseDTO(rep.save(task));
    }

    public TaskResponseDTO toggleComp(Long id){
        Task task = rep.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
        task.setCompleted(!task.getCompleted());
        return toResponseDTO(rep.save(task));
    }

    public void delete(Long id){
        Task task = rep.findById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
        rep.delete(task);
    }

    private TaskResponseDTO toResponseDTO(Task task){
        return new TaskResponseDTO(task.getId(), task.getTitle(), task.getDescription(),
                task.getCompleted(), task.getCreatedAt());
    }
}
