CREATE TABLE `milk_tea_hana`.`orderitems`
(
    `idorderItems`    INT         NOT NULL,
    `priceProduct`    DECIMAL(10) NOT NULL,
    `quantityProduct` INT         NOT NULL,
    `total`           DECIMAL(10) NOT NULL,
    `createdAT`       DATETIME    NOT NULL,
    `status`          VARCHAR(45) NOT NULL,
    PRIMARY KEY (`idorderItems`)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;
