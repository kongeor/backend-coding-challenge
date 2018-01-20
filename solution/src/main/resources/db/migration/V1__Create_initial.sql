CREATE TABLE expense
(
  id serial PRIMARY KEY,
  date date NOT NULL,
  amount bigint NOT NULL,
  reason text
);