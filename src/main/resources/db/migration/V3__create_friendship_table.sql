DROP TABLE IF EXISTS friendships;

CREATE TABLE friendships (
  id bigserial PRIMARY KEY,
  sender bigint NOT NULL,
  receiver bigint NOT NULL,
  sender_username varchar(50) NOT NULL,
  status varchar(10) NOT NULL
);