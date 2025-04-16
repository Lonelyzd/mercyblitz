package com.acme.biz.api.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author : IceBlue
 * @date : 2025/4/3 22:29
 **/
@Data
public class User {

    @NotNull
    private Long id;

    @NotNull
    @Email
    @Size(min = 10)
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
