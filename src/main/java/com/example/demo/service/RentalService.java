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

@Service
public class RentalService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalRecordRepository rentalRecordRepository;

    public void rentProduct(Long productId, Long userId) {
        Optional<Product> product = productRepository.findById(productId);
        Optional<User> user = userRepository.findById(userId);

        if (product.isPresent() && user.isPresent() && product.get().isAvailable()) {
            RentalRecord record = new RentalRecord();
            record.setProduct(product.get());
            record.setUser(user.get());
            record.setRentalDate(LocalDate.now());
            rentalRecordRepository.save(record);

            Product p = product.get();
            p.setAvailable(false);
            productRepository.save(p);
        } else {
            throw new IllegalArgumentException("Product not available or User not found");
        }
    }

    public boolean returnProduct(Long productId) {
        // レンタルレコードを検索
        RentalRecord rentalRecord = rentalRecordRepository.findByProductIdAndReturnDateIsNull(productId);
        if (rentalRecord != null) {
            // 返却日を設定
            rentalRecord.setReturnDate(LocalDate.now());
            rentalRecordRepository.save(rentalRecord);

            // 商品の貸出可を更新
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                product.setAvailable(true);
                productRepository.save(product);
                return true;
            }
        }
        return false;
    }
}
