package com.iyzico.entity;

import com.iyzico.entity.enums.TodoStatus;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by hikuley on 27/08/16.
 */


@Entity
public class Todo extends BaseEntity {

    private String name;
    private String description;
    private String content;

    @ManyToOne
    private User user;

    @Enumerated(value = EnumType.STRING)
    private TodoStatus status;

    @Temporal(TemporalType.TIMESTAMP)
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
