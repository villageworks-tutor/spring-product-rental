package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.formbean.LinkFormBean;
import com.example.demo.entity.Product;
import com.example.demo.entity.Rental;
import com.example.demo.service.ProductService;
import com.example.demo.service.RentalService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	RentalService rentalService;
	
	/**
	 * 製品マスタ登録処理
	 * @param serialNo 製品管理番号
	 * @param imei     IMEIコード
	 * @param model    スコープ
	 * @return 遷移先Thymeleafテンプレート名
	 */
	@PostMapping("/add")
	public String store(
				@RequestParam(name = "serialNo", defaultValue = "") Integer serialNo,
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
			this.setPageHeader(model);
			return "product/list";
		}
		
		// 製品マスタ登録処理
		productService.store(product);
		
		// 貸出状況一覧に追加
		Rental rental = new Rental();
		rental.setProductSerial(serialNo);
		rentalService.execute(rental);
		
		// 画面遷移：/product/listにリダイレクト
		return "redirect:/product/list";
	}
	
	/**
	 * 初期画面表示：製品マスタ一覧表示
	 * @param model スコープ
	 * @return 遷移先Thymeleafテンプレート名
	 */
	@GetMapping({"", "/list"})
	public String index(Model model) {
		List<Product> productList = productService.findAll();
		model.addAttribute("products", productList);
		model.addAttribute("product", new Product());
		this.setPageHeader(model);
		return "product/list";
	}
	
	/**
	 * 製品貸出状況一覧画面用のページヘッダを設定する
	 * @param model スコープ
	 */
	private void setPageHeader(Model model) {
		List<LinkFormBean> linkList = new ArrayList<LinkFormBean>();
		linkList.add(new LinkFormBean("/rentals", "貸出状況一覧"));
		linkList.add(new LinkFormBean("/user/list", "利用者管理"));
		model.addAttribute("linkList", linkList);
	}
	
}
