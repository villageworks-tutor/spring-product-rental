package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
	/**
	 * フィールド
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;       // 管理ID
    private String  imei; // IMEIコード
    @Column(name = "serial_no")
    private int serialNo; // 管理番号
}