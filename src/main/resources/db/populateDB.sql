DELETE
FROM user_roles;
DELETE
FROM votes;
DELETE
FROM meals;
DELETE
FROM users;
DELETE
FROM restaurants;

INSERT INTO users (ID, NAME, EMAIL, PASSWORD)
VALUES (1, 'Oleg', 'olegsmirnov@gm.com', 'simsimopen'),
       (2, 'Anna', 'anna@ya.ru', 'mynameisann'),
       (3, 'Valeria', 'valeria@gm.com', 'lera1010'),
       (4, 'Zaur', 'zaur@mail.ru', 'password'),
       (5, 'Pavel', 'pavel@icl.com', 'pavel2001');

INSERT INTO RESTAURANTS (ID, NAME)
VALUES (6, 'Fastfood'),
       (7, 'Wellmeal'),
       (8, 'Deadhorse'),
       (9, 'Cowsaymoo'),
       (10, 'Bistro');

INSERT INTO MEALS (NAME, PRICE, RESTAURANT_ID)
VALUES ('Crabsburger', 500, 6),
       ('Bread', 150, 6),
       ('Milk', 200, 6),
       ('Ice Cream', 350, 6),
       ('Pizza Pepperoni', 750, 7),
       ('Milk cocktail', 180, 7),
       ('Fish', 450, 7),
       ('Chicken', 320, 7),
       ('Lemonade', 100, 8),
       ('Fresh fruits basket', 220, 8),
       ('Cheezecake', 160, 8),
       ('Sushi', 330, 8),
       ('Pasta', 390, 9),
       ('IDN wot is it:)', 900, 9),
       ('Something from the floor', 100, 9),
       ('Piece of cheeze', 110, 9),
       ('Glass of water', 200, 10),
       ('Glass of tea', 300, 10),
       ('Glass of coffee', 400, 10),
       ('Glass of air', 50, 10);

INSERT INTO USER_ROLES (USER_ID, ROLE)
VALUES (1, 'ADMIN'),
       (2, 'USER'),
       (3, 'ADMIN'),
       (4, 'USER'),
       (5, 'USER');

INSERT INTO VOTES (USER_ID, RESTAURANT_ID, DATE)
VALUES (1, 6, '2022-05-11'),
       (2, 8, '2022-05-11'),
       (3, 9, '2022-05-11'),
       (4, 10, '2022-05-11'),
       (5, 7, '2022-05-11'),
       (1, 7, '2022-05-12'),
       (2, 8, '2022-05-12'),
       (3, 10, '2022-05-12'),
       (4, 8, '2022-05-12'),
       (5, 9, '2022-05-12'),
       (1, 9, '2022-05-15');