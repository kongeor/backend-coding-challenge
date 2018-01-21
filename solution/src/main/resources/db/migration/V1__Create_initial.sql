CREATE TABLE expense
(
  id serial PRIMARY KEY,
  date date NOT NULL,
  amount bigint NOT NULL,
  vat bigint NOT NULL,
  reason text NOT NULL,
  created_at timestamptz NOT NULL DEFAULT now()
);