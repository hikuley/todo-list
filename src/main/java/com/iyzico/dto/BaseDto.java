package com.iyzico.dto;

import java.util.Calendar;

/**
 * Created by hikuley on 28/08/16.
 */
public class BaseDto {

    private Long id;
    private Calendar createTime;
    private Calendar updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }
}


