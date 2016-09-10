package com.iyzico.dto;

import com.iyzico.entity.enums.TodoStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;

/**
 * Created by hikuley on 28/08/16.
 */

public class TodoDto extends BaseDto {


    public static final int NAME_MIN_LENGTH = 4;
    public static final int NAME_MAX_LENGTH = 50;

    public static final int CONTENT_MIN_LENGTH = 4;
    public static final int CONTENT_MAX_LENGTH = 50;


    @NotNull
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
    private String name;
    private String description;

    @NotNull
    @Size(min = CONTENT_MIN_LENGTH, max = CONTENT_MAX_LENGTH)
    private String content;

    private UserDto user;
    private TodoStatus status;
    private Calendar deadline;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }
}
