CREATE TABLE unit_type (
                          unit_type_id INT PRIMARY KEY AUTO_INCREMENT,
                          type_name VARCHAR(50) NOT NULL,
                          description VARCHAR(255) NOT NULL,
                          icon_name VARCHAR(255) NOT NULL
);

INSERT INTO unit_type (unit_type_id, type_name, icon_name, description)
VALUES 
       (1, 'бійці', 'humans.svg', 'окремі бійці, як елементи складу груп та взводів. Як приклад стрілець, гранатометник, кулеметник і т.д.'),
       (2, 'групова зброя', 'group_weapons.svg', 'Групове озброєння, яке працює розрахунком із групи людей або ж військова техніка з екіпажем'),
       (3, 'техніка', 'vehicles.svg', 'Військова техніка з екіпажем як розрахунок, що підтримує бійців');
