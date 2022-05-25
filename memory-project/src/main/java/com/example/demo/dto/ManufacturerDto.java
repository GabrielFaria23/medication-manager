package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name","activated","deleted","createdAt", "lastUpdate" })
public class ManufacturerDto extends BaseModelDto{

    private Integer id;
    private String name;

    public ManufacturerDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ManufacturerDto() {
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
