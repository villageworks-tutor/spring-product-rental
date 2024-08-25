package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.RentalRecord;

@Repository
public interface RentalRecordRepository extends JpaRepository<RentalRecord, Long> {

	/***
	 * 指定した製品のうち返却日がnullである貸出情報を取得する：製品情報から貸出日がnullである貸出情報を取得する
	 * SELECT * FROM rental_records WHERE product_id = :product.id AND return_date IS NULL;
	 * @param product 対象となる製品
	 * @return 貸出情報クラスのインスタンス
	 */
	RentalRecord findByProductAndReturnDateIsNull(Product product);

	/**
	 * 指定した製品通番に合致した製品でかつ返却日がnullである貸出情報を取得する：製品通番から貸出日がnullである貸出情報を取得する
	 * SELECT * FROM rental_records WHERE product_id = :productId AND return_date IS NULL;
	 * @param productId
	 * @return 貸出情報クラスのインスタンス
	 */
	RentalRecord findByProductIdAndReturnDateIsNull(Long productId);

}
