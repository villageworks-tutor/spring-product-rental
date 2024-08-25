package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * 指定された製品通番に合致した製品を取得する
	 * @param productId 取得対象となる製品の製品通番
	 * @return 指定された製品通番に合致した製品のOptionalクラス
	 */
	Optional<Product> findById(Long productId);

	
	/**
	 *  すべての製品を製品通番の降順に並べ替える
	 * @return 製品通番の降順に並べ替られた製品リスト
	 */
	List<Product> findAllByOrderByIdAsc();


}
