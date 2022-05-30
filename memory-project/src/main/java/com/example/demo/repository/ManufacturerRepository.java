package com.example.demo.repository;

import com.example.demo.dto.ManufacturerDto;
import com.example.demo.model.AdverseReactions;
import com.example.demo.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

    @Query(nativeQuery = true, value ="" +
            "SELECT * FROM manufacturer m " +
            "WHERE m.id = :id " +
            "AND m.deleted IS NOT true ")
    Optional<Manufacturer> findByIdAndDeletedFalse(Integer id);

    @Query(nativeQuery = true, value ="" +
            "SELECT * FROM manufacturer m " +
            "WHERE m.deleted IS NOT true " +
            "order by m.id ")
    List<Manufacturer> findAllWhereDeletedIsFalse();

    @Query(nativeQuery = true, value ="" +
            "SELECT * FROM manufacturer m " +
            "WHERE m.deleted IS NOT true " +
            "AND m.name ILIKE concat('%',:filter,'%') " +
            "order by m.id; ")
    List<Manufacturer> findByName(String filter);
}
