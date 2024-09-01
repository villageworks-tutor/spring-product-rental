package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
	/**
	 * フィールド
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;       // 管理ID
    private String  imei; // IMEIコード
    @Column(name = "serial_no")
    private Integer serialNo; // 管理番号
    
    /**
     * コンストラクタ
     * @param imei     IMEIコード
     * @param serialNo 管理番号
     */
	public Product(String imei, Integer serialNo) {
		this.imei = imei;
		this.serialNo = serialNo;
	}
    
    
    
}