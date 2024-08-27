package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.formbean.LoanFormBean;
import com.example.demo.service.LoanService;

@Controller
public class LoanController {
	
    @Autowired
    private LoanService loanService;

	/**
	 * 貸出状況一覧表示
	 * @param model スコープ
	 * @return 遷移先Thymeleafテンプレート名
	 */
	@GetMapping("/rentals")
	public String showLoanList(Model model) {
		List<LoanFormBean> list = loanService.findAllForDisplay();
		model.addAttribute("loans", list);
		return "list";
	}
    
    /**
     * 製品貸出処理
     * @param productId 製品通番
     * @param userId    ユーザID
     * @return 遷移先Thymeleafテンプレート名
     */
    @PostMapping("/api/rentals/rent")
    public String rentProduct(@RequestParam Long productId, @RequestParam Long userId) {
        return "redirect:/rentals";
    }

    /**
     * 製品返却処理
     * @param productId 製品通番
     * @param userId    ユーザID
     * @return 遷移先Thymeleafテンプレート名
     */
    @PostMapping("/api/rentals/return")
    public String returnProduct(@RequestParam Long productId, Model model) {
        // boolean isReturned = rentalService.returnProduct(productId);
        // model.addAttribute("isReturned", isReturned);
        return "redirect:/products";
    }

}
