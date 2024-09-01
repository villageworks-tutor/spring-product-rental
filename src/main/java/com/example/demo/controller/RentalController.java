package com.example.demo.controller;

import java.time.LocalDate;
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
import com.example.demo.controller.formbean.RentalFormBean;
import com.example.demo.entity.Product;
import com.example.demo.entity.Rental;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProductService;
import com.example.demo.service.RentalService;

@Controller
@RequestMapping("/rentals")
public class RentalController {
	
	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/entry")
	public String add(
			@RequestParam(name = "productSerial", defaultValue = "") Integer serialNo,
			Model model) {
		// リクエストパラメータをもとに貸出インスタンスを生成
		Rental rental = new Rental(serialNo);
		
		// 入力値チェック
		List<String> errorList = rentalService.invalidate(rental);
		// 製品管理番号存在チェック
		Product product = productService.findBySerialNo(serialNo);
		// 存在しなかった場合
		if (product == null) {
			errorList.add("指定された製品管理番号の製品は登録されていません。");
		}
		// エラーリストにメッセージがある場合
		if (errorList.size() > 0) {
			model.addAttribute("errorLiist", errorList);
			this.setPageHeader(model);
			return "rental/list";
		}
		rentalService.save(rental);
		// 画面遷移
		return "redirect:/rentals";
	}

	/**
	 * 貸出状況一覧表示
	 * @param model スコープ
	 * @return 遷移先Thymeleafテンプレート名
	 */
	@GetMapping("") // @RequestMappingを宣言しているので「/rentals」というパターンに対応するGetMappingは空文字列で指定することが正しい
	public String showLoanList(Model model) {
		List<RentalFormBean> list = rentalService.findAllForDisplay();
		if (list.size() > 0) {
			model.addAttribute("rentals", list);
		}
		List<Product> productList = productService.findAll();
		model.addAttribute("productList", productList);
		// ページヘッダの設定
		this.setPageHeader(model);
		// 画面遷移
		return "rental/list";
	}
	
	/**
	 * 製品貸出処理
	 * @param productId 製品通番
	 * @param userId	ユーザID
	 * @return 遷移先Thymeleafテンプレート名
	 */
	@PostMapping("/rent")
	public String rentProduct(
				@RequestParam("id") Integer id,
				@RequestParam("productSerial") Integer productSerial, 
				@RequestParam(name = "userId", defaultValue = "") Integer userId,
				Model model
			) {
		// リクエストパラメータをもとに貸出インスタンスを生成：Loanクラスにbuilderパターンを起用
		Rental rental = Rental.builder()
							  .id(id)
							  .lendDate(LocalDate.now())
							  .productSerial(productSerial)
							  .userId(userId)
							  .build();
		// 利用者IDが未入力の場合
		if (userId == null) {
			// 台帳IDと選択された製品管理番号をスコープに登錄
			model.addAttribute("id", id);
			model.addAttribute("productSerial", productSerial);
			model.addAttribute("isRent", "");
			// 貸出状況リストを取得
			List<RentalFormBean> list = rentalService.findAllForDisplay();
			model.addAttribute("rentals", list);
			// 利用者洗濯用利用者リストを取得
			List<User> userList = userRepository.findAllByOrderById();
			model.addAttribute("userList", userList);
			// ページヘッダの設定
			this.setPageHeader(model);
			// 画面遷移
			return "rental/list";
		}
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
	@PostMapping("/return")
	public String returnProduct(
			@RequestParam("id") Integer id,
			@RequestParam("productSerial") Integer productSerial, 
			@RequestParam("userId") Integer userId,
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
	
	/**
	 * 製品貸出状況一覧画面用のページヘッダを設定する
	 * @param model スコープ
	 */
	private void setPageHeader(Model model) {
		List<LinkFormBean> linkList = new ArrayList<LinkFormBean>();
		linkList.add(new LinkFormBean("/product/list", "製品管理"));
		linkList.add(new LinkFormBean("/user/list", "利用者管理"));
		model.addAttribute("linkList", linkList);
	}
	
}
