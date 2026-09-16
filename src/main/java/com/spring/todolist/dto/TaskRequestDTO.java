package com.spring.todolist.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskRequestDTO(
        @NotBlank(message = "O título é obrigatório")
        String title,
        String description
) {}