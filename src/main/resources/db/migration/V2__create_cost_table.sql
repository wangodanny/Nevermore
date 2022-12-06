DROP TABLE IF EXISTS costs;

CREATE TABLE costs (
id bigserial PRIMARY KEY,
content varchar(250) NOT NULL, 
price double precision NOT NULL, 
user_id bigint, 
category varchar(50) NOT NULL, 
date timestamp,
month text,
constraint fk_user_id foreign key(user_id) references users(id)
);

INSERT INTO costs(content, price, user_id, category, date, month)
VALUES('Netflix', 5, 2, 'Subscription', '2022/11/05', 'November');

INSERT INTO costs(content, price, user_id, category, date, month)
VALUES('Hulu', 8, 2, 'Subscription', '2022/11/05', 'November');

INSERT INTO costs(content, price, user_id, category, date, month)
VALUES('T-Shirt', 25, 1, 'Clothing', '2022/11/23', 'November');