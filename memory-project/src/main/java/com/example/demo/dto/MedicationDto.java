package com.example.demo.dto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "medication")
public class MedicationDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "anvisa_registration_number")
    private String anvisaRegistrationNumber;

    private String name;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "telephone_sac")
    private String telephoneSac;

    private Double price;

    @Column(name = "quantity_pills")
    private Integer quantityPills;

    private ManufacturerDto manufacturer;

    private List<AdverseReactionsDto>  adverseReactions;

    public MedicationDto(Integer id, String anvisaRegistrationNumber, String name, LocalDateTime expirationDate,
                         String telephoneSac, Double price, Integer quantityPills, ManufacturerDto manufacturer,
                         List<AdverseReactionsDto> adverseReactions) {
        this.id = id;
        this.anvisaRegistrationNumber = anvisaRegistrationNumber;
        this.name = name;
        this.expirationDate = expirationDate;
        this.telephoneSac = telephoneSac;
        this.price = price;
        this.quantityPills = quantityPills;
        this.manufacturer = manufacturer;
        this.adverseReactions = adverseReactions;
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

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
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
