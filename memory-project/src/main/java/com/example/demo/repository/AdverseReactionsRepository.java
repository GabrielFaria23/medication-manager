package com.example.demo.repository;

import com.example.demo.model.AdverseReactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdverseReactionsRepository extends JpaRepository<AdverseReactions, Integer> {

    @Query(nativeQuery = true, value ="" +
            "SELECT * FROM adverse_reactions ar " +
            "WHERE ar.id = :id " +
            "AND ar.deleted IS NOT true ")
    Optional<AdverseReactions> findByIdAndDeletedFalse(Integer id);

    @Query(nativeQuery = true, value ="" +
            "SELECT * FROM adverse_reactions ar " +
            "WHERE ar.deleted IS NOT true " +
            "order by ar.id ")
    List<AdverseReactions> findAllWhereDeletedIsFalse();

    @Query(nativeQuery = true, value = "" +
            "select count(1) from adverse_reactions ar " +
            "inner join medication_adverse_reactions mar on mar.id_adverse_reactions = ar.id " +
            "inner join medication m on mar.id_medication = m.id  " +
            "where ar.id = :id")
    Integer checkIfAdverseReactionIsNotViculedToAnyMedication(Integer id);
}
