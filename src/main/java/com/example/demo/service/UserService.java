package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public List<String> validate(User user) {
		List<String> errorList = new ArrayList<String>();
		if (user.getName() == null || user.getName().isEmpty()) {
			errorList.add("利用者名は必須です。");
		} else if (user.getName().length() > 100) {
			errorList.add("利用者名は100文字以内です。");
		}
		if ((user.getPhone() == null || user.getPhone().isEmpty()) && (user.getEmail() == null || user.getEmail().isEmpty())) {
			errorList.add("電話番号とメールアドレスがともに未入力です。どちらかまたは両方入力してください。");
		}
		return errorList;
	}
	
	/**
	 * 利用者をすべて取得する
	 * @return 利用者IDの昇順で並べ替えられた利用者リスト
	 */
	public List<User> findAll() {
		return userRepository.findAllByOrderById();
	}

	/**
	 * 利用者を登録する
	 * @param user 利用者インスタンス
	 */
	public void store(User user) {
		userRepository.save(user);
	}
	/**
	 * 指定された利用者IDの利用者を取得する
	 * @param id 利用者ID
	 * @return 利用者IDに合致した利用者が存在する場合は利用者インスタンス、それ以外はnull
	 */
	public User findById(int id) {
		Optional<User> entity = userRepository.findById(id);
		if (entity.isPresent()) {
			return entity.get();
		}
		return null;
	}

	/**
	 * 指定された利用者IDの利用者を削除する
	 * @param id 削除対象利用者の利用者ID
	 */
	public void delete(int id) {
		userRepository.deleteById(id);
	}

}
