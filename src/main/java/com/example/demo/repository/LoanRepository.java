package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.controller.formbean.LoanFormBean;
import com.example.demo.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

	/**
	 * 表示用の貸出状況レコードを取得する
	 * @return 貸出状況リスト
	 */
	@Query(value = "SELECT new com.example.demo.controller.formbean.LoanFormBean(l.id, l.productSerial, l.userId, u.name, l.lendDate, l.returnDate) FROM Loan l LEFT JOIN User u ON l.userId = u.id")
	List<LoanFormBean> findAllWithUserName();

}
