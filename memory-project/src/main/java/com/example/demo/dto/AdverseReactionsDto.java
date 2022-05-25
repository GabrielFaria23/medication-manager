package com.example.demo.dto;

import com.example.demo.model.Medication;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"id", "description","activated","deleted","createdAt", "lastUpdate" })
public class AdverseReactionsDto extends BaseModelDto{

    private Integer id;

    private String description;

    private List<Medication> medications;

    public AdverseReactionsDto(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public AdverseReactionsDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
