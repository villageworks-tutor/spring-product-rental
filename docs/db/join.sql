SELECT
  loans.id,
  loans.product_serial,
  loans.user_id,
  users.name,
  loans.lend_date,
  loans.return_date
FROM
  loans
  JOIN users
  ON users.id = loans.user_id;
