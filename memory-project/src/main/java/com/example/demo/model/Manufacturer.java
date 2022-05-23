package com.example.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "manufacturer")
@SequenceGenerator(name = "sq_manufacturer", sequenceName = "sq_manufacturer", allocationSize = 1)
public class Manufacturer extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_manufacturer")
    private Integer id;
    private String name;

    public Manufacturer(Integer id, String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
