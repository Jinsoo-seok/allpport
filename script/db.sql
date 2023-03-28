CREATE TABLE `allpport`.`user_info` (
    `user_id` INT NOT NULL AUTO_INCREMENT,
    `user_name` VARCHAR(45) NULL,
    `user_pw` VARCHAR(200) NULL,
    PRIMARY KEY (`user_id`),
    INDEX `index_name` (`user_name` ASC) VISIBLE,
    INDEX `index_name_password` (`user_name` ASC, `user_pw` ASC) VISIBLE
);
