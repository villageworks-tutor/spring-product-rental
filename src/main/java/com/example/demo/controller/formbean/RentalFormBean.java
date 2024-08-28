package com.example.demo.controller.formbean;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalFormBean {
	/**
	 * フィールド
	 */
	private int id;               // 台帳ID
	private int productSerial;    // 製品管理番号
	private int userId;           // 利用者ID
	private String userName;      // 利用者名
	private LocalDate lendDate;   // 貸出日
	private LocalDate returnDate; // 返却日
	private boolean available;    // 貸出状況ステータス
	
	/**
	 * コンストラクタ
	 * @param id            台帳ID
	 * @param productSerial 製品管理番号
	 * @param userId        利用者ID
	 * @param userName      利用者名
	 * @param lendDate      貸出日
	 * @param returnDate    返却日
	 */
	public RentalFormBean(
			int id, 
			int productSerial, 
			int userId, 
			String userName, 
			LocalDate lendDate,
			LocalDate returnDate
		   ) {
		this.id = id;
		this.productSerial = productSerial;
		this.userId = userId;
		this.userName = userName;
		this.lendDate = lendDate;
		this.returnDate = returnDate;
		this.available = true; // 利用状況はデフォルト値としてtrueを設定
	}
	
}
