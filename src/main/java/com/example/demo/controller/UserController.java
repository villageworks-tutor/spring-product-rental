package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable(name = "id") int id) {
		// 削除処理の実行
		userService.delete(id);
		// 画面遷移：/ure/listにリダイレクト
		return "redirect:/user/list";
	}
	
	@PostMapping("/{id}/edit")
	public String update(
				@PathVariable(name = "id") Integer id,
				@RequestParam(name = "name", defaultValue = "") String name,
				@RequestParam(name = "phone", defaultValue = "") String phone,
				@RequestParam(name = "email", defaultValue = "") String email,
				Model model
			) {
		// パスパラメータとリクエストパラメータをもとに更新する利用者インスタンスを生成
		User user = new User(id, name, phone, email);
		// 入力値チェック
		List<String> errorList = userService.validate(user);
		// 入力エラーがある場合
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("user", user);
			model.addAttribute("users", userService.findAll());
			return "user/list";
		}
		// 更新処理の実行
		userService.store(user);
		// 画面遷移：/user/listにリダイレクト
		return "redirect:/user/list";
	}
	
	/**
	 * 更新画面モード切替
	 */
	@GetMapping("/{id}/edit")
	public String edit(
				@PathVariable(name = "id") int id,
				Model model
			) {
		// パスパラメータをもとに利用者インスタンスを取得
		User user = userService.findById(id);
		// 取得した利用者インスタンスとすべての利用者リストををスコープに登録
		model.addAttribute("user", user);
		model.addAttribute("users", userService.findAll());
		// ボタントップの表示文字列を登録
		model.addAttribute("buttonText", "更新");
		// 画面遷移
		return "user/list";
	}
	
	/**
	 * 利用者登録処理
	 * @param name
	 * @param phone
	 * @param email
	 * @param model
	 * @return 遷移先のThymeleafテンプレート名
	 *         エラーがない場合は利用者一覧画面表示にリダイレクト、それ以外の場合は利用者一覧画面
	 */
	@PostMapping("/add")
	public String store(
				@RequestParam(name = "name", defaultValue = "") String name,
				@RequestParam(name = "phone", defaultValue = "") String phone,
				@RequestParam(name = "email", defaultValue = "") String email,
				Model model
			) {
		// リクエストパラメータをもとに利用者インスタンスを生成
		User user = new User(name, phone, email);
		// 入力値チェック
		List<String> errorList = userService.validate(user);
		// 入力エラーがある場合
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("users", userService.findAll());
			model.addAttribute("user", user);
			return "user/list";
		}
		
		userService.store(user);
		
		return "redirect:/user/list";
	}
	
	/**
	 * 利用者一覧画面表示
	 * @param model スコープ
	 * @return 遷移先のThymeleafテンプレート名
	 */
	@GetMapping("/list")
	public String index(Model model) {
		List<User> list = userService.findAll();
		model.addAttribute("users", list);
		model.addAttribute("user",new User());
		return "user/list";
	}
	
}
