package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/add")
	public String store(
				@RequestParam(name = "serialNo") Integer serialNo,
				@RequestParam(name = "imei", defaultValue = "") String imei,
				Model model
			) {
		// リクエストパラメータをもとに製品インスタンスを生成
		Product product = new Product(imei, serialNo);
		// 入力値チェック
		List<String> errorList = productService.validate(product);
		// エラーがある場合
		if (errorList.size() > 0) {
			//　エラーリストと製品インスタンスをスコープに登録
			model.addAttribute("errorList", errorList);
			List<Product> productList = productService.findAll();
			model.addAttribute("products", productList);
			model.addAttribute("product", product);
			return "product/list";
		}
		productService.store(product);
		return "redirect:/product/list";
	}
	
	@GetMapping({"/", "/list"})
	public String index(Model model) {
		List<Product> productList = productService.findAll();
		model.addAttribute("products", productList);
		model.addAttribute("product", new Product());
		return "product/list";
	}
}
