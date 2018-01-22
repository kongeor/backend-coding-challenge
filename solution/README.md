# README


## Dependencies

Make sure you have installed `java` and `postgresql`.

Verify that `java` is properly set:

```bash
$ java -version
```

If `postgresql` is properly installed an running you should be
able to connect to it using the `psql` interactive terminal:

```bash
$ psql -U postgres
```

Note: `postgres` user depends on the local installation. It is assumed
that `postgres` is the db superuser.

## Prepare DB

You will need two databases and users to be created in order to run the server and the intergration tests
respectively. Connect to db using `psql` as described above and then:

```psql
create database engage;
create database engage_test;
```

Create two users each one being the owner of these two databases:

```psql
create user engage with password 'engage';
create user engage_test with password 'engage_test';

alter database engage owner to engage;
alter database engage_test owner to engage_test;

```


## Running the instance




IMPORTANT
====
To avoid unconcious bias, we aim to have your submission reviewed anonymously by one of our engineering team. Please try and avoid adding personal details to this document such as your name, or using pronouns that might indicate your gender.