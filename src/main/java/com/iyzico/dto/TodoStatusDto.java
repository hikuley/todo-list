package com.iyzico.dto;

import com.iyzico.entity.enums.TodoStatus;

/**
 * Created by hikuley on 30/08/16.
 */
public class TodoStatusDto {
    private Long id;
    private TodoStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }
}
