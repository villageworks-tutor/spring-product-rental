package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
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
}
