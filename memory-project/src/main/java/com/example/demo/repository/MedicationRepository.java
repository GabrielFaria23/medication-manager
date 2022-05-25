package com.example.demo.repository;

import com.example.demo.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Integer> {

    @Query(nativeQuery = true, value ="" +
            "SELECT * FROM medication m " +
            "WHERE m.id = :id " +
            "AND m.deleted IS NOT true ")
    Optional<Medication> findByIdAndDeletedFalse(Integer id);

    @Query(nativeQuery = true, value ="" +
            "SELECT * FROM medication m " +
            "WHERE m.deleted IS NOT true ")
    List<Medication> findAllWhereDeletedIsFalse();
}
