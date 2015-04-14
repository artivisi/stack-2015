# Stack Development ArtiVisi 2015 #

Aplikasi terdiri dari beberapa bagian:

* Authorization Server : menangani login dan logout
* Resource Server : RESTful API
* Web Client : Aplikasi web dengan AngularJS

## Fitur Aplikasi ##

* Login
* Logout
* User Info
* Add ToDo list
* Remove ToDo list
* Set as completed

## Roadmap ##

* Aplikasi Client dengan Android
* Dynamic Menu

## Teknologi yang digunakan ##

### Server Side ###

* Spring Boot

    * Spring MVC
    * Spring Data JPA

* Spring OAuth 2
* Flyway Database Migration
* MySQL InnoDB
* Rest-Assured
* Apache Maven


### Client Side ###

* AngularJS
* Bootstrap
* Yeoman

## Build dan Run ##

### Persiapan Database ###

1. Install MySQL Server

2. Login ke MySQL Server

    ```
    mysql -u root -p
    ```

3. Buat user untuk mengakses database

    ```
    grant all on stack2015.* to stack2015@localhost identified by 'stack2015';
    ```

4. Buat databasenya

    ```
    create database stack2015;
    ```

### Resource Server ###

1. Masuk ke folder resource-server

    ```
    cd resource-server
    ```

2. Jalankan aplikasi

    ```
    mvn clean spring-boot:run
    ```

3. Browse ke [http://localhost:10002/api/halo?nama=endy](http://localhost:10002/api/halo?nama=endy)
