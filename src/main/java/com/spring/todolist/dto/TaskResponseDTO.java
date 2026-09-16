package com.spring.todolist.dto;

import java.time.LocalDateTime;

// O que a API DEVOLVE pro cliente
public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        boolean completed,
        LocalDateTime createdAt
) {}
