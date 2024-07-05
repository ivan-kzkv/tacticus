CREATE TABLE unit (
                      unit_id INT PRIMARY KEY AUTO_INCREMENT,
                      unit_name VARCHAR(50) NOT NULL,
                      description VARCHAR(256) NOT NULL,
                      unit_icon VARCHAR(50) NOT NULL,
                      unit_type_id INT NOT NULL,
                      amount INT NOT NULL,
                      subdivision INT ,
                      FOREIGN KEY (subdivision) REFERENCES subdivision(subdivision_id),
                      FOREIGN KEY (unit_type_id) REFERENCES unit_type(unit_type_id)
);

INSERT INTO unit (unit_name, description, unit_type_id, unit_icon, amount)
VALUES 
       ('стрілець', 'базовий елемент з легкою стрілецькою зброєю типу гвинтівка', 1, 'shooter_mark.svg', 1),
       ('снайпер', 'військовослужбовець озброєний снайперською гвинтівкою', 1, 'sniper_mark.svg', 1),
       ('навідник', 'військовослужбовець озброєний легким кулеметом типу РПК', 1, 'aimer_mark.svg', 1),
       ('кулеметник', 'військовослужбовець озброєний кулеметом типу ПКМ', 1, 'machine_gunner_mark.svg', 1),
       ('гранатометник', 'військовослужбовець озброєний легким піготним гранатометом', 1, 'grenade_launcher_mark.svg', 1),
       ('міномет-60', 'мінометний розрахунок, 60мм міномету', 2, 'mortar_60_mark.svg', 3),
       ('міномет-80', 'мінометний розрахунок, 80мм міномету', 2, 'mortar_80_mark.svg', 3),
       ('АГС', 'розрахунок автоматичного гранатомету', 2, 'agl_mark.svg', 2),
       ('ПТРК', 'розрахунок протитанкового ракетного комплексу', 2, 'anti_tank_missile_mark.svg', 2),
       ('БТР', 'бронетранспортер для транспортування і підсилення живої сили', 3, 'btr_mark.svg', 3),
       ('БМП', 'бойова машина піхоти для транспортування і підсилення живої сили', 3, 'bmp_mark.svg', 3),
       ('Т-90', 'Танк Т-90 з екіпажем. Базова модель', 3, 'bmp_mark.svg', 3);
