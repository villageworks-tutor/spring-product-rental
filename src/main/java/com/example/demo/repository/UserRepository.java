package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * すべての利用者を利用者IDの昇順で並べ替えた利用者リストを取得する
	 * SELECT * FROM users ORDER BY id
	 * @return 利用者IDの昇順で並べ替えた利用者リスト
	 */
	List<User> findAllByOrderById();

}
