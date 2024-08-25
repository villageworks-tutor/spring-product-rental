package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

/**
 * 製品情報関連サービス
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * すべての製品の情報を取得する
     * @return 製品リスト：製品クラスのインスタンスを要素とするリスト
     *         　　　　　　登録されている製品がない場合は空リスト
     */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * すべての製品の情報を製品通番順に並べ替えて取得する
     * @return 製品リスト：製品クラスのインスタンスを要素とするリスト
     *         　　　　　　登録されている製品がない場合は空リスト
     */
	public List<Product> findAllByOrderIdAsc() {
		return productRepository.findAllByOrderByIdAsc();
	}

}