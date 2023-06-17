INSERT INTO USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin'),
       ('Guest', 'guest@gmail.com', '{noop}guest');

INSERT INTO USER_ROLE (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (NAME, ADDRESS, ENABLED)
VALUES ('Макдоналдс', 'ул. Зеленая, 31', true),
       ('Шаляпин', 'ул. Мира, 67', false),
       ('Васаби', 'ул. Бумажная, д.20', true);

INSERT INTO DISH_REF (NAME, PRICE, RESTAURANT_ID)
VALUES ('Филе-о-Фиш', 12700, 1),
       ('Чикенбургер', 5000, 1),
       ('Чикен Макнаггетс (20шт)', 27200, 1),

       ('Борщ с фасолью и чесночной пампушкой', 49000, 2),
       ('Рассольник по-шаляпински', 55000, 2),
       ('Шницель из телятины с квашеной капустой и печеным яблоком', 95000, 2),

       ('Ролл Сочная креветка', 25700, 3),
       ('Ролл Огонь', 31700, 3),
       ('Ролл Калифорния с цыпленком', 12900, 3);

INSERT INTO MENU_ITEM (ACTUAL_DATE, RESTAURANT_ID, DISH_REF_ID)
VALUES
(CURRENT_DATE, 1, 1),
(CURRENT_DATE, 1, 2),
(CURRENT_DATE, 1, 3),

(CURRENT_DATE, 2, 4),
(CURRENT_DATE, 2, 5),
(CURRENT_DATE, 2, 6),

(CURRENT_DATE, 3, 7),
(CURRENT_DATE, 3, 8),
(CURRENT_DATE, 3, 9);

INSERT INTO VOTE (USER_ID, ACTUAL_DATE, ACTUAL_TIME, RESTAURANT_ID)
VALUES (1, CURRENT_DATE, '12:30:00', 1),
       (1, CURRENT_DATE-1, '09:15:00', 1),
       (1, CURRENT_DATE-2, '15:55:00', 3),
       (2, CURRENT_DATE-1, '08:15:00', 2),
       (2, CURRENT_DATE-2, '12:55:00', 3);