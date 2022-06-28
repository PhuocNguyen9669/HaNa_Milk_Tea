CREATE TABLE products
(
    productId       int PRIMARY KEY AUTO_INCREMENT,
    nameProduct     VARCHAR(255) NOT NUll,
    quantityProduct VARCHAR(255) NOT NUll,
    typeProduct     VARCHAR(255) NOT NUll,
    decription      VARCHAR(255) NOT NUll,
    image           LONGTEXT     NOT NULL,
    createdAT       DATETIME     NOT NUll,
    updatedAT       DATETIME     NOT NULL
);
)


INSERT INTO `milk_tea_hana`.`products` (`nameProduct`, `priceProduct`, `quantityProduct`, `typeProduct`, `description`, `image`, `createdAT`, `updatedAT`) VALUES ('Thái Xanh', '20000', '50', 'Trà Sữa', 'Còn hàng', 'https://congthucphache.com/wp-content/uploads/2019/12/thai-xanh.jpg', now(), now());
INSERT INTO `milk_tea_hana`.`products` (`nameProduct`, `priceProduct`, `quantityProduct`, `typeProduct`, `description`,
                                        `image`, `createdAT`, `updatedAT`)
VALUES ('Thái Đỏ', '20000', '40', 'Trà Sữa', 'Còn hàng',
        'https://congthucphache.com/wp-content/uploads/2019/12/tra-sua-thai-do.jpg', now(), now());
INSERT INTO `milk_tea_hana`.`products` (`nameProduct`, `priceProduct`, `quantityProduct`, `typeProduct`, `description`,
                                        `image`, `createdAT`, `updatedAT`)
VALUES ('Socola', '22000', '90', 'Trà Sữa', 'Còn hàng',
        'https://autoshop.com.vn/wp-content/uploads/2020/04/cach-lam-tra-sua-socola.jpg', now(), now());
INSERT INTO `milk_tea_hana`.`products` (`nameProduct`, `priceProduct`, `quantityProduct`, `typeProduct`, `description`,
                                        `image`, `createdAT`, `updatedAT`)
VALUES ('Bánh tráng trộn', '10000', '500', 'Món ăn vặt', 'Còn hàng',
        'https://cdn-www.vinid.net/8e74c3a4-cach-lam-banh-trang-tron.jpg', now(), now());
INSERT INTO `milk_tea_hana`.`products` (`nameProduct`, `priceProduct`, `quantityProduct`, `typeProduct`, `description`,
                                        `image`, `createdAT`, `updatedAT`)
VALUES ('Khoai tây chiên', '16000', '60', 'Món ăn vặt', 'Còn hàng',
        'https://cdn.tgdd.vn/Files/2020/08/19/1281398/cach-lam-khoai-tay-chien-trong-10-phut-voi-noi-chien-khong-dau-khoai-gion-de-lau-cung-khong-mem-202008191207088100.jpg',
        now(), now());


UPDATE products AS p
SET p.nameProduct    = ?,
    p.priceProduct= ?,
    p.quantityProduct= ?,
    p.typeProduct= ?,
    p.`description`= ?,
    p.updatedAT=now(),
    p.image          = ?
WHERE p.productId = ?;