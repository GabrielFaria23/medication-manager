package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "medication")
public class Medication extends BaseModel{

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

    private Manufacturer manufacturer;

    private List<AdverseReactions>  adverseReactions;

    public Medication(Integer id, String anvisaRegistrationNumber, String name, LocalDateTime expirationDate,
                      String telephoneSac, Double price, Integer quantityPills, Manufacturer manufacturer,
                      List<AdverseReactions> adverseReactions) {
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

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<AdverseReactions> getAdverseReactions() {
        return adverseReactions;
    }

    public void setAdverseReactions(List<AdverseReactions> adverseReactions) {
        this.adverseReactions = adverseReactions;
    }
}
