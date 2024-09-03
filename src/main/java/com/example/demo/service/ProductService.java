package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	private static final Integer IMEI_LENGTH = 15;
	
	@Autowired
	ProductRepository productRepository;

	/**
	 * 製品マスタ入力値の妥当性検査
	 * @param product 入力値が設定された製品インスタンス
	 * @return エラーリスト
	 */
	public List<String> validate(Product product) {
		// エラーリストの初期化
		List<String> errorList = new ArrayList<String>();
		
		// 管理番号の妥当性検査
		if (!Validator.isRequired(product.getSerialNo())) {
			Validator.createErrorMessage(errorList, "管理番号", Validator.ERR_TYPE_REQUIRED);
		} else if (!Validator.isPositiveNumber(product.getSerialNo())) {
			Validator.createErrorMessage(errorList, "", Validator.ERR_TYPE_POSITIVE_NUMBER);
		} else {
			Optional<Product> optionalProduct = productRepository.findBySerialNo(product.getSerialNo());
			if (optionalProduct.isPresent()) {
				Validator.createErrorMessage(errorList, "管理番号", Validator.ERR_TYPE_ALREADY_REGISTED);
			}
		}
		// IMEIコードの妥当性検査
		if (!Validator.isRequired(product.getImei())) {
			Validator.createErrorMessage(errorList, "IMEI", Validator.ERR_TYPE_REQUIRED);
		} else if (!Validator.hasLength(product.getImei(), IMEI_LENGTH)) {
			Validator.createErrorMessage(errorList, "IMEI", IMEI_LENGTH, Validator.ERR_TYPE_LENGTH);
		} else {
			Optional<Product> optionalProduct = productRepository.findByImei(product.getImei());
			// すでにIMEIコードの製品が登録されている場合
			if (optionalProduct.isPresent()) {
				Validator.createErrorMessage(errorList, "IMEIコードの製品", Validator.ERR_TYPE_ALREADY_REGISTED);
			}
		}
		
		// エラーリストを返却
		return errorList;
	}

	/**
	 * 製品マスタ登錄処理
	 * @param product 処理対象製品インスタンス
	 */
	public void store(Product product) {
		productRepository.save(product);
	}
	
	/**
	 * すべての製品を製品IDの昇順に並べ替えて取得する
	 * @return 製品IDの昇順に並べ替えられた製品リスト
	 */
	public List<Product> findAll() {
		List<Product> list = productRepository.findAllByOrderByIdAsc();
		return list;
	}

	/**
	 * 指定された製品管理番号の製品を取得する
	 * @param serialNo 製品管理番号
	 * @return 指定された製品管理番号に合致した製品インスタンス、合致したものがない場合はnull
	 */
	public Product findBySerialNo(Integer serialNo) {
		Product product = productRepository.findBySerialNo(serialNo).get();
		return product;
	}

}
