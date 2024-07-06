CREATE TABLE subdivision (
                             subdivision_id INT PRIMARY KEY AUTO_INCREMENT,
                             name VARCHAR(50) NOT NULL,
                             description VARCHAR(50) NOT NULL,
                             label VARCHAR(26) CHECK (label IN ('WHITE', 'BLACK', 'RED', 'GREEN', 'YELLOW', 'PURPLE', 'PINK', 'ORANGE', 'GREY', 'MAROON', 'NAVY')),
                             troops_type VARCHAR(26) NOT NULL CHECK (troops_type IN ('FRIENDLY', 'ENEMY', 'NEUTRAL', 'UNKNOWN'))
);

