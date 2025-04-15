package com.acme.biz.api.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author : IceBlue
 * @date : 2025/4/3 22:29
 **/
@Data
public class User {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
