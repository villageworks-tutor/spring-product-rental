package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.RentalService;

@Controller
public class RentalController {
	
    @Autowired
    private ProductService productService;

    @Autowired
    private RentalService rentalService;

    /**
     * 製品一覧表示
     * @param model スコープ
     * @return 遷移先Thymeleafテンプレート名
     */
    @GetMapping("/products")
    public String showProductList(Model model) {
        List<Product> products = productService.findAllByOrderIdAsc();
        model.addAttribute("products", products);
        return "product_list";
    }
    
    /**
     * 製品貸出処理
     * @param productId 製品通番
     * @param userId    ユーザID
     * @return 遷移先Thymeleafテンプレート名
     */
    @PostMapping("/api/rentals/rent")
    public String rentProduct(@RequestParam Long productId, @RequestParam Long userId) {
        rentalService.rentProduct(productId, userId);
        return "redirect:/products";
    }

    /**
     * 製品返却処理
     * @param productId 製品通番
     * @param userId    ユーザID
     * @return 遷移先Thymeleafテンプレート名
     */
    @PostMapping("/api/rentals/return")
    public String returnProduct(@RequestParam Long productId, Model model) {
        boolean isReturned = rentalService.returnProduct(productId);
        model.addAttribute("isReturned", isReturned);
        return "redirect:/products";
    }

}
