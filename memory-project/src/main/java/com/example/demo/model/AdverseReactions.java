package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "adverse_reactions")
@SequenceGenerator(name = "sq_adverse_reactions", sequenceName = "sq_adverse_reactions", allocationSize = 1)
public class AdverseReactions extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_adverse_reactions")
    private Integer id;

    private String description;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "adverseReactions")
    private List<Medication> medications ;

    public AdverseReactions(Integer id, String description) {
        this.id = id;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdverseReactions that = (AdverseReactions) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
