package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	/**
	 * フィールド
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;       // 利用者ID
	private String name;  // 利用者名
	private String phone; // 連絡先電話番号
	private String email; // 連絡先メールアドレス
	
	/**
	 * コンストラクタ
	 * @param name  利用者名
	 * @param phone 連絡先電話番号
	 * @param email 連絡先電子メールアドレス
	 */
	public User(String name, String phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
}
