CREATE TABLE commit_unit_state (
                                 commit_unit_state_id INT PRIMARY KEY AUTO_INCREMENT,
                                 commit_id INT,
                                 unit_id INT,
                                 latitude DECIMAL(9,6),
                                 longitude DECIMAL(9,6),
                                 active BOOLEAN,
                                 FOREIGN KEY (commit_id) REFERENCES commit(commit_id),
                                 FOREIGN KEY (unit_id) REFERENCES unit(unit_id)
);
