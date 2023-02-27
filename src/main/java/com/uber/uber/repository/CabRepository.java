package com.uber.uber.repository;

import com.uber.uber.models.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabRepository extends JpaRepository<Cab, Integer> {

}
