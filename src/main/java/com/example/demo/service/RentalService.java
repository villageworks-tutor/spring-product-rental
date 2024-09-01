package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.formbean.RentalFormBean;
import com.example.demo.entity.Rental;
import com.example.demo.repository.RentalRepository;

/**
 * 貸出返却処理を担当するサービスクラス
 */
@Service
public class RentalService {
	
	@Autowired
	RentalRepository rentalRepository;
	
	/**
	 * 貸出台帳の貸出情報を変更する
	 * @param rental 変更対象の貸出インスタンス
	 */
	public void execute(Rental rental) {
		// 貸出情報を台帳テーブルに永続化
		rentalRepository.save(rental);
	}
	
	/**
	 * 表示用のすべての貸出状況リストを取得する
	 * @return 貸出状況リスト
	 */
	public List<RentalFormBean> findAllForDisplay() {
		// 貸出状況リストを取得
		List<RentalFormBean> list = rentalRepository.findAllWithUserNameOrderByIdAsc();
		// 利用状況を設定
		this.setAvailable(list);
		// 貸出状況リストを返却
		return list;
	}

	/**
	 * 貸出状況リストの要素の利用状況フラグを設定する
	 * @param list 利用状況が設定された貸出状況リスト
	 */
	private void setAvailable(List<RentalFormBean> list) {
		for (RentalFormBean bean : list) {
			// 貸出日と返却日が両方ともnullではない場合
			if ((bean.getLendDate() != null && bean.getReturnDate() == null)) {
				// 「利用不可」とする
				bean.setAvailable(false);
			}
		}
	}

	public List<String> invalidate(Rental rental) {
		List<String> list = new ArrayList<String>();
		if (rental.getProductSerial() == null) {
			list.add("製品管理番号は必須です。");
		} else if (rental.getProductSerial() < 1) {
			list.add("製品管理番号は正の整数です。");
		}
		return list;
	}

	public void save(Rental rental) {
		rentalRepository.save(rental);
	}

}
