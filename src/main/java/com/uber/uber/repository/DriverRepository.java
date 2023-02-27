package com.uber.uber.repository;

import com.uber.uber.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer>{

}
