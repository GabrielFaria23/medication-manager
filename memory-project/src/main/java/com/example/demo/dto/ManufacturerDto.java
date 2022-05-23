package com.example.demo.dto;

import javax.persistence.*;

public class ManufacturerDto {

    private Integer id;
    private String name;

    public ManufacturerDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
