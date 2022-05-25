package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@JsonPropertyOrder({"id", "anvisaRegistrationNumber","name","expirationDate","telephoneSac","price","quantityPills",
        "manufacturer","adverseReactions","activated","deleted","createdAt", "lastUpdate" })
public class MedicationDto extends BaseModelDto{

    private Integer id;

    private String anvisaRegistrationNumber;

    private String name;

    private Date expirationDate;

    private String telephoneSac;

    private Double price;

    private Integer quantityPills;

    private ManufacturerDto manufacturer;

    private List<AdverseReactionsDto>  adverseReactions;

    public MedicationDto(Integer id, String anvisaRegistrationNumber, String name, Date expirationDate,
                         String telephoneSac, Double price, Integer quantityPills) {
        this.id = id;
        this.anvisaRegistrationNumber = anvisaRegistrationNumber;
        this.name = name;
        this.expirationDate = expirationDate;
        this.telephoneSac = telephoneSac;
        this.price = price;
        this.quantityPills = quantityPills;
    }

    public MedicationDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnvisaRegistrationNumber() {
        return anvisaRegistrationNumber;
    }

    public void setAnvisaRegistrationNumber(String anvisaRegistrationNumber) {
        this.anvisaRegistrationNumber = anvisaRegistrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getTelephoneSac() {
        return telephoneSac;
    }

    public void setTelephoneSac(String telephoneSac) {
        this.telephoneSac = telephoneSac;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantityPills() {
        return quantityPills;
    }

    public void setQuantityPills(Integer quantityPills) {
        this.quantityPills = quantityPills;
    }

    public ManufacturerDto getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDto manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<AdverseReactionsDto> getAdverseReactions() {
        return adverseReactions;
    }

    public void setAdverseReactions(List<AdverseReactionsDto> adverseReactions) {
        this.adverseReactions = adverseReactions;
    }
}
