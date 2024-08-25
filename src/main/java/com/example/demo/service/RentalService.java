package com.example.demo.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.entity.RentalRecord;
import com.example.demo.entity.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.RentalRecordRepository;
import com.example.demo.repository.UserRepository;

/**
 * 製品貸出サービス：返却サービスを含む
 */
@Service
public class RentalService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalRecordRepository rentalRecordRepository;

    /**
     * 製品貸出処理
     * @param productId 製品通番
     * @param userId    ユーザID
     */
    public void rentProduct(Long productId, Long userId) {
    	
    	// 指定された製品通番の製品をデータベースから取得
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalProduct.isPresent() && optionalUser.isPresent() && optionalProduct.get().isAvailable()) {
        	// 貸し出せる製品がありかつ指定されたユーザが存在する場合
        	
        	// 貸出クラスのインスタンスを生成
            RentalRecord record = new RentalRecord();
            record.setProduct(optionalProduct.get());
            record.setUser(optionalUser.get());
            record.setRentalDate(LocalDate.now());
            // 生成した貸出インスタンスを永続化（登録）
            rentalRecordRepository.save(record);
            
            // 製品のインスタンスを取得
            Product product = optionalProduct.get();
            // 製品の利用不可
            product.setAvailable(false);
            // 製品インスタンスを永続化（更新：利用状況を変更）
            productRepository.save(product);
            
        } else {
        	// それ以外の場合：例外処理に丸投げ
            throw new IllegalArgumentException("Product not available or User not found");
        }
    }

    /**
     * 製品返却処理
     * @param productId 製品通番
     * @param userId    ユーザID
     */
    public boolean returnProduct(Long productId) {
        // 貸出情報を検索：指定した製品連番の製品でかつ返却日がnullであるレコードを取得
        RentalRecord rentalRecord = rentalRecordRepository.findByProductIdAndReturnDateIsNull(productId);
        
    	// 貸出情報がある場合
        if (rentalRecord != null) {
        	
            // 返却日を設定
            rentalRecord.setReturnDate(LocalDate.now());
            rentalRecordRepository.save(rentalRecord);

            // 指定した製品番号に合致した製品でnullではないものを取得：メソッドチェーンにしても可
            // Product product = productRepository.findById(productId).orElse(null);
            Optional<Product> optionalProduct = productRepository.findById(productId);
            Product product = optionalProduct.orElse(null);
            
            // 取得した製品が存在する場合：trueを返却
            if (product != null) {
                product.setAvailable(true);      // 利用可に変更
                productRepository.save(product); // 製品を永続化
                return true;
            }
        }
        // 貸出情報がなかった場合
        return false;
    }
}
