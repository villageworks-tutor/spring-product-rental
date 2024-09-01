package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProducctRepository;

@Service
public class ProductService {
	
	@Autowired
	ProducctRepository productRepository;

	/**
	 * 製品マスタ入力値の妥当性検査
	 * @param product 入力値が設定された製品インスタンス
	 * @return エラーリスト
	 */
	public List<String> validate(Product product) {
		// エラーリストの初期化
		List<String> errorList = new ArrayList<String>();
		
		// 管理番号の妥当性検査
		if (product.getSerialNo() == null) {
			errorList.add("管理番号は必須です。");
		} else if (product.getSerialNo() < 0) {
			errorList.add("管理番号は正の整数で入力してください。");
		} else {
			Product existedProduct = productRepository.findBySerialNo(product.getSerialNo());
			if (existedProduct != null) {
				errorList.add("管理番号はすでに登録されています。");
			}
		}
		// IMEIコードの妥当性検査
		if (product.getImei() == null || product.getImei().isEmpty()) {
			errorList.add("IMEIは必須です。");
		} else if (product.getImei().length() != 15) {
			errorList.add("IMEIは15文字固定で入力してください。");
		} else {
			Product existedProduct = productRepository.findByImei(product.getImei());
			if (existedProduct != null) {
				errorList.add("IMEIコードはすでに登録されています。");
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

}
