CREATE TABLE commit (
                        commit_id INT PRIMARY KEY AUTO_INCREMENT,
                        commit_name VARCHAR(50) NOT NULL,
                        description VARCHAR(256),
                        stage int NOT NULL,
                        previous_commit_id INT,
                        next_commit_id INT,
                        scenario_id INT NOT NULL,
                        FOREIGN KEY (previous_commit_id) REFERENCES commit(commit_id),
                        FOREIGN KEY (next_commit_id) REFERENCES commit(commit_id),
                        FOREIGN KEY (scenario_id) REFERENCES scenario(id)
);
