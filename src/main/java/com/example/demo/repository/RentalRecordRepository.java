package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.RentalRecord;

@Repository
public interface RentalRecordRepository extends JpaRepository<RentalRecord, Long> {

	RentalRecord findByProductAndReturnDateIsNull(Product p);

	RentalRecord findByProductIdAndReturnDateIsNull(Long productId);

}
