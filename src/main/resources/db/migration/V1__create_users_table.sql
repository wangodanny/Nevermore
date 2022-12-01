DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id bigserial PRIMARY KEY,
  username varchar(50) NOT NULL UNIQUE,
  password varchar(50) NOT NULL,
  salary integer,
  enabled boolean NOT NULL
);

CREATE TABLE authorities (
  id bigserial PRIMARY KEY,
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities(username, authority);

INSERT INTO users(username, password, salary, enabled)
VALUES ('danny', '123456', 1200, true);
INSERT INTO users(username, password,  salary, enabled)
VALUES ('hisham', '123456', 2500, true);
INSERT INTO users(username, password,  salary, enabled)
VALUES ('thomas', '123456', 1500, true);
INSERT INTO users(username, password,  salary, enabled)
VALUES ('debs', '123456', 2800, true);


INSERT INTO authorities(username, authority)
VALUES ('danny', 'ROLE_USER');
INSERT INTO authorities(username, authority)
VALUES ('hisham', 'ROLE_USER');
INSERT INTO authorities(username, authority)
VALUES ('thomas', 'ROLE_USER');
INSERT INTO authorities(username, authority)
VALUES ('debs', 'ROLE_USER');