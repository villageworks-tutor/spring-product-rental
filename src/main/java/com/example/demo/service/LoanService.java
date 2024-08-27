package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.formbean.LoanFormBean;
import com.example.demo.repository.LoanRepository;

@Service
public class LoanService {
	
	@Autowired
	LoanRepository loanRepository;

	/**
	 * 表示用のすべての貸出状況リストを取得する
	 * @return 
	 */
	public List<LoanFormBean> findAllForDisplay() {
		// 貸出状況リストを取得
		List<LoanFormBean> list = loanRepository.findAllWithUserName();
		// 利用状況を設定
		this.setAvailable(list);
		// 貸出状況リストを返却
		return list;
	}

	/**
	 * 貸出状況リストの要素の利用状況フラグを設定する
	 * @param list 利用状況が設定された貸出状況リスト
	 */
	private void setAvailable(List<LoanFormBean> list) {
		for (LoanFormBean bean : list) {
			// 貸出日と返却日が両方ともnullではない場合
			if (!(bean.getLendDate() == null && bean.getReturnDate() == null)) {
				// 「利用不可」とする
				bean.setAvailable(false);
			}
		}
	}
	
}
