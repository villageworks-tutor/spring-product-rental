package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.controller.formbean.RentalFormBean;
import com.example.demo.entity.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

	/**
	 * 表示用の貸出状況レコードを台帳IDの昇順で取得する
	 * @return 貸出状況リスト
	 */
	@Query(value = ""
			+ "SELECT "
			+ "	new com.example.demo.controller.formbean.RentalFormBean("
			+ "		rental.id,"
			+ " 	rental.productSerial,"
			+ " 	rental.userId, "
			+ "		user.name, "
			+ "		rental.lendDate, "
			+ "		rental.returnDate"
			+ "	) "
			+ "FROM Rental rental "
			+ "	LEFT JOIN User user "
			+ "	ON rental.userId = user.id "
			+ "WHERE"
			+ "	(rental.lendDate IS NULL) OR (rental.returnDate IS NULL)"
			+ "ORDER BY rental.id ASC")
	List<RentalFormBean> findAllWithUserNameOrderByIdAsc();

	Optional<Rental> findByProductSerial(Integer serialNo);

}
