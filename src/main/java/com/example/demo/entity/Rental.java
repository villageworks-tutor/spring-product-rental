package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rentals")
@Data
@Builder
@NoArgsConstructor
public class Rental {
	/**
	 * フィールド
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;               // 台帳ID
	@Column(name = "product_serial")
	private Integer productSerial;    // 製品管理番号
	@Column(name = "user_id")
	private Integer userId;           // 利用者ID
	@Column(name = "lend_date")
	private LocalDate lendDate;   // 貸出日
	@Column(name = "return_date")
	private LocalDate returnDate; // 返却日
	@Transient
	private String userName;      // 利用者名（表示用）
	
	/**
	 * すべてのフィールドを引数とするコンストラクタ
	 * @param id            台帳ID
	 * @param productSerial 製品管理番号
	 * @param userId        利用者ID
	 * @param lendDate      貸出日
	 * @param returnDate    減却日
	 * @param userName      利用者名（表示用）
	 */
	public Rental(Integer id, Integer productSerial, Integer userId, LocalDate lendDate, LocalDate returnDate, String userName) {
		this.id = id;
		this.productSerial = productSerial;
		this.userId = userId;
		this.lendDate = lendDate;
		this.returnDate = returnDate;
		this.userName = userName;
	}
	
	
}
