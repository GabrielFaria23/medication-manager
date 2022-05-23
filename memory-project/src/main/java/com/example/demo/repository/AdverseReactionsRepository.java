package com.example.demo.repository;

import com.example.demo.model.AdverseReactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdverseReactionsRepository extends JpaRepository<AdverseReactions, Integer> {
}
