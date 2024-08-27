package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "loans")
@Data
public class Loan {
	/**
	 * フィールド
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;               // 台帳ID
	@Column(name = "product_serial")
	private int productSerial;    // 製品管理番号
	@Column(name = "user_id")
	private int userId;           // 利用者ID
	@Column(name = "lend_date")
	private LocalDate lendDate;   // 貸出日
	@Column(name = "return_date")
	private LocalDate returnDate; // 返却日
	private String userName;
}
