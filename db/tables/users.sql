SELECT *
FROM milk_tea_hana.users;

INSERT INTO users (User)

UPDATE users AS u
SET u.full_name = 'abc',
    u.age       = 15,
    u.address   = 'abc',
    u.email     = 'abc@gmail.com',
    u.createdAT = now(),
    u.updatedAT = now(),
    u.img       = 'https://anhdep123.com/wp-content/uploads/2020/11/avatar-facebook-mac-dinh-nam.jpeg'
WHERE u.userId = 34;

SELECT *
FROM users
WHERE userName LIKE '%m%'
   OR full_name LIKE '%m%'
   OR phone LIKE '%m%'
   OR age LIKE '%m%'
   OR email LIKE '%m%'
   OR address LIKE '%m%'
   OR role LIKE '%m%';