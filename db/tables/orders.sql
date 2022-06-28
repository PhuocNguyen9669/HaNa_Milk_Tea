CREATE TABLE `milk_tea_hana`.`orders`
(
    `id`        INT         NOT NULL AUTO_INCREMENT,
    `full_name` VARCHAR(45) NOT NULL,
    `phone`     VARCHAR(45) NOT NULL,
    `age`       INT         NOT NULL,
    `email`     VARCHAR(45) NOT NULL,
    `createdAT` DATETIME    NOT NULL,
    `updatedAT` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
);