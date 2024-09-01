SELECT
  rentals.id,
  rentals.product_serial,
  rentals.user_id,
  users.name,
  rentals.lend_date,
  rentals.return_date
FROM
  rentals
  LEFT JOIN users
  ON users.id = rentals.user_id;

/*
SELECT 
  new com.example.demo.controller.formbean.RentalFormBean(
    rental.id, 
    rental.productSerial, 
    rental.userId, 
    user.name, 
    rental.lendDate, 
    rental.returnDate
  ) 
FROM Rental rental LEFT JOIN User user ON rental.userId = user.id ORDER BY rental.id ASC")
*/
