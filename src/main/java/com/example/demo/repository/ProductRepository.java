package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	/**
	 * すべての製品の情報を製品管理番号の照準に取得する
	 * SELECT * FROM products ORDER BY id ASC
	 * @return 製品リスト
	 */
	List<Product> findAllByOrderByIdAsc();

	/**
	 * 指定された管理番号の製品を取得する
	 * SELECT * FROM produccts WHERE serial_no = :serialNo
	 * @param serialNo 管理番号
	 * @return 製品インスタンス
	 */
	Optional<Product> findBySerialNo(int serialNo);

	/**
	 * 指定されたIMEIコードの製品を取得する
	 * SELECT * FROM produccts WHERE serial_no = :serialNo
	 * @param imei
	 * @return 製品インスタンス
	 */
	Optional<Product> findByImei(String imei);
	
}
