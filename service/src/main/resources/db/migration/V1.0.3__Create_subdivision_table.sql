CREATE TABLE subdivision (
                             subdivision_id INT PRIMARY KEY AUTO_INCREMENT,
                             name VARCHAR(50) NOT NULL,
                             description VARCHAR(50) NOT NULL,
                             label VARCHAR(26) CHECK (label IN ('white', 'black', 'red', 'green', 'yellow', 'purple', 'pink', 'orange', 'grey', 'maroon', 'navy')),
                             type VARCHAR(26) NOT NULL CHECK (type IN ('friendly', 'enemy', 'neutral', 'unknown'))
);
