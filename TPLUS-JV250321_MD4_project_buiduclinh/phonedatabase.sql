CREATE DATABASE phone_database;
USE phone_database;
# SHOW DATABASES ;
# DROP DATABASE phone_database;

CREATE TABLE admin
(
    id       INT PRIMARY KEY AUTO_INCREMENT, -- Mã định danh Admin
    username VARCHAR(50)  NOT NULL UNIQUE,   -- tên đăng nhập
    password VARCHAR(100) NOT NULL           -- Mật khẩu mã hóa
);

INSERT INTO admin(username, password)
VALUES ('admin', '123456789');

-- Đăng nhập
# DROP PROCEDURE login;
DELIMITER $$
CREATE PROCEDURE login(
    IN input_username VARCHAR(50),
    IN input_password VARCHAR(100)
)
BEGIN
    SELECT *
    FROM admin
    WHERE username = input_username
      AND password = input_password;
END $$
DELIMITER ;

-- lấy tên admin
DELIMITER $$
CREATE PROCEDURE get_admin_user(
    IN input_user VARCHAR(50)
)
BEGIN
    SELECT *
    FROM admin
    WHERE username = input_user;
END $$
DELIMITER ;

CREATE TABLE product
(
    id    INT            NOT NULL PRIMARY KEY AUTO_INCREMENT, -- Mã sản phẩm
    name  VARCHAR(100)   NOT NULL,                            -- tên sản phẩm
    brand VARCHAR(50)    NOT NULL,                            -- nhãn hàng
    price DECIMAL(10, 2) NOT NULL,                            -- giá sản phẩm
    stock INT            NOT NULL                             -- số lượng tồn kho
);

CREATE TABLE customer
(
    id      INT          NOT NULL PRIMARY KEY AUTO_INCREMENT, -- mã khách hàng
    name    VARCHAR(100) NOT NULL,                            -- họ tên khách hàng
    phone   VARCHAR(20)  NULL,                                -- số điện thoại
    email   VARCHAR(100) NULL UNIQUE,                         -- email người dùng
    address VARCHAR(255) NULL                                 -- địa chỉ người dùng
);

CREATE TABLE invoice
(
    id           INT PRIMARY KEY AUTO_INCREMENT,     -- mã hóa đơn
    customer_id  INT,                                -- mã người dùng
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP, -- ngày tạo hóa đơn
    total_amount DECIMAL(12, 2) NOT NULL             -- tổng tiền hóa đơn
);

CREATE TABLE invoice_details
(
    id         INT PRIMARY KEY AUTO_INCREMENT, -- mã chi tiết
    invoice_id INT,                            -- mã hóa đơn
    product_id INT,                            -- mã sản phẩm
    quantity   INT            NOT NULL,        -- số lượng
    unit_price DECIMAL(12, 2) NOT NULL         -- đơn giá tại thời điểm mua
);

ALTER TABLE invoice
    ADD CONSTRAINT customer_id
        FOREIGN KEY (customer_id)
            REFERENCES customer (id)
            ON DELETE CASCADE;

ALTER TABLE invoice_details
    ADD CONSTRAINT invoice_id
        FOREIGN KEY (invoice_id)
            REFERENCES invoice (id)
            ON DELETE CASCADE;

ALTER TABLE invoice_details
    ADD CONSTRAINT product_id
        FOREIGN KEY (product_id)
            REFERENCES product (id)
            ON DELETE CASCADE;


-- hiển thị danh sách sản phẩm
DELIMITER $$
CREATE PROCEDURE get_all_product()
BEGIN
    SELECT * FROM product;
END $$
DELIMITER ;

-- thêm mới sản phẩm
DELIMITER $$
CREATE PROCEDURE add_product(
    IN add_product_name VARCHAR(100),
    IN add_brand_name VARCHAR(50),
    IN add_price DECIMAL(10, 2)
)
BEGIN
    INSERT INTO product(name, brand, price, stock)
    VALUES (add_product_name, add_brand_name, add_price, 1);
END $$
DELIMITER ;


-- cập nhật sản phẩm
DELIMITER $$
CREATE PROCEDURE update_product(
    IN update_product_id INT,
    IN update_product_name VARCHAR(100),
    IN update_brand_name VARCHAR(50),
    IN update_price DECIMAL(10, 2),
    IN update_stock INT
)
BEGIN
    UPDATE product
    SET name  = update_product_name,
        brand = update_brand_name,
        price = update_price,
        stock = update_stock
    WHERE id = update_product_id;
END $$
DELIMITER ;


-- xoá sản phẩm
DELIMITER $$
CREATE PROCEDURE delete_product(
    IN delete_id INT
)
BEGIN
    DELETE
    FROM product
    WHERE id = delete_id;
END $$
DELIMITER ;

-- tìm theo nhãn sản phẩm
# DROP PROCEDURE find_product_by_brand;
DELIMITER $$
CREATE PROCEDURE find_product_by_brand(
    IN find_product_name_by_brand VARCHAR(100)
)
BEGIN
    SELECT *
    FROM product
    WHERE brand LIKE CONCAT('%', find_product_name_by_brand, '%');
end $$
DELIMITER ;

-- tìm kiếm sản phẩm theo khoảng giá

# DROP PROCEDURE find_prodcut_by_price;

DELIMITER $$
CREATE PROCEDURE find_product_by_price(
    IN find_product_by_price1 DECIMAL(10, 2),
    IN find_product_by_price2 DECIMAL(10, 2)
)
BEGIN
    SELECT *
    FROM product
    WHERE price >= find_product_by_price1
      AND price <= find_product_by_price2;
END $$
DELIMITER ;


-- tìm kiếm sản phẩm theo giá trị tồn kho
DELIMITER $$
CREATE PROCEDURE find_product_by_stock(
    IN find_product_by_stock1 INT,
    IN find_product_by_stock2 INT
)
BEGIN
    SELECT *
    FROM product
    WHERE stock >= find_product_by_stock1
      AND stock <= find_product_by_stock2;
END $$
DELIMITER ;

-- tìm kiếm sản phẩm theo id
DELIMITER $$
CREATE PROCEDURE find_product_by_id(
    IN find_by_id INT
)
BEGIN
    SELECT *
    FROM product
    WHERE id = find_by_id;
END $$
DELIMITER ;

-- tìm kiếm sản phẩm theo tên
DELIMITER $$
CREATE PROCEDURE find_product_by_name(
    IN find_product_by_name VARCHAR(100)
)
BEGIN
    SELECT *
    FROM product
    WHERE name = find_product_by_name;
END $$
DELIMITER ;


-- hiển thị thông tin khách hàng

DELIMITER $$
CREATE PROCEDURE get_all_customer_information()
BEGIN
    SELECT * FROM customer;
END $$
DELIMITER ;


# -- thêm mới khách hàng
DELIMITER $$
CREATE PROCEDURE add_customer(
    IN add_customer_name VARCHAR(100),
    IN add_customer_phone VARCHAR(20),
    IN add_customer_email VARCHAR(100),
    IN add_customer_address VARCHAR(255)
)
BEGIN
    INSERT INTO customer(name, phone, email, address)
    VALUES (add_customer_name, add_customer_phone, add_customer_email, add_customer_address);
END $$
DELIMITER ;

-- cập nhật thông tin khách hàng
DELIMITER $$
CREATE PROCEDURE update_customer(
    IN update_customer_id INT,
    IN update_customer_name VARCHAR(100),
    IN update_customer_phone VARCHAR(20),
    IN update_customer_email VARCHAR(100),
    IN update_customer_address VARCHAR(255)
)
BEGIN
    UPDATE customer
    SET name    = update_customer_name,
        phone   = update_customer_phone,
        email   = update_customer_email,
        address = update_customer_address
    WHERE id = update_customer_id;
END $$
DELIMITER ;


-- Xóa thông tin khách hàng
DELIMITER $$
CREATE PROCEDURE delete_customer(
    IN delete_id INT
)
BEGIN
    DELETE
    FROM customer
    WHERE id = delete_id;
END $$
DELIMITER ;


-- tìm kiếm khách theo tên
DELIMITER $$
CREATE PROCEDURE find_customer_name(
    find_customer_name VARCHAR(100)
)
BEGIN
    SELECT *
    FROM customer
    WHERE name LIKE CONCAT('%', find_customer_name, '%');
END $$
DELIMITER ;


-- tìm kiếm khách hàng theo id
DELIMITER $$
CREATE PROCEDURE find_customer_id(
    find_customer_id INT
)
BEGIN
    SELECT *
    FROM customer
    WHERE id = find_customer_id;
END $$
DELIMITER ;


-- tìm kiếm theo khách hàng theo email
DELIMITER $$
CREATE PROCEDURE find_customer_email(
    find_customer_email VARCHAR(100)
)
BEGIN
    SELECT *
    FROM customer
    WHERE email = find_customer_email;
END $$
DELIMITER ;