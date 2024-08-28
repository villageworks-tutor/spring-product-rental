package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.formbean.RentalFormBean;
import com.example.demo.entity.Rental;
import com.example.demo.service.RentalService;

@Controller
public class RentalController {
	
	@Autowired
	private RentalService rentalService;

	/**
	 * 貸出状況一覧表示
	 * @param model スコープ
	 * @return 遷移先Thymeleafテンプレート名
	 */
	@GetMapping("/rentals")
	public String showLoanList(Model model) {
		List<RentalFormBean> list = rentalService.findAllForDisplay();
		model.addAttribute("loans", list);
		return "list";
	}
	
	/**
	 * 製品貸出処理
	 * @param productId 製品通番
	 * @param userId	ユーザID
	 * @return 遷移先Thymeleafテンプレート名
	 */
	@PostMapping("/api/rentals/rent")
	public String rentProduct(
				@RequestParam("id") int id,
				@RequestParam("productSerial") int productSerial, 
				@RequestParam("userId") int userId
			) {
		// リクエストパラメータをもとに貸出インスタンスを生成：Loanクラスにbuilderパターンを起用
		Rental rental = Rental.builder()
							  .id(id)
							  .lendDate(LocalDate.now())
							  .productSerial(productSerial)
							  .userId(userId)
							  .build();
		// 貸出処理の実行
		rentalService.execute(rental);
		return "redirect:/rentals";
	}

	/**
	 * 製品返却処理
	 * @param productId 製品通番
	 * @param userId	ユーザID
	 * @return 遷移先Thymeleafテンプレート名
	 */
	@PostMapping("/api/rentals/return")
	public String returnProduct(
			@RequestParam("id") int id,
			@RequestParam("productSerial") int productSerial, 
			@RequestParam("userId") int userId,
			@RequestParam("lendDate") LocalDate lendDate
		) {
		// リクエストパラメータをもとに貸出インスタンスを生成：Loanクラスにbuilderパターンを起用
		Rental rental = Rental.builder()
							  .id(id)
							  .productSerial(productSerial)
							  .userId(userId)
							  .lendDate(lendDate)
							  .returnDate(LocalDate.now())
							  .build();
		// 返却処理の実行
		rentalService.execute(rental);
		// 画面遷移：貸出状況一覧表示にリダイレクト
		return "redirect:/rentals";
	}

}
