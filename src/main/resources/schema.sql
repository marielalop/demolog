DROP TABLE IF EXISTS TBL_LOG_MESSAGES;

CREATE TABLE TBL_LOG_MESSAGES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  message VARCHAR(250) NOT NULL,
  type INT NOT NULL
);