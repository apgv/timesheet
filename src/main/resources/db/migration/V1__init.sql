CREATE TABLE users (
  user_id    MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50)        NOT NULL,
  last_name  VARCHAR(50)        NOT NULL,
  username   VARCHAR(50)        NOT NULL UNIQUE,
  password   VARCHAR(60)        NOT NULL,
  enabled    BOOLEAN            NOT NULL
);

CREATE TABLE authorities (
  user_id   MEDIUMINT UNSIGNED NOT NULL,
  authority VARCHAR(50)        NOT NULL,
  CONSTRAINT fk_authorities_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);
CREATE UNIQUE INDEX ix_auth_username ON authorities (user_id, authority);