DROP TABLE IF EXISTS costs;

CREATE TABLE costs (
id bigserial PRIMARY KEY,
content varchar(250) NOT NULL, 
price int NOT NULL, 
user_id bigint NOT NULL, 
category varchar(50) NOT NULL, 
date timestamp,
constraint fk_user_id foreign key(user_id) references users(id)
);

INSERT INTO costs(content, price, user_id, category, date)
VALUES('Netflix', 5, 2, 'Subscription', '2022/11/05');

INSERT INTO costs(content, price, user_id, category, date)
VALUES('Hulu', 8, 2, 'Subscription', '2022/11/05');

INSERT INTO costs(content, price, user_id, category, date)
VALUES('T-Shirt', 25, 1, 'Clothing', '2022/11/23');