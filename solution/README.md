# README


## Dependencies

Make sure you have `java` and `postgresql` installed.

Verify that `java` is properly set:

```bash
$ java -version
```

If `postgresql` is properly installed and running you should be
able to connect to it using the `psql` interactive terminal:

```bash
$ psql -U postgres
```

Note: It is assumed that `postgres` is the db superuser.


## Prepare DB

You will need two databases and users to be created in order to run the server and the integration tests
respectively. Connect to db using `psql` as described above and then:

```psql
create database engage;
create database engage_test;
```

Create two users and update the database ownership:

```psql
create user engage with password 'engage';
create user engage_test with password 'engage_test';

alter database engage owner to engage;
alter database engage_test owner to engage_test;
```


## Running the Web Server

In order to run the Web Server, execute:

```sh
$ cd solution
$ ./gradlew bootRun
```

Note: by default the port 8888 will be used


## Working with Angular

By running `gulp`, the webapp will be served on port 8080 and 
all requests to _/app/*_ will be proxied to 8888


## Running tests

Run

```bash
$ ./gradlew test
```

to run unit and integration tests


## Putting everything together

In the top level folder run the `prod-build.sh` script to produce
a production bundle. The produced `jar` can be found in the `solution/build/libs` folder.

Override the default application properties as follows:

```bash
$ java -jar solution-0.0.1-SNAPSHOT.jar --server.port=8887
```




