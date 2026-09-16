package com.spring.todolist.controller;

import com.spring.todolist.dto.TaskRequestDTO;
import com.spring.todolist.dto.TaskResponseDTO;
import com.spring.todolist.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<TaskResponseDTO> findAll(){
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO create(@Valid @RequestBody TaskRequestDTO dto){
        return service.create(dto);
    }

    @PatchMapping("/{id}/toggle")
    public TaskResponseDTO toggle(@PathVariable Long id){
        return service.toggleComp(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
